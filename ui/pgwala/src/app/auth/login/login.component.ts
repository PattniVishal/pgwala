import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';

import { AuthService } from '../auth.service';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html'
})
export class LoginComponent {

    loginSub: Subscription;
    errorMsg: string = null;

    constructor(private authService: AuthService,
                private router: Router) {}

    onLoginClicked(loginForm: NgForm){
        const email = loginForm.value.email;
        const password = loginForm.value.password;

        console.log(email, password);

        this.authService.login(email, password)
        .subscribe(loginResp => {
            console.log(loginResp);
            this.router.navigate(['/home']);
        },
        errorMessage => {
            console.log(errorMessage);
            this.errorMsg = errorMessage;
        });
    }

}