import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { AuthService } from '../auth/auth.service';
import { LoggedInUser } from '../shared/model/logged-in-user.model';
import { UserService } from './user.service';

@Component({
    selector: 'app-user',
    templateUrl: './user.component.html',
    styleUrls: []
})
export class UserComponent implements OnInit {

    authSub: Subscription;
    loggedInUser: LoggedInUser;

    constructor(private userService: UserService,
                private authService: AuthService) {}

    ngOnInit(){
        this.authSub = this.authService.user.subscribe(user => {
            this.loggedInUser = user;
        });
    }

}