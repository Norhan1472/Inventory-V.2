import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';
import { Product } from '../interfaces/product';
import { SystemInfo } from '../interfaces/system-info';

@Injectable({
  providedIn: 'root'
})
export class SystemInfoService {
  urlApi = environment.systemURL;

  constructor(private httpClient:HttpClient) { }

  getSystemInfo():Observable<any>{
    return this.httpClient.get<any>(`${this.urlApi}getAllSystemInfo`,{
      headers : new HttpHeaders().set('Content-Type','application/json')
    })
  }

  searchSystemInfoName(productName:any):Observable<any>{
    return this.httpClient.get<any>(`${this.urlApi}searchProductName/${productName}`,{
      headers:new HttpHeaders().set('Content-Type','application/json')
    })
  }

  deleteSystemInfo(systemId:any):Observable<Object>{
    return this.httpClient.delete(`${this.urlApi+"deleteSystemInfoById/"}${systemId}`);
  }
  updateSystemInfo(systemId:any,systemInfo:SystemInfo):Observable<Object>{
    return this.httpClient.put(`${this.urlApi+"updateSystemInfo/"}${systemId}`,systemInfo);
  }
  getSystemInfoById(systemId:any):Observable<SystemInfo>{
    return this.httpClient.get<SystemInfo>(`${this.urlApi+"getSystemInfoById/"}${systemId}`);
  }

  addSystemInfo(systemInfo:SystemInfo):Observable<Product>{
    return this.httpClient.post<Product>(`${this.urlApi + "addSystemInfo"}`,systemInfo);
  }
  generateReport2():Observable<any>{

    return this.httpClient.get( `${this.urlApi}generateReport`, { responseType: 'arraybuffer' })
     }
}
