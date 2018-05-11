import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { FlightService } from '../services/flight.service';
import { NgForm } from '@angular/forms';
import { GoogleService } from '../services/google.service';

@Component({
  selector: 'app-flight-edit',
  templateUrl: './flight-edit.component.html',
  styleUrls: ['./flight-edit.component.css']
})
export class FlightEditComponent implements OnInit, OnDestroy {

  flight: any = {};
  sub: Subscription;
  travelDuration: number; // en secondes

  constructor(private route: ActivatedRoute,
    private router: Router,
    private flightService: FlightService,
    private googleService: GoogleService) {
  }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      // console.log('params[reference]= ' + params['reference'])
      const reference = params['reference'];
      if (reference) {
        this.flightService.get(reference).subscribe((flight: any) => {
          // console.log('flight => ' + JSON.stringify(flight))
          if (flight) {
            // console.log('flight._links.self.href :: ' + flight._links.self.href)
            this.flight = flight;
            this.flight.href = '//localhost:8080/' + this.flight.reference;
          } else {
            console.log(`Flight with reference '${reference}' not found, returning to list`);
            this.gotoList();
          }
        });
      }
    });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  save(form: NgForm) {
    this.travelDuration = this.googleService.calculDurationBetween(form.value.departureCity, form.value.destinationCity);
    console.log('travelDuration ' + this.travelDuration)
    this.flightService.save(form).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }

  remove(href) {
    this.flightService.remove(href).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }

  gotoList() {
    this.router.navigate(['/flight-list']);
  }

}
