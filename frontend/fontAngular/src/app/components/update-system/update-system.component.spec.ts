import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateSystemComponent } from './update-system.component';

describe('UpdateSystemComponent', () => {
  let component: UpdateSystemComponent;
  let fixture: ComponentFixture<UpdateSystemComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UpdateSystemComponent]
    });
    fixture = TestBed.createComponent(UpdateSystemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
