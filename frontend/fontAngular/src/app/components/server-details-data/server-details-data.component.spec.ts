import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ServerDetailsDataComponent } from './server-details-data.component';

describe('ServerDetailsDataComponent', () => {
  let component: ServerDetailsDataComponent;
  let fixture: ComponentFixture<ServerDetailsDataComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ServerDetailsDataComponent]
    });
    fixture = TestBed.createComponent(ServerDetailsDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
