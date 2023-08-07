import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddSystemComponent } from './add-system.component';

describe('AddSystemComponent', () => {
  let component: AddSystemComponent;
  let fixture: ComponentFixture<AddSystemComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddSystemComponent]
    });
    fixture = TestBed.createComponent(AddSystemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
