import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { AuthService } from '../auth/auth.service';

@Component({
    selector: 'app-header',
    templateUrl: './header.component.html',
    styleUrls: []
})
export class HeaderComponent implements OnInit, OnDestroy {

    isAuthenticated: boolean = false;
    authSub: Subscription;

    constructor(private authService: AuthService) {}

    ngOnInit(){
        this.authSub = this.authService.user.subscribe(user => {
            this.isAuthenticated = !user ? false : true;
            console.log();
        });
    }

    onLogoutClicked(){
        this.authService.logout();
    }

    ngOnDestroy(){
        this.authSub.unsubscribe();
    }

}