import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddWorkerToBankDialogComponent } from './add-worker-to-bank-dialog.component';

describe('AddWorkerToBankDialogComponent', () => {
  let component: AddWorkerToBankDialogComponent;
  let fixture: ComponentFixture<AddWorkerToBankDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddWorkerToBankDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddWorkerToBankDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
