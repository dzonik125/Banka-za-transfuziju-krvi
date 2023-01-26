import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterMedicalWorkerComponent } from './register-medical-worker.component';

describe('RegisterMedicalWorkerComponent', () => {
  let component: RegisterMedicalWorkerComponent;
  let fixture: ComponentFixture<RegisterMedicalWorkerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegisterMedicalWorkerComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegisterMedicalWorkerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
