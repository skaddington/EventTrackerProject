import { BodyOfWater } from './body-of-water';
import { CatchLog } from './catch-log';

export class Fish {
  id: number;
  commonName: string;
  scientificName: string;
  image: string;
  website: string;
  waters: BodyOfWater[] | undefined;
  logs: CatchLog[] | undefined;

  constructor(
    id: number = 0,
    commonName: string = '',
    scientificName: string = '',
    image: string = '',
    website: string = '',
    waters: BodyOfWater[] | undefined = [],
    logs: CatchLog[] | undefined = []
  ) {
    this.id = id;
    this.commonName = commonName;
    this.scientificName = scientificName;
    this.image = image;
    this.website = website;
    this.waters = waters;
    this.logs = logs;
  }
}
