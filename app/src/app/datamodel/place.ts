export class Place {
  locationName:string
  phenomenon: string
  tempMax?:number
  tempMin?:number

  constructor(location:string, phenomenon:string, tempMax?: number, tempMin?:number) {
    this.locationName = location
    this.phenomenon = phenomenon
  }

  public set setTempMax(value:number){
    this.tempMax = value
  }

  public set setTempMin(value:number){
    this.tempMin = value
  }
}
