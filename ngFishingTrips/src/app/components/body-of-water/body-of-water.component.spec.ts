import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BodyOfWaterComponent } from './body-of-water.component';

describe('BodyOfWaterComponent', () => {
  let component: BodyOfWaterComponent;
  let fixture: ComponentFixture<BodyOfWaterComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BodyOfWaterComponent]
    });
    fixture = TestBed.createComponent(BodyOfWaterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
