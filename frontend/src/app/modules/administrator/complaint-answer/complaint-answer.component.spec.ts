import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ComplaintAnswerComponent } from './complaint-answer.component';

describe('ComplaintAnswerComponent', () => {
  let component: ComplaintAnswerComponent;
  let fixture: ComponentFixture<ComplaintAnswerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ComplaintAnswerComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ComplaintAnswerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
