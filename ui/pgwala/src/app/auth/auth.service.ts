import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { catchError, tap } from 'rxjs/operators';
import { Observable, BehaviorSubject, throwError } from 'rxjs';

import { ResponseDTO } from '../shared/dto/response-dto.model';
import { LoggedInUser } from '../shared/model/logged-in-user.model';
import { UserComponent } from '../user/user.component';
import { Router } from '@angular/router';

@Injectable({
    providedIn: 'root'
})
export class AuthService{
    tokenExpirationTimeout;

    user = new BehaviorSubject<LoggedInUser>(null);

    constructor(private http: HttpClient,
                private router: Router) {}

    login(email: string, password: string){
        return this.http.post(environment.authServiceLoginUrl, 
            {
                email: email,
                password: password
            })
            .pipe(
                catchError(this.handleError),
                tap((loginRes: ResponseDTO) => {
                    console.log("Login Response : ", loginRes);
                    this.handleAuthentication(loginRes.data)
                })
            );
    }
    autoLogin(){
        const userData: LoggedInUser = JSON.parse(localStorage.getItem('userData'));

        if(!userData){
            return;
        }

        const loadedUser = new LoggedInUser(userData.email, userData.password, userData.jwt, userData.expiresAt, userData.role);
        if((new Date()) < (new Date(userData.expiresAt))){
            let expirationDuration = (new Date(loadedUser.expiresAt).getTime() - new Date().getTime());
            this.autoLogout(expirationDuration);
        
            this.user.next(loadedUser);
        }
    }

    register(name: string, email: string, password: string, isSeller: boolean, aadharNumber?: string){
        return this.http.post(environment.authServiceRegisterUrl,
            {
                name: name,
                email: email,
                password: password,
                role: isSeller ? "seller" : "buyer",
                aadharNumber: isSeller ? aadharNumber : null
            })
            .pipe(
                catchError(this.handleError),
                tap((registerRes: ResponseDTO) => {
                    console.log("Register Response : ", registerRes);
                    this.handleAuthentication(registerRes.data);
                })
            );
    }

    logout(){
        this.user.next(null);
        localStorage.removeItem("userData");
        if(this.tokenExpirationTimeout){
            clearTimeout(this.tokenExpirationTimeout);
        }
        this.tokenExpirationTimeout = null;
        this.router.navigate(['/home']);
    }

    autoLogout(expirationDuration: number){
        this.tokenExpirationTimeout = setTimeout(() => {
            this.logout();
        }, expirationDuration);
    }

    private handleError(errorRes: HttpErrorResponse){
        return throwError(errorRes.error.message);
    }

    private handleAuthentication(loggedInUserData){
        const user = new LoggedInUser(loggedInUserData.email,
                                    loggedInUserData.password,
                                    loggedInUserData.jwt,
                                    loggedInUserData.expiresAt,
                                    loggedInUserData.role);
        let expirationDuration = (new Date(loggedInUserData.expiresAt).getTime() - new Date().getTime());
        this.autoLogout(expirationDuration);
        this.user.next(user);
        localStorage.setItem("userData", JSON.stringify(user));
    }

}