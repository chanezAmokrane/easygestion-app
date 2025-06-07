import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, OnChanges, OnInit, Output, output, SimpleChanges } from '@angular/core';
import { ListProductsService } from '../../services/list-products.service';
import { HttpClientModule } from '@angular/common/http';
import { Iproduct } from '../../shared/models/product.model';
@Component({
  selector: 'app-product-list',
  imports: [CommonModule,HttpClientModule],
  templateUrl: './product-list.component.html',
  styleUrl: './product-list.component.css'
})
export class ProductListComponent implements OnChanges, OnInit {
@Input() products: any[] = [];
@Output() productEdit=new EventEmitter();
public selectedProduct;
constructor( private productService:ListProductsService){

}

ngOnInit(): void {  
}

ngOnChanges(changes: SimpleChanges) {
  console.log('Données reçues dans ProductListComponent :', this.products);
}

public deleteProduct(productId: number): void {
  const index = this.products.findIndex(p => p.productId === productId);
  if (index !== -1) {
    this.products.splice(index, 1);
    this.productService.deleteProduct(productId).subscribe(() => {
      console.log("Produit supprimé. Nouvelle liste :", this.products);
    });
  }
}


public editProduct(index: number): void {
  console.log("edit le product");
  this.productService.getProduct(index).subscribe(product => {
    this.selectedProduct = product;

    console.log('le produit selectionné est ', this.selectedProduct);
    this.productEdit.emit(this.selectedProduct);
  });

  console.log("la liste des produits", this.products);
}

}
