import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';

import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { HomepageComponent } from './homepage/homepage.component';
import { FeaturedPropertiesComponent } from './homepage/featured-properties/featured-properties.component';
import { NewlyAddedPropertiesComponent } from './homepage/newly-added-properties/newly-added-properties.component';
import { SignupComponent } from './auth/signup/signup.component';
import { LoginComponent } from './auth/login/login.component';
import { AuthComponent } from './auth/auth.component';
import { UserComponent } from './user/user.component';
import { ProfileComponent } from './user/profile/profile.component';
import { PropertyComponent } from './user/property/property.component';
import { PropertyListComponent } from './user/property/property-list/property-list.component' ;
import { PropertyDetailComponent } from './user/property/property-detail/property-detail.component';
import { PropertyEditComponent } from './user/property/property-edit/property-edit.component';
import { SearchPropertyComponent } from './search-property/search-property.component';
import { LoaderComponent } from './shared/loader/loader.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    HomepageComponent,
    FeaturedPropertiesComponent,
    NewlyAddedPropertiesComponent,
    AuthComponent,
    SignupComponent,
    LoginComponent,
    UserComponent,
    ProfileComponent,
    PropertyComponent,
    PropertyListComponent,
    PropertyDetailComponent,
    PropertyEditComponent,
    SearchPropertyComponent,
    LoaderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
