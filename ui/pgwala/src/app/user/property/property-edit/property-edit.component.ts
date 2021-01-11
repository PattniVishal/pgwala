import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Params } from '@angular/router';

@Component({
    selector: 'app-property-edit',
    templateUrl: './property-edit.component.html',
    styleUrls: []
})
export class PropertyEditComponent implements OnInit {

    propertyId: string;

    isEditMode: boolean;

    constructor(private route: ActivatedRoute) {}

    ngOnInit(){
        this.route.params
        .subscribe((params: Params) => {
            this.propertyId = params['id'];
            this.isEditMode = params['id'] != null;
        });
    }

    onSaveClicked(propertyForm: NgForm) {
        console.log("Add New Property : ", propertyForm);
    }

}