import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterBloodBankComponent } from './register-blood-bank.component';

describe('RegisterBloodBankComponent', () => {
  let component: RegisterBloodBankComponent;
  let fixture: ComponentFixture<RegisterBloodBankComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegisterBloodBankComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegisterBloodBankComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
