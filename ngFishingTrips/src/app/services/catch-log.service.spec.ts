import { TestBed } from '@angular/core/testing';

import { CatchLogService } from './catch-log.service';

describe('CatchLogService', () => {
  let service: CatchLogService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CatchLogService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
