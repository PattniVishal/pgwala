import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/app/auth/auth.service';
import { UserService } from '../user.service';

import { LoggedInUser } from 'src/app/shared/model/logged-in-user.model';
import { UserDTO } from '../../shared/dto/user-dto.model';
import { ResponseDTO } from 'src/app/shared/dto/response-dto.model';
import { exhaustMap, take } from 'rxjs/operators';

@Component({
    selector: 'app-profile',
    templateUrl: './profile.component.html',
    styleUrls: []
})
export class ProfileComponent implements OnInit {

    authSub: Subscription;
    userSub: Subscription;
    loggedInUser: LoggedInUser;
    user: UserDTO;

    constructor(private authService: AuthService,
                private userService: UserService) {}

    ngOnInit(){
        this.authSub = this.authService.user.subscribe(user => {
            this.loggedInUser = user;
            console.log("In ProfilComp: ", this.loggedInUser);
            this.setUser();
        });
    }

    setUser(){
        if(this.loggedInUser.role == "buyer"){
            this.userService.getBuyer(this.loggedInUser.email, this.loggedInUser.jwt).subscribe();
            this.userService.user.subscribe(currentUser => {
                this.user = currentUser;
                console.log("User if buyer : ", this.user);
            });
        }
        if(this.loggedInUser.role == "seller"){
            this.userService.getSeller(this.loggedInUser.email, this.loggedInUser.jwt).subscribe();
            this.userService.user.subscribe(currentUser => {
                this.user = currentUser;
                console.log("User if seller : ", this.user);
            });
        }
    }

}