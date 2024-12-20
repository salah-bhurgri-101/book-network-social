import { Component } from '@angular/core';
import { AuthenticationRequest } from '../../services/models';
import { NgIf , NgFor } from '@angular/common';
import { NgModule } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../services/services';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

  authRequest: AuthenticationRequest = {email: '', password: ''};
  errorMsg: Array<string> = [];


  constructor(
    private router: Router,
    private authService: AuthenticationService,
    //
  ){}

  login(){
    // this.errorMsg = [];
    // this.authService.authenticate({
    //   body: this.authRequest
    // }).subscribe({
    //   next: ()=>{
    //     this.router.navigate(['books'])
    //   },
    //   error: (err)=>{
    //     console.log(err);
    //   }
    // })
  }
  register(){
    // this.router.navigate(['register'])
  }


}
