import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class GoogleService {

  GEOCODING_API = 'https://maps.googleapis.com/maps/api/geocode/json?address=';
  API_KEY = 'AIzaSyBf4rD9e9HrJSb6tZKOgXPjzhddfjZPVU8';
  fromLat: number;
  fromLng: number;
  toLat: number;
  toLng: number;
  distance: number;

  constructor(private http: HttpClient) { }

  getLatLng(city: string) {
    return this.http.get(this.GEOCODING_API + city + '&key=' + this.API_KEY);
  }

  calculDistanceBetween(fromLat: number, fromLng: number, toLat: number, toLng: number) {
    console.log('ALL VALUES:' + fromLat + fromLng + toLat + toLng);
    const fromLatLng = new google.maps.LatLng(fromLat, fromLng);
    const toLatLng = new google.maps.LatLng(toLat, toLng);
    console.log('AFTER GOOGLE MAPS TRANSFORM:' + fromLatLng + ' ' + toLatLng);
    const distance = google.maps.geometry.spherical.computeDistanceBetween(fromLatLng, toLatLng);
    console.log('Distance:' + distance);
    return distance;
  }

  calculDurationBetween(departureCity: string, destinationCity: string) {

    this.getLatLng(departureCity).subscribe(res => {
      this.fromLat = (res['results'])['0'].geometry.location.lat;
      this.fromLng = (res['results'])['0'].geometry.location.lng;
      this.getLatLng(destinationCity).subscribe(res2 => {
        this.toLat = (res2['results'])['0'].geometry.location.lat;
        this.toLng = (res2['results'])['0'].geometry.location.lng;
        this.distance = this.calculDistanceBetween(this.fromLat, this.fromLng, this.toLat, this.toLng);
      });
    });

    return (this.distance / 1000) / (800 / 3600); // Transformation pour avoir une durée en secondes

  }

}
