import { BodyOfWater } from "./body-of-water";
import { Fish } from "./fish";
import { TimeOfDay } from "./time-of-day";

export class CatchLog {
  id: number;
  date: string;
  weight: number;
  length: number;
  enabled: boolean;
  createdAt: string;
  lastUpdate: string;
  water: BodyOfWater;
  fish: Fish;
  time: TimeOfDay;

  constructor(
    id: number = 0,
    date: string = '',
    weight: number = 0,
    length: number = 0,
    enabled: boolean = true,
    createdAt: string = '',
    lastUpdate: string = '',
    water: BodyOfWater = new BodyOfWater(),
    fish: Fish = new Fish(),
    time: TimeOfDay = new TimeOfDay(),
  ){
    this.id = id;
    this.date = date;
    this.weight = weight;
    this.length = length;
    this.enabled = enabled;
    this.createdAt = createdAt;
    this.lastUpdate = lastUpdate;
    this.water = water;
    this.fish = fish;
    this.time = time;
  }

}
