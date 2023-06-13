import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MonthlySubscriptionsComponent } from './monthly-subscriptions.component';

describe('MonthlySubscriptionsComponent', () => {
  let component: MonthlySubscriptionsComponent;
  let fixture: ComponentFixture<MonthlySubscriptionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MonthlySubscriptionsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MonthlySubscriptionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
