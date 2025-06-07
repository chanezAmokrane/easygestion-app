import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AddProductComponent } from '../product/add-product/add-product.component';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  imports: [AddProductComponent, RouterModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {

}
