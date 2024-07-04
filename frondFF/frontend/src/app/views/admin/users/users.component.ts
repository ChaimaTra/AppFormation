import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  userForm: FormGroup;
  users: any[] = [];
  editMode = false;
  currentIndex: number = 0;

  constructor(private fb: FormBuilder, private userService: UserService) {
    this.userForm = this.fb.group({
      firstname: ['', Validators.required],
      lastname: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]],
      quartier: ['', [Validators.required]],
    });
  }

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers(): void {
    this.userService.getUsers().subscribe(
      users => {
        this.users = users;
      },
      error => {
        console.error('Erreur lors du chargement des utilisateurs', error);
        // Gérer l'erreur (afficher un message à l'utilisateur par exemple)
      }
    );
  }

  submitForm(): void {
    if (this.userForm.valid) {
      const formValue = this.userForm.value;
      if (this.editMode && this.currentIndex !== null) {
        this.userService.updateUser(this.users[this.currentIndex].id, formValue).subscribe(
          updatedUser => {
            // Mettre à jour l'utilisateur dans la liste ou afficher un message de succès
            this.users[this.currentIndex] = updatedUser;
            this.resetForm();
          },
          error => {
            console.error('Erreur lors de la mise à jour de l\'utilisateur', error);
            // Gérer l'erreur (afficher un message à l'utilisateur par exemple)
          }
        );
      } else {
        this.userService.addUser(formValue).subscribe(
          newUser => {
            // Ajouter le nouvel utilisateur à la liste ou afficher un message de succès
            this.users.push(newUser);
            this.resetForm();
          },
          error => {
            console.error('Erreur lors de l\'ajout de l\'utilisateur', error);
            // Gérer l'erreur (afficher un message à l'utilisateur par exemple)
          }
        );
      }
    }
  }

  editUser(index: number): void {
    this.editMode = true;
    this.currentIndex = index;
    const user = this.users[index];
    this.userForm.patchValue(user); // Pré-remplir le formulaire avec les données de l'utilisateur sélectionné
  }

  deleteUser(index: number): void {
    if (this.currentIndex !== null) {
      const userId = this.users[index].id;
      this.userService.deleteUser(userId).subscribe(
        () => {
          // Supprimer l'utilisateur du tableau après suppression
          this.users.splice(index, 1);
        },
        error => {
          console.error('Erreur lors de la suppression de l\'utilisateur', error);
          // Gérer l'erreur (afficher un message à l'utilisateur par exemple)
        }
      );
    }
  }

  resetForm(): void {
    this.editMode = false;
    this.currentIndex = 0;
    this.userForm.reset({ role: 'user' }); // Réinitialiser le formulaire après l'ajout ou la modification
  }
}
