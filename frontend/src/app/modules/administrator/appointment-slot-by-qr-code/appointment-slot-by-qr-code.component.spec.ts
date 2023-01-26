import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppointmentSlotByQrCodeComponent } from './appointment-slot-by-qr-code.component';

describe('AppointmentSlotByQrCodeComponent', () => {
  let component: AppointmentSlotByQrCodeComponent;
  let fixture: ComponentFixture<AppointmentSlotByQrCodeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AppointmentSlotByQrCodeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AppointmentSlotByQrCodeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
