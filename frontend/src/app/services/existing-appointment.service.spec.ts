import { TestBed } from '@angular/core/testing';

import { ExistingAppointmentService } from './existing-appointment.service';

describe('ExistingAppointmentService', () => {
  let service: ExistingAppointmentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ExistingAppointmentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
