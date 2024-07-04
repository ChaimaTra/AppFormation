import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/views/services/auth.service';

@Component({
  selector: 'app-admin-layout',
  templateUrl: './admin-layout.component.html',
  styleUrls: ['./admin-layout.component.css']
})
export class AdminLayoutComponent implements OnInit {
  

 // Inject AuthService, Router, and FormBuilder in the constructor
 constructor(
  private authService: AuthService, // Inject AuthService for authentication
  private router: Router, // Inject Router for navigation
  
) { }

  ngOnInit(): void {
   
  }
/*  logout() {
    this.authService.logout();
    
  }*/
    logout() {
      this.authService.logout().subscribe({
        next: () => {
          this.router.navigate(['/signin']);
        },
        error: (err) => {
          console.error('La déconnexion a échoué', err);
          // Optionnellement, gérer l'erreur (par exemple, afficher une notification)
        }
      });
    }
}
