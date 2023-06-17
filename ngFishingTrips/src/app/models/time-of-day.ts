export class TimeOfDay {
  id: number;
  timeframe: string;

  constructor(id: number = 0, timeframe: string = '') {
    this.id = id;
    this.timeframe = timeframe;
  }
}
