import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DonorVisitedComponent } from './donor-visited.component';

describe('DonorVisitedComponent', () => {
  let component: DonorVisitedComponent;
  let fixture: ComponentFixture<DonorVisitedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DonorVisitedComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DonorVisitedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
