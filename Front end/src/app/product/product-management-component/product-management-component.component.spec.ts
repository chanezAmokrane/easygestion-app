import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductManagementComponentComponent } from './product-management-component.component';

describe('ProductManagementComponentComponent', () => {
  let component: ProductManagementComponentComponent;
  let fixture: ComponentFixture<ProductManagementComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProductManagementComponentComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProductManagementComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
