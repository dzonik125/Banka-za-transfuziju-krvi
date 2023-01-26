import { TestBed } from '@angular/core/testing';

import { BloodBankServiceService } from './blood-bank-service.service';

describe('BloodBankServiceService', () => {
  let service: BloodBankServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BloodBankServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
