import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/app/auth/auth.service';
import { HomepageService } from 'src/app/homepage/homepage.service';
import { LoggedInUser } from 'src/app/shared/model/logged-in-user.model';
import { Property } from 'src/app/shared/model/property.model';
import { UserService } from '../../user.service';

@Component({
    selector: 'app-property-list',
    templateUrl: './property-list.component.html',
    styleUrls: []
})
export class PropertyListComponent implements OnInit {

    authSub: Subscription;
    userSub: Subscription;
    loggedInUser: LoggedInUser;
    properties: Property[];

    constructor(private userService: UserService,
        private homepageService: HomepageService,
        private authService: AuthService,
        private router: Router) { }

    ngOnInit() {
        if (this.router.url === '/searchProperty') {
            this.homepageService.fetchFeaturedProperties()
                .subscribe((res: any) => {
                    console.log("FeaturedProperties : ", res);
                    this.properties = res.data;
                });
        }

        else {
            this.authSub = this.authService.user.subscribe(user => {
                this.loggedInUser = user;
                console.log("In PropertyList Comp: ", this.loggedInUser);
                this.userService.getSeller(this.loggedInUser.email, this.loggedInUser.jwt).subscribe(() => {
                    this.userSub = this.userService.user.subscribe(currentUser => {
                        this.properties = currentUser.properties;
                        console.log("Properties of seller in PropListComp : ", this.properties);
                    });
                });
            });
        }
    }

}