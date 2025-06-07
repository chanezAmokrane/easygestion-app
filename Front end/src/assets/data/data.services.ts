import { Injectable } from '@angular/core';
import { InMemoryDbService } from 'angular-in-memory-web-api';
@Injectable({
  providedIn: 'root'
})
export class DataService implements InMemoryDbService {
  constructor() { }
  createDb() {
    return {
      products:[
  {
    "id": 0,
    "label": "HairShampoo",
    "type": "haircare",
    "price": 25.99,
    "quantity": 10
  },
  {
    "id": 1,
    "label": "CottonFaceMask",
    "type": "skincare",
    "price": 14.5,
    "quantity": 20
  },
  {
    "id": 2,
    "label": "LiquidFoundation",
    "type": "makeup",
    "price": 32.0,
    "quantity": 5
  },
  {
    "id": 3,
    "label": "BodyLotion",
    "type": "bodycare",
    "price": 6.75,
    "quantity": 50
  }
], orders: [
   {
  id: 0,
  clientName: 'Chanez',
  productId: 0,
  productLabel: 'HairShampoo',
  quantity: 2,
  date: '2025-05-20'
},
{
  id: 1,
  clientName: 'Ines',
  productId: 1,
  productLabel: 'CottonFaceMask',
  quantity: 3,
  date: '2025-05-21'
}

    ]
    }
}
}