import {Component} from '@angular/core';
import {WeatherService} from "./weather.service";
import {Observable} from "rxjs";
import {Forecast} from "./datamodel/forecast";
import {tap} from "rxjs/operators";
import {Place} from "./datamodel/place";
import {ForecastData} from "./datamodel/forecast-data";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor(
    private weatherService: WeatherService
  ){}

  public isDaySelected: boolean = true
  public selectedPlace: Place | undefined

  public selectedForecast: Forecast | undefined
  public selectedForecastData: ForecastData | undefined
  public weatherData$: Observable<Forecast[]> = this.weatherService.getWeatherData().pipe(
    tap((weather: Forecast[])  => {
      this.selectedForecast = weather[0]
      this.selectedForecastData=this.isDaySelected ? this.selectedForecast?.dayData: this.selectedForecast?.nightData
    })
  );


  ngOnInit(): void {
  }

  public switchToNight(): void {
    this.selectedForecastData = this.selectedForecast?.nightData
    this.isDaySelected = false
  }
  public switchToDay(): void{
    this.selectedForecastData = this.selectedForecast?.dayData
    this.isDaySelected = true
  }
  public switchDate(forecast: Forecast): void {
    if(!forecast) {return;}
    this.selectedForecast = forecast
    this.selectedForecastData = this.isDaySelected ? forecast.dayData : forecast.nightData
  }
  public nextPlace(): Place | undefined {
    if(!this.selectedForecastData?.placeList) {return}
    if(this.selectedPlace) {
      const selectedPlaceidx: number = this.selectedForecastData.placeList?.indexOf(this.selectedPlace)
      this.selectedPlace = this.selectedForecastData?.placeList[selectedPlaceidx + 1]
      return this.selectedPlace
    }
    this.selectedPlace = this.selectedForecastData.placeList[0]
    return this.selectedPlace
  }

  public previousPlace(): Place | undefined {
    if(!this.selectedForecastData?.placeList) {return}
    if(this.selectedPlace) {
      const selectedPlaceidx: number = this.selectedForecastData.placeList?.indexOf(this.selectedPlace)
      this.selectedPlace = this.selectedForecastData?.placeList[selectedPlaceidx - 1]
      return this.selectedPlace
    }
    return this.selectedPlace
  }

}
