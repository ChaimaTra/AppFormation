import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../../services/user.service';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  profileForm: FormGroup;

  constructor(private fb: FormBuilder, private userService: UserService) {
    this.profileForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    const userId = 'user-id-placeholder'; // Replace with actual logic to get user ID
    this.userService.getUserProfile(userId).subscribe((user: any) => {
      this.profileForm.patchValue(user);
    });
  }

  onSubmit(): void {
    if (this.profileForm.valid) {
      const userId = 'user-id-placeholder'; // Replace with actual user ID
      this.userService.updateUser(userId, this.profileForm.value).subscribe((response: any) => {
        console.log('Profile updated successfully', response);
      }, (error: any) => {
        console.error('Error updating profile', error);
      });
    }
  }
}


