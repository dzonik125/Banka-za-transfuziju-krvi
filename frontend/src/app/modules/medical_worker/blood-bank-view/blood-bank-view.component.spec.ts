import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BloodBankViewComponent } from './blood-bank-view.component';

describe('BloodBankViewComponent', () => {
  let component: BloodBankViewComponent;
  let fixture: ComponentFixture<BloodBankViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BloodBankViewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BloodBankViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
