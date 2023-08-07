import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateDataEmployeeComponent } from './update-data-employee.component';

describe('UpdateDataEmployeeComponent', () => {
  let component: UpdateDataEmployeeComponent;
  let fixture: ComponentFixture<UpdateDataEmployeeComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UpdateDataEmployeeComponent]
    });
    fixture = TestBed.createComponent(UpdateDataEmployeeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
