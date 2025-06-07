import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Iorder } from '../../shared/models/order.model';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListProductsService } from '../../services/list-products.service';
import { OrderService } from '../../services/order.service';
@Component({
  selector: 'app-orders-list',
  imports: [CommonModule],
  templateUrl: './orders-list.component.html',
  styleUrl: './orders-list.component.css'
})
export class OrdersListComponent {
@Input() orders :Iorder[]=[];
@Output() orderEdited = new EventEmitter<Iorder>();

constructor(private productService:ListProductsService, private orderService:OrderService){

}


@Output() orderDeleted = new EventEmitter<number>();

public deleteOrder(orderId: number): void {
  
  this.orderDeleted.emit(orderId);
}

public editOrder(orderId: number): void {
  console.log("le orderId à éditer :", orderId);

  const orderSelected = this.orders.find(o => o.order_id === orderId);

  if (orderSelected) {
    console.log("le order à émettre pour le edit :", orderSelected);
    this.orderEdited.emit(orderSelected);
  } else {
    console.error("Aucune commande trouvée avec l'id :", orderId);
  }
}

}
