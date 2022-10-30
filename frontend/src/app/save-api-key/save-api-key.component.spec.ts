import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SaveApiKeyComponent } from './save-api-key.component';

describe('SaveApiKeyComponent', () => {
  let component: SaveApiKeyComponent;
  let fixture: ComponentFixture<SaveApiKeyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SaveApiKeyComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SaveApiKeyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
