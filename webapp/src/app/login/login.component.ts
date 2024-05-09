import { AfterViewInit, ApplicationModule, Component, ElementRef, OnInit, ViewChild, signal } from '@angular/core';
import { AuthService } from '../services/auth/auth.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, ApplicationModule, FormsModule, ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit, AfterViewInit {
  @ViewChild('username', { static: false })
  username!: ElementRef;

  authenticationError = signal(false);

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
    //
  }

  loginForm = new FormGroup({
    username: new FormControl('', { nonNullable: true, validators: [Validators.required] }),
    password: new FormControl('', { nonNullable: true, validators: [Validators.required] }),
  });

  login(): void {
    this.authService.login(this.loginForm.getRawValue().username, this.loginForm.getRawValue().password).subscribe({
      next: (response: any) => {
        console.log(response.body);
        this.router.navigate(['/transactions']);
      },
      error: (error) => {
        console.log(error.body);
        this.router.navigate(['/logout']);
      }
    });
  }

  ngAfterViewInit(): void {
    this.username.nativeElement.focus();
  }
}
