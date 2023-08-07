import { Injectable } from '@angular/core';
import { environment } from '../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Brand } from '../interfaces/brand';

@Injectable({
  providedIn: 'root'
})
export class BrandService {
  apiURL = environment.brandURL;

  constructor(private httpClient :HttpClient) { }

  getData():Observable<any>{
    return this.httpClient.get<any>(`${this.apiURL}getAllBrands`,{
      headers:new HttpHeaders().set('Content-Type','application/json')
    })
  }
  addBrand(brand:any):Observable<any>{
    return this.httpClient.post<any>(`${this.apiURL}addBrand`,brand,{
      headers:new HttpHeaders().set('Content-Type','application/json')
    });
  }
  deleteBrand(brandId:any):Observable<any>{
    return this.httpClient.delete<any>(`${this.apiURL}deleteBrandById/${brandId}`,{
      headers:new HttpHeaders().set('Content-Type','application/json')
    });
  }
  getBrandByName(brandName:string):Observable<Brand>{
    return this.httpClient.get<Brand>(`${this.apiURL+"getBrandByName/"}${brandName}`);
  }

  updateBrand(id:number,brand:Brand):Observable<Object>{
    return this.httpClient.put(`${this.apiURL+"updateBrand"}/${id}`,brand);
  }
}
