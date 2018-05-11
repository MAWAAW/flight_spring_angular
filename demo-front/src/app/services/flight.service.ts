import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class FlightService {

  API = '//localhost:8080';
  FLIGHT_API = this.API + '/flights';

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    console.log('GET ALL');
    return this.http.get(this.API);
  }

  get(reference: string) {
    console.log('GET ' + reference);
    return this.http.get(this.API + '/' + reference);
  }

  save(flight: any): Observable<any> {
    let result: Observable<Object>;
    if (flight['href']) {
      console.log('PUT ' + flight);
      result = this.http.put(this.API, flight);
    } else {
      console.log('POST ' + flight);
      result = this.http.post(this.API, flight);
    }
    return result;
  }

  remove(href: string) {
    console.log('DELETE ' + href);
    return this.http.delete(href);
  }

}
