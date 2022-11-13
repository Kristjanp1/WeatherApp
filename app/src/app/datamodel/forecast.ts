import {ForecastData} from "./forecast-data";

export class Forecast {
  date: string
  nightData: ForecastData
  dayData: ForecastData

  constructor(date: string, nightData: ForecastData, dayData: ForecastData) {
    this.date = date
    this.nightData = nightData
    this.dayData = dayData
  }


}
