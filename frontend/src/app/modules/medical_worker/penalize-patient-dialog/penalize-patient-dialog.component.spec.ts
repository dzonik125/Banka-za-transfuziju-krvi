import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PenalizePatientDialogComponent } from './penalize-patient-dialog.component';

describe('PenalizePatientDialogComponent', () => {
  let component: PenalizePatientDialogComponent;
  let fixture: ComponentFixture<PenalizePatientDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PenalizePatientDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PenalizePatientDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
