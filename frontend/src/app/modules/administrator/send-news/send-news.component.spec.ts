import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SendNewsComponent } from './send-news.component';

describe('SendNewsComponent', () => {
  let component: SendNewsComponent;
  let fixture: ComponentFixture<SendNewsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SendNewsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SendNewsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
