import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
    selector: 'app-homepage',
    templateUrl: './homepage.component.html',
    styleUrls: []
})

export class HomepageComponent implements OnInit {

    constructor(private http: HttpClient){}

    ngOnInit(){
        
    }

}