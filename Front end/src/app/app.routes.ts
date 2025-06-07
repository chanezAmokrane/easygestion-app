import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AddProductComponent } from './product/add-product/add-product.component';
import { ProductListComponent } from './product/product-list/product-list.component';
import { AddOrderComponent } from './orders/add-order/add-order.component';
import { ProductManagementComponentComponent } from './product/product-management-component/product-management-component.component';
import { OrderManagementComponent } from './orders/order-management/order-management.component';



export const routes: Routes = [
  { path: "", component: LoginComponent },
  {
    path: "dashBoard",
    component: DashboardComponent,
    children: [
      { path: "addProduct", component: ProductManagementComponentComponent },
       { path: "addOrder", component: OrderManagementComponent },

    ]
  }
];

