import { Component, OnInit } from '@angular/core';
import { AddProductComponent } from '../add-product/add-product.component';
import { ProductListComponent } from '../product-list/product-list.component';

import { HttpClientModule } from '@angular/common/http';
import { ListProductsService } from '../../services/list-products.service';
import { Iproduct } from '../../shared/models/product.model';
@Component({
  selector: 'app-product-management-component',
   standalone: true,
  imports: [AddProductComponent,ProductListComponent,HttpClientModule],
  templateUrl: './product-management-component.component.html',
  styleUrls: ['./product-management-component.component.css']
})
export class ProductManagementComponentComponent implements OnInit {
  


constructor(private productService:ListProductsService){

}

productList: Iproduct[] = [];
public selectedProductToEdit:Iproduct;
ngOnInit(): void {

   this.productService.getProductList().subscribe({
      next: (data) => {
        this.productList = data;
        console.log('Produits recuperees :', data);
      },
      error: (err) => {
        console.error('Erreur lors du chargement des produits', err);
      }
    });
}

onProductAdded(product: Iproduct) {
  const index = this.productList.findIndex(p => p.productId === product.productId);

  if (index !== -1) {
    //  mis a jour en en BD
    this.productService.updateProduct(product).subscribe({
      next: (updatedProduct) => {
        this.productList[index] = updatedProduct;
        console.log("Produit mis à jour :", updatedProduct);
      },
      error: err => console.error("Erreur lors de la mise à jour", err)
    });
  } else {
    // Ajout en BD
    this.productService.addProduct(product).subscribe({
      next: (newProduct) => {
        this.productList.push(newProduct);
        console.log("Produit ajouté :", newProduct);
      },
      error: err => console.error("Erreur lors de l'ajout", err)
    });
  }

  this.selectedProductToEdit = null;
}


onProductEdit(product:Iproduct){
  this.selectedProductToEdit=product;  
  
}
}
