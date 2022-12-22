import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ScheduleNewAppointmentComponent } from './schedule-new-appointment.component';

describe('ScheduleNewAppointmentComponent', () => {
  let component: ScheduleNewAppointmentComponent;
  let fixture: ComponentFixture<ScheduleNewAppointmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ScheduleNewAppointmentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ScheduleNewAppointmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
