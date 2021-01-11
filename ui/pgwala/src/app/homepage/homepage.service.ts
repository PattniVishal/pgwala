import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Injectable({
    providedIn: 'root'
})
export class HomepageService{

    constructor(private http: HttpClient) {}

    fetchFeaturedProperties(){
        return this.http.get(environment.getAllProperties);
    }

    fetchNewlyAddedProperties(){
        return this.http.get(environment.getAllProperties);
    }

    searchProperty(area: string, city: string){

    }

}