import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddOrderProductComponent } from './add-order-product.component';

describe('AddOrderProductComponent', () => {
  let component: AddOrderProductComponent;
  let fixture: ComponentFixture<AddOrderProductComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddOrderProductComponent]
    });
    fixture = TestBed.createComponent(AddOrderProductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
