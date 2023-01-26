import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayAllCentersComponent } from './display-all-centers.component';

describe('DisplayAllCentersComponent', () => {
  let component: DisplayAllCentersComponent;
  let fixture: ComponentFixture<DisplayAllCentersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DisplayAllCentersComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DisplayAllCentersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
