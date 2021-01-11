import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/app/auth/auth.service';
import { LoggedInUser } from 'src/app/shared/model/logged-in-user.model';
import { Property } from 'src/app/shared/model/property.model';
import { UserService } from '../../user.service';

@Component({
    selector: 'app-property-detail',
    templateUrl: './property-detail.component.html',
    styles: ['carousel-inner img { width: 100%; height: 100%; }']
})
export class PropertyDetailComponent implements OnInit {

    propertyId: string;
    property: Property;
    loggedInUser: LoggedInUser;
    userSub: Subscription;

    constructor(private route: ActivatedRoute,
                private userService: UserService) {}

    ngOnInit(){
        this.route.params
        .subscribe((params: Params) => {
            this.propertyId = params['id'];
            this.userSub = this.userService.getPropertyById(this.propertyId)
                                .subscribe(property => {
                                    this.property = property.data;
                                    console.log("Property in PropDetailsComp : ", this.property);
                                });
        });
    }

}