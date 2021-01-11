import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

import { HomepageService } from '../homepage.service';

import { Property } from '../../shared/model/property.model';

@Component({
    selector: 'app-featured-properties',
    templateUrl: './featured-properties.component.html',
    styleUrls: []
})

export class FeaturedPropertiesComponent implements OnInit {

    featuredProperties: Property[];

    constructor(private http: HttpClient,
                private homepageService: HomepageService) {}

    ngOnInit(){
        this.homepageService.fetchFeaturedProperties()
        .subscribe((res: any) => {
            console.log("FeaturedProperties : ", res);
            this.featuredProperties = res.data;
        });
    }

}