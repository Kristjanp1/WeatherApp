export class Wind {
  location: string
  direction: string
  speedMin: number
  speedMax: number
  gustSpeed: number

  constructor(location: string, direction: string, speedmin: number, speedmax: number, gustspeed:number) {
    this.location = location
    this.direction = direction
    this.speedMin = speedmin
    this.speedMax = speedmax
    this.gustSpeed = gustspeed
  }
}
