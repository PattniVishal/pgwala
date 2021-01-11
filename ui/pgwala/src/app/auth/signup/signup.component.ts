import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { ResponseDTO } from 'src/app/shared/dto/response-dto.model';

import { AuthService } from '../auth.service';

@Component({
    selector: 'app-signup',
    templateUrl: './signup.component.html'
})
export class SignupComponent implements OnInit {

    registerObs: Observable<ResponseDTO>;
    errorMsg: string = null;

    constructor(private authService: AuthService,
        private router: Router) {}

    ngOnInit(){

    }

    onSignupClicked(signupForm: NgForm){

        let name: string = signupForm.value.name;
        let email: string = signupForm.value.email;
        let password: string = signupForm.value.password;
        let isSeller: boolean = signupForm.value.isSeller;
        let aadharNumber: string;

        if(isSeller){
            aadharNumber = signupForm.value.aadharNumber;    
            this.registerObs = this.authService.register(name, email, password, isSeller, aadharNumber);
        }
        else{
            this.registerObs = this.authService.register(name, email, password, isSeller);
        }

        this.registerObs.subscribe(registerRes => {
            this.router.navigate(['/home']);
        },
        errorMessage => {
            console.log("Register Err", errorMessage);
            this.errorMsg = errorMessage;
        });

    }

}