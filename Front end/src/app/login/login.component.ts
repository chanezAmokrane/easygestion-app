import { Component } from '@angular/core';
import { FormBuilder, FormGroup, PatternValidator, Validators } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule,CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  loginForm:FormGroup;
  loginFailed:boolean=false;
  
  
  

  constructor( public fb:FormBuilder, public router:Router){
    
this.loginForm = this.fb.group({
  userName: ['', [
    Validators.required,
    Validators.pattern('^[a-zA-Z].{11,}$')
  ]],
  passWord: ['', [
    Validators.required,
    Validators.pattern('^(?=.*[A-Z])(?=.*[0-9])(?=.*[=\\?;,!]).{12,}$')
  ]]
});

  }

public OnSubmit(): void {
  if(this.loginForm.invalid){
    this.loginForm.markAllAsTouched();
  }
  else {
  const userName= this.loginForm.get('userName').value;
   const passWord=this.loginForm.get('passWord').value;
     if(userName=="chanezUserApp"&& passWord=="WebTest100%?"){
      this.router.navigate(['/dashBoard']);
     }
     else{
           this.loginFailed=true;
     }
  }}
}
