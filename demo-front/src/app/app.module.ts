import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AgmCoreModule, MapsAPILoader } from '@agm/core';

import { AppComponent } from './app.component';
import { FlightService } from './services/flight.service';
import { FlightListComponent } from './flight-list/flight-list.component';
import { FlightEditComponent } from './flight-edit/flight-edit.component';
import { GoogleService } from './services/google.service';

export const API_KEY = 'AIzaSyBf4rD9e9HrJSb6tZKOgXPjzhddfjZPVU8';

const appRoutes: Routes = [
  { path: '', redirectTo: '/flight-list', pathMatch: 'full' },
  {
    path: 'flight-list',
    component: FlightListComponent
  },
  {
    path: 'flight-add',
    component: FlightEditComponent
  },
  {
    path: 'flight-edit/:reference',
    component: FlightEditComponent
  }
];

@NgModule({
  declarations: [
    AppComponent,
    FlightListComponent,
    FlightEditComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes),
    NgbModule.forRoot(),
    AgmCoreModule.forRoot({
      apiKey: API_KEY
    })
  ],
  providers: [FlightService, GoogleService],
  bootstrap: [AppComponent]
})
export class AppModule { }
