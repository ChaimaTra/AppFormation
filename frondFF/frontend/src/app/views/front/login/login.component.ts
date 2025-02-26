import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  email: string = '';
  password: string = '';
  AuthUserSub!:Subscription ;
  errorMessage!:Error;
  

  constructor(private authService: AuthService, private router: Router) { }

  // Lifecycle hook that is called after Angular has initialized all data-bound properties
  ngOnInit() {
    // Subscribe to the AuthenticatedUser$ observable to monitor authentication state
    this.AuthUserSub = this.authService.AuthenticatedUser$.subscribe({
      next: user => {
        // If a user is authenticated, navigate to the home page
        if (user) {
          this.router.navigate(['/']);
        }
      }
    });
  }

  // Method to handle the sign-in form submission
  onSubmitSingin(formLogin: NgForm) {
    // Validate the form
    if (!formLogin.valid) {
      return;
    }

    const email = formLogin.value.email; // Get email from the form
    const password = formLogin.value.password; // Get password from the form

    // Call the login method from AuthService
    this.authService.login(email, password).subscribe({
      next: userData => {
        // On successful login, navigate to the home page
        this.router.navigate(['/']);
      },
      error: err => {
        // On error, set the error message and log it to the console
        this.errorMessage = err;
      }
    });
  }

  // Lifecycle hook that is called when the component is destroyed
  ngOnDestroy() {
    // Unsubscribe from the AuthenticatedUser$ observable to prevent memory leaks
    this.AuthUserSub.unsubscribe();
  }
  }
