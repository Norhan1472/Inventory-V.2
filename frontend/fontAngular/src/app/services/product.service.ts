import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';
import { Product } from '../interfaces/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  urlApi = environment.productURL;

  constructor(private httpClient:HttpClient) { }

  getProducts():Observable<any>{
    return this.httpClient.get<any>(`${this.urlApi}getALLProducts`,{
      headers : new HttpHeaders().set('Content-Type','application/json')
    })
  }

  searchProductName(productName:any):Observable<any>{
    return this.httpClient.get<any>(`${this.urlApi}searchProductName/${productName}`,{
      headers:new HttpHeaders().set('Content-Type','application/json')
    })
  }
  getActiveProducts():Observable<any>{
    return this.httpClient.get<any>(`${this.urlApi}getAllProductThatActive`,{
      headers:new HttpHeaders().set('Content-Type','application/json')
    })
  }
  getNotActiveProducts(){
    return this.httpClient.get<any>(`${this.urlApi}getNotActiveProducts`,{
      headers:new HttpHeaders().set('Content-Type','application/json')
    })
  }
  deleteProduct(productId:any):Observable<Object>{
    return this.httpClient.delete(`${this.urlApi+"deleteProduct/"}${productId}`);
  }
  updateProduct(productId:any,product:Product):Observable<Object>{
    return this.httpClient.put(`${this.urlApi+"updateProduct/"}${productId}`,product);
  }
  getProductById(productId:any):Observable<Product>{
    return this.httpClient.get<Product>(`${this.urlApi+"getProductById/"}${productId}`);
  }
  getAllProductThatActive():Observable<Product[]>{
    return this.httpClient.get<Product[]>(`${this.urlApi+"getAllProductThatActive"}`);
  }
  getCountOfActiveProducts():Observable<any>{
    return this.httpClient.get<any>(`${this.urlApi}countActiveProducts`);
  }
  getCountNotActiveProoducts():Observable<any>{
    return this.httpClient.get<any>(`${this.urlApi}countNotActiveProduct`);
  }
  getAllBrandNames():Observable<string[]>{
    return this.httpClient.get<string[]>(`${this.urlApi + "getUniqueBrands"}`);
  }
  addProduct(product:Product):Observable<Product>{
    return this.httpClient.post<Product>(`${this.urlApi + "addProduct"}`,product);
  }

  assignCategoryAndBrandToProduct(productSerialNumber:string,categoryId:number,brandId:number):Observable<Object>{
    console.log("ddd");
    console.log(productSerialNumber);
    console.log("ddd");
    return this.httpClient.put(`${this.urlApi+"assignCategoryAndBrandToProduct/"}
                                ${productSerialNumber}/category/${categoryId}/brand/${brandId}`,null);
  }
}
