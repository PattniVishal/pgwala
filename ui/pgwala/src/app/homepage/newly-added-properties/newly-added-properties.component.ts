import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

import { HomepageService } from '../homepage.service';

import { Property } from '../../shared/model/property.model';

@Component({
    selector: 'app-newly-added-properties',
    templateUrl: './newly-added-properties.component.html',
    styleUrls: []
})

export class NewlyAddedPropertiesComponent implements OnInit {

    newlyAddedProperties: Property[];

    constructor(private http: HttpClient,
        private homepageService: HomepageService) {}

    ngOnInit(){
        this.homepageService.fetchNewlyAddedProperties()
        .subscribe((res: any) => {
            console.log("Newly-Added-Properties: ", res);
            this.newlyAddedProperties = res.data;
        });
    }

}