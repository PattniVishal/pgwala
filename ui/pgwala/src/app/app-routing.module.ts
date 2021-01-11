import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthComponent } from './auth/auth.component';
import { LoginComponent } from './auth/login/login.component';
import { SignupComponent } from './auth/signup/signup.component';
import { UserComponent } from './user/user.component';
import { HomepageComponent } from './homepage/homepage.component';
import { ProfileComponent } from './user/profile/profile.component';
import { PropertyComponent } from './user/property/property.component';
import { PropertyDetailComponent } from './user/property/property-detail/property-detail.component';
import { PropertyEditComponent } from './user/property/property-edit/property-edit.component';
import { SearchPropertyComponent } from './search-property/search-property.component';
import { PropertyListComponent } from './user/property/property-list/property-list.component';

const appRoutes: Routes = [
    { path: '', redirectTo: '/home', pathMatch: 'full' },
    { path: 'home', component: HomepageComponent },
    {
        path: 'auth', component: AuthComponent, children:
            [
                { path: '', redirectTo: 'login', pathMatch: 'full' },
                { path: 'login', component: LoginComponent },
                { path: 'signup', component: SignupComponent }
            ]
    },
    {
        path: 'user', component: UserComponent, children: [
            { path: '', redirectTo: 'profile', pathMatch: 'full' },
            { path: 'profile', component: ProfileComponent },
            { path: 'property', component: PropertyComponent },
            { path: 'property/new', component: PropertyEditComponent },
            { path: 'property/propertyDetail/edit', component: PropertyEditComponent },
            { path: 'property/propertyDetail/:id', component: PropertyDetailComponent }
        ]
    },
    { path: 'propertyDetail/:id', component: PropertyDetailComponent },
    {
        path: 'searchProperty', component: SearchPropertyComponent, children: [
            { path: '', component: PropertyListComponent, pathMatch: 'full' },
            { path: 'propertyDetail/:id', component: PropertyDetailComponent },
        ]
    }
]

@NgModule({
    imports: [RouterModule.forRoot(appRoutes)],
    exports: [RouterModule]
})
export class AppRoutingModule {

}