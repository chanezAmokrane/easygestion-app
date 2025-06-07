import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map, of, switchMap } from 'rxjs';
import { Iproduct } from '../shared/models/product.model';


@Injectable({
  providedIn: 'root'
})
export class ListProductsService {
public url = 'http://localhost:8080/products';


  constructor( private http :HttpClient) { 
  }
public addProduct(product: Iproduct): Observable<Iproduct> {
  return this.http.post<Iproduct>(this.url, product);
}

  public getProductList(): Observable<Iproduct[]> {
  return this.http.get<Iproduct[]>(this.url);
}

// recupere un seul produit 
public getProduct(id: number): Observable<Iproduct | undefined> {
  return this.http.get<Iproduct[]>(this.url).pipe(
    map(products => products.find(p => p.productId === id))
  );
}
public deleteProduct(index: number): Observable<any> {
  return this.http.delete(`${this.url}/${index}`);
}

public updateProduct(product: Iproduct): Observable<Iproduct> {
  return this.http.put<Iproduct>(`${this.url}/${product.productId}`, product);
}



}
