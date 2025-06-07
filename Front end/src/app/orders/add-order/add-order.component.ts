import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, OnChanges, OnInit, Output, output, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Iproduct } from '../../shared/models/product.model';
import { ProductListComponent } from '../../product/product-list/product-list.component';
import { ListProductsService } from '../../services/list-products.service';
import { Iorder } from '../../shared/models/order.model';

@Component({
  selector: 'app-add-order',
  imports: [CommonModule, ReactiveFormsModule,FormsModule],
  templateUrl: './add-order.component.html',
  styleUrl: './add-order.component.css'
})
export class AddOrderComponent implements OnInit,OnChanges {
public productList:Iproduct[];
  public formAddOrder:FormGroup;
  public invalidForm:boolean=false;
  public invalidQuantity: boolean=false;
@Output() orderAdded= new EventEmitter();
@Input() orderToEdit: Iorder;

  constructor(private fb:FormBuilder , private productServices:ListProductsService){
    this.formAddOrder=this.fb.group({  
      clientName:['',Validators.required],
     productId: ['', Validators.required], 
      quantity:['',Validators.required],
      date:['',Validators.required]      

    })
  }

ngOnChanges(changes: SimpleChanges): void {

    if (changes['orderToEdit'] && this.orderToEdit) {
    const productId = this.orderToEdit.products?.[0]?.productId ?? '';

    this.formAddOrder.patchValue({
      clientName: this.orderToEdit.clientName,
      date: this.orderToEdit.date,
      quantity: this.orderToEdit.quantity,
      productId: productId
    });
  }
}
  ngOnInit(): void {
    //recuperer la list des produits 
     this.productServices.getProductList().subscribe({
      next: (data) => {
        this.productList = data;
      },
      error: (err) => {
        console.error('Erreur lors du chargement des produits', err);
      }
    });

    

  }

public onSubmit(): void {
    if (this.formAddOrder.invalid) {
      this.invalidForm = true;
      this.formAddOrder.markAllAsTouched();
      console.log("Formulaire invalide");
      return;
    }

    const productId = Number(this.formAddOrder.get('productId')?.value);
    const quantityDemandee = this.formAddOrder.get('quantity')?.value;
    const productSelected = this.productList.find(p => p.productId === productId);

    if (!productSelected) {
      console.error("Produit introuvable");
      this.invalidQuantity = true;
      return;
    }

    const stock = productSelected.stockQuantity;
    let finalStock = stock;

    if (this.orderToEdit) {
    const ancienneQuantite = this.orderToEdit['quantity'];

      const difference = quantityDemandee - ancienneQuantite;
      finalStock = stock - difference;

      if (finalStock < 0) {
        this.invalidQuantity = true;
        return;
      }
    } else {
      finalStock = stock - quantityDemandee;
      if (finalStock < 0) {
        this.invalidQuantity = true;
        return;
      }
    }

    const updatedProduct = {
      ...productSelected,
      quantity: finalStock
    };

    this.productServices.updateProduct(updatedProduct).subscribe({
      next: () => {
        this.invalidForm = false;
        this.invalidQuantity = false;

        const orderFormValue = this.formAddOrder.value;
const orderToEmit = {
  clientName: orderFormValue.clientName,
  quantity: Number(orderFormValue.quantity),
  date: orderFormValue.date,
  productIds: [productSelected.productId]
};



        if (this.orderToEdit) {
          const updatedOrder = { ...this.orderToEdit, ...orderToEmit };
          this.orderAdded.emit(updatedOrder);
          this.orderToEdit = null;
          console.log('MODIFICATION', updatedOrder);
        } else {
          this.orderAdded.emit(orderToEmit);
          console.log('AJOUT', orderToEmit);
        }

        this.formAddOrder.reset();
      },
      error: (err) => {
        console.error('Erreur lors de la mise à jour du produit', err);
      }
    });
  }


}
