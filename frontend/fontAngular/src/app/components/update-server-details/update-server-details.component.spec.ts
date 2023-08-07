import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateServerDetailsComponent } from './update-server-details.component';

describe('UpdateServerDetailsComponent', () => {
  let component: UpdateServerDetailsComponent;
  let fixture: ComponentFixture<UpdateServerDetailsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UpdateServerDetailsComponent]
    });
    fixture = TestBed.createComponent(UpdateServerDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
