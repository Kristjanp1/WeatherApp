import {Place} from "./place";
import {Wind} from "./wind";

export class ForecastData {
  phenomenon: string
  tempMin: number
  tempMax: number
  text: string
  placeList?: Place[]
  windList?: Wind[]
  sea?: string
  peipsi?: string

  constructor(phenomenon: string, tempMin: number, tempMax: number, text:string, placeList?: Place[], windList?: Wind[], sea?: string, peipsi?: string) {
    this.phenomenon = phenomenon
    this.tempMin = tempMin
    this.tempMax = tempMax
    this.text = text
    this.placeList = placeList
    this.windList = windList
    this.sea = sea
    this.peipsi = peipsi
  }
}

