import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayAllUsersComponent } from './display-all-users.component';

describe('DisplayAllUsersComponent', () => {
  let component: DisplayAllUsersComponent;
  let fixture: ComponentFixture<DisplayAllUsersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DisplayAllUsersComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DisplayAllUsersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
