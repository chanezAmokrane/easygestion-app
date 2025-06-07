import { Component, OnInit } from '@angular/core';
import { AddOrderComponent } from '../add-order/add-order.component';
import { Iorder } from '../../shared/models/order.model';
import { ListProductsService } from '../../services/list-products.service';
import { OrdersListComponent } from '../orders-list/orders-list.component';
import { OrderService } from '../../services/order.service';

@Component({
  selector: 'app-order-management',
  imports: [AddOrderComponent,OrdersListComponent],
  templateUrl: './order-management.component.html',
  styleUrl: './order-management.component.css'
})
export class OrderManagementComponent implements OnInit {
  constructor(private serviceOrder:OrderService, private productService:ListProductsService){

  }
  ordersList: Iorder[] = [];
  public selectedOrderToEdit:Iorder;
  ngOnInit(): void {
      
   this.serviceOrder.getOrderList().subscribe({
      next: (data) => {
        this.ordersList = data;
        console.log('les oders recuperees de la BD:', data);
      },
      error: (err) => {
        console.error('Erreur lors du chargement des commandes', err);
      }
    });

  }


  public OnOrderAdded(order: Iorder) {
  if (order.order_id) {
    //Mise à jour
    const index = this.ordersList.findIndex(p => p.order_id=== order.order_id);
    if (index !== -1) {
      this.serviceOrder.updateOrder(order).subscribe({
        next: () => {
          this.ordersList[index] = order;
          console.log("Commande mise à jour avec succès :", order);
        },
        error: (err) => {
          console.error("Erreur lors de la mise à jour :", err);
        }
      });
    }
  } else {
    // creation
    this.serviceOrder.addOrder(order).subscribe({
      next: (newOrder) => {
        this.ordersList.push(newOrder); // <-- newOrder contient le nouvel orderId
        console.log("Commande ajoutée avec succès :", newOrder);
      },
      error: (err) => {
        console.error("Erreur lors de l'ajout :", err);
      }
    });
  }

  this.selectedOrderToEdit = null;
}

public OnOrderDelete(orderId: number): void {
const orderToDelete = this.ordersList.find(order => order.order_id === orderId);
if (!orderToDelete) return;

this.ordersList = [...this.ordersList.filter(order => order.order_id !== orderId)];


  // Supprimer la commande côté back
  this.serviceOrder.deleteOrder(orderId).subscribe({
    next: () => {
      // Supprimer la commande dans la liste front
      this.ordersList = this.ordersList.filter(order => order.order_id !== orderId);

      // Restaurer le stock du produit
      this.productService.getProduct(orderToDelete.products[0].productId).subscribe({
        next: (product) => {
          const updatedProduct = {
            ...product,
            quantity: product.stockQuantity + Number(orderToDelete.quantity)
          };

          this.productService.updateProduct(updatedProduct).subscribe();
        },
        error: (err) => console.error("Erreur lors de la récupération du produit :", err)
      });
    },
    error: (err) => console.error("Erreur lors de la suppression de la commande :", err)
  });
}



  
  public OnOrderEdit(order:Iorder):void{

this.selectedOrderToEdit=order;
  }


}
