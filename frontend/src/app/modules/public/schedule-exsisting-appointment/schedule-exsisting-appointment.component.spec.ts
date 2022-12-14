import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ScheduleExsistingAppointmentComponent } from './schedule-exsisting-appointment.component';

describe('ScheduleExsistingAppointmentComponent', () => {
  let component: ScheduleExsistingAppointmentComponent;
  let fixture: ComponentFixture<ScheduleExsistingAppointmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ScheduleExsistingAppointmentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ScheduleExsistingAppointmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
