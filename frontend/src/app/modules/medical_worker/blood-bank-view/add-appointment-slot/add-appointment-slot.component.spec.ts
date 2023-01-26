import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddAppointmentSlotComponent } from './add-appointment-slot.component';

describe('AddAppointmentSlotComponent', () => {
  let component: AddAppointmentSlotComponent;
  let fixture: ComponentFixture<AddAppointmentSlotComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddAppointmentSlotComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddAppointmentSlotComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
