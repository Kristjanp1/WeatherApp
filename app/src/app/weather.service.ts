import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, take} from "rxjs";
import {Forecast} from "./datamodel/forecast";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class WeatherService {

  private weatherApiUrl: string = "http://localhost:8080/weatherData"

  constructor(private http: HttpClient) {}


public getWeatherData(): Observable<Forecast[]> {

  return this.http.get<Forecast[]>(this.weatherApiUrl).pipe(
    take(1),
    map((forecast: Forecast[]) =>forecast)
  )
}
}
