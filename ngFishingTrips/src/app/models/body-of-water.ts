import { CatchLog } from './catch-log';
import { Fish } from './fish';

export class BodyOfWater {
  id: number;
  name: string;
  county: string;
  type: boolean;
  elevationInFt: number;
  description: string;
  image: string;
  website: string;
  fishies: Fish[] | undefined;
  logs: CatchLog[] | undefined;

  constructor(
    id: number = 0,
    name: string = '',
    county: string = '',
    type: boolean = false,
    elevationInFt: number = 0,
    description: string = '',
    image: string = '',
    website: string = '',
    fishies: Fish[] | undefined = [],
    logs: CatchLog[] | undefined = []
  ) {
    this.id = id;
    this.name = name;
    this.county = county;
    this.type = type;
    this.elevationInFt = elevationInFt;
    this.description = description;
    this.image = image;
    this.website = website;
    this.fishies = fishies;
    this.logs = logs;
  }
}
