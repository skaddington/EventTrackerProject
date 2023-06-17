import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CatchLogComponent } from './catch-log.component';

describe('CatchLogComponent', () => {
  let component: CatchLogComponent;
  let fixture: ComponentFixture<CatchLogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CatchLogComponent]
    });
    fixture = TestBed.createComponent(CatchLogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
