import { TestBed } from '@angular/core/testing';

import { BodyOfWaterService } from './body-of-water.service';

describe('BodyOfWaterService', () => {
  let service: BodyOfWaterService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BodyOfWaterService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
