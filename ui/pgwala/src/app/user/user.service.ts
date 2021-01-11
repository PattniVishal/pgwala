import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { tap } from 'rxjs/operators';

import { environment } from '../../environments/environment';
import { UserDTO } from '../shared/dto/user-dto.model';
import { BehaviorSubject } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class UserService {

    user = new BehaviorSubject<UserDTO>(null);

    constructor(private http: HttpClient) { }

    getBuyer(email: string, jwt: string) {
        return this.http.get(environment.getBuyer + "/" + email,
            {
                headers: new HttpHeaders({ 'Authorization': "Bearer " + jwt })
            })
            .pipe(
                tap((buyer: any) => {
                    console.log("Buyer Response : ", buyer);
                    this.user.next(buyer.data);
                    // console.log("User if buyer [Service] : ", this.user);
                })
            );
    }

    getSeller(email: string, jwt: string) {
        return this.http.get(environment.getSeller + "/" + email,
            {
                headers: new HttpHeaders({ 'Authorization': "Bearer " + jwt })
            })
            .pipe(
                tap((seller: any) => {
                    console.log("Seller Response : ", seller);
                    this.user.next(seller.data);
                    // console.log("User if seller [Service] : ", this.user);
                })
            );
    }

    getPropertyById(propertyId: string) {
        return this.http.get(environment.getPropertyById + "/" + propertyId,
            // {
            //     headers: new HttpHeaders({ 'Authorization': "Bearer " + jwt })
            // }
        )
            .pipe(
                tap((property: any) => {
                    console.log("Property Response : ", property);
                })
            );
    }

}