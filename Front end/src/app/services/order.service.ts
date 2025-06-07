import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Iorder } from '../shared/models/order.model';

@Injectable({
  providedIn: 'root'
})
export class OrderService {


  public url = 'http://localhost:8080/orders';

  constructor( private http :HttpClient) { 
  }

  public getOrderList(): Observable<Iorder[]> {
  return this.http.get<Iorder[]>(this.url);
}
public updateOrder(order: Iorder): Observable<any> {
  return this.http.put(`${this.url}/${order.order_id}`, order);
}
public addOrder(order: Iorder): Observable<any> {
  return this.http.post(`${this.url}`, order);
}

public deleteOrder(index: number): Observable<any> {
  return this.http.delete(`${this.url}/${index}`);
}

}
