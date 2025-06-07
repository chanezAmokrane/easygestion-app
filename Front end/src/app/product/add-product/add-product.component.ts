import { Component, EventEmitter, Input, OnChanges, OnInit, Output, output, SimpleChanges } from '@angular/core';

import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { ProductListComponent } from '../product-list/product-list.component';
import { HttpClientModule } from '@angular/common/http';
import { ListProductsService } from '../../services/list-products.service';

@Component({
  selector: 'app-add-product',
  imports: [ReactiveFormsModule, CommonModule, RouterModule, ProductListComponent,HttpClientModule],
  templateUrl: './add-product.component.html',
  styleUrl: './add-product.component.css'
})
export class AddProductComponent implements OnChanges {

  @Output() productAdded= new EventEmitter<any>();
  @Input() productToEdit: { [key: string]: any; } ;
public formAddProduct:FormGroup;
public invalidForm :boolean=false;
constructor(private fb:FormBuilder, private ProductService:ListProductsService){
this.formAddProduct = this.fb.group({
  productId: [null],
  label: ['', [
    Validators.required,
    Validators.minLength(3)
  ]],
  type: ['', Validators.required],
  price: ['', [
    Validators.required,
    Validators.pattern('^[0-9]+(\\.[0-9]{1,2})?$')
  ]],
  stockQuantity: ['', Validators.required]
});

}

ngOnChanges(changes: SimpleChanges): void {
  if (changes['productToEdit'] && this.productToEdit) {
       console.log("Produit reçu pour édition :", this.productToEdit);  
    this.formAddProduct.patchValue(this.productToEdit);
  }

}

onSubmit(): void {
  if (this.formAddProduct.invalid) {
    this.invalidForm = true;
    this.formAddProduct.markAllAsTouched();  
    return;
  }

  this.invalidForm = false;
  const product = this.formAddProduct.value;
  console.log("la valeur de formulaire", product);

  if (product.label && product.type && product.price && product.stockQuantity) {
    console.log("la condition est true ");
    if (this.productToEdit) {
      
      const updatedProduct = { ...this.productToEdit, ...product };
      this.productAdded.emit(updatedProduct);
      this.productToEdit = null; 
    } else {
      this.productAdded.emit(product);
    }

    this.formAddProduct.reset();
  }
}



}
