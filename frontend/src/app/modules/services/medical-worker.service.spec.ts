import { TestBed } from '@angular/core/testing';

import { MedicalWorkerService } from './medical-worker.service';

describe('MedicalWorkerService', () => {
  let service: MedicalWorkerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MedicalWorkerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
