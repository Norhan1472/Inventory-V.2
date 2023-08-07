import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { environment } from '../environments/environment';
import { Category } from '../interfaces/category';

@Injectable({
  providedIn: 'root'
})
export class CategoryService{
  urlApi = environment.categoryURL;

  constructor(private httpClient:HttpClient) { }

  getCategories():Observable<any>{
    return this.httpClient.get<any>(`${this.urlApi}getALLCategory`,{
      headers : new HttpHeaders().set('Content-Type','application/json')
    })
  }

  addCategory(category:any):Observable<any>{
    return this.httpClient.post<any>(`${this.urlApi}addCategory`,category,{
      headers:new HttpHeaders().set('Content-Type','application/json')
    })
  }
  getCategoryById(id:number):Observable<Category>{
    return this.httpClient.get<Category>(`${this.urlApi+"getCategoryById"}/${id}`);
  }
  assignBrandToCategory(categoryId:number,brandId:number):Observable<Object>{//,brandId:number//categoryId:number
    return this.httpClient.
    put(`${this.urlApi+"assignBrandToCategory"}/${categoryId}/brand/${brandId}`,null);
  }
  deleteCategory(id:number):Observable<Object>{
    return this.httpClient.delete(`${this.urlApi+"deleteCategory/"}${id}`);
  }
  updateCategory(id:number,category:Category):Observable<Object>{
    return this.httpClient.put(`${this.urlApi+"updateCategory/"}${id}`,category)
  }
  getCategoryByName(categoryName:string):Observable<Category>{
    return this.httpClient.get<Category>(`${this.urlApi+"getCategoryByName/"}${categoryName}`);
  }

}
