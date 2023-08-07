import { Injectable } from '@angular/core';
import { environment } from '../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Orders } from '../interfaces/orders';
import { Product } from '../interfaces/product';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  urlApi = environment.orderURL;

  constructor(private httpClient:HttpClient) { }
  getAllOrders():Observable<Orders[]>{
    return this.httpClient.get<Orders[]>(`${this.urlApi+"getAllOrders"}`);
  }
  getAllProductsOfOrder(orderId:number):Observable<Product[]>{
    return this.httpClient.get<Product[]>(`${this.urlApi+"getAllProductsOfOrder/"}${orderId}`);
  }
  getOrderById(orderId:number):Observable<Orders>{
    return this.httpClient.get<Orders>(`${this.urlApi+"getOrderById/"}${orderId}`);
  }
  addOrder(order:Orders):Observable<Orders>{
    return this.httpClient.post<Orders>(`${this.urlApi+"addOrder"}`,order);
  }
  //assignOrderWithProduct/{orderId}/product/{productSerialNumber}
  assignOrderWithProduct(orderId:number,productSerialNumber:string):Observable<Orders>{
    return this.httpClient.put<Orders>(`${this.urlApi}assignOrderWithProduct/${orderId}/product/${productSerialNumber}`,null);
  }
  deleteOrderById(id:number,reasonReturn:string):Observable<Boolean>{
    return this.httpClient.delete<Boolean>(`${this.urlApi}deleteOrderById/${id}/reason/${reasonReturn}`);
  }
  //deleteOneItemOfOrder/order/{orderId}/product/{productId}/reason/{reason}
  deleteOneItemOfOrder(orderId:number,productId:number,reason:string):Observable<Boolean>{
    return this.httpClient.delete<Boolean>(`${this.urlApi}deleteOneItemOfOrder/order/${orderId}/product/${productId}/reason/${reason}`)
  }
  addProductsToOrder(orderId:number,product:Product[]):Observable<Orders>{
    return this.httpClient.put<Orders>(`${this.urlApi}addProductToItem/order/${orderId}`,product);
  }
  updateEmployeeData(orderId:number,order:Orders):Observable<Orders>{
   return this.httpClient.put<Orders>(`${this.urlApi}updateOrder/${orderId}`,order);

  }
  generateReport2(id:any):Observable<any>{

    return this.httpClient.get( `${this.urlApi}generateReport/${id}`, { responseType: 'arraybuffer' })
     }
}
