import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';
import { Product } from '../interfaces/product';
import { SystemInfo } from '../interfaces/system-info';
import { ServerDetails } from '../interfaces/server-details';

@Injectable({
  providedIn: 'root'
})
export class ServerDetailsService {
  urlApi = environment.serverDetailsURL;

  constructor(private httpClient:HttpClient) { }

  getserverDetails():Observable<any>{
    return this.httpClient.get<any>(`${this.urlApi}getAllServerDetails`,{
      headers : new HttpHeaders().set('Content-Type','application/json')
    })
  }

  searchServerDetails(productName:any):Observable<any>{
    return this.httpClient.get<any>(`${this.urlApi}searchProductName/${productName}`,{
      headers:new HttpHeaders().set('Content-Type','application/json')
    })
  }

  deleteServerDetails(serverId:any):Observable<Object>{
    return this.httpClient.delete(`${this.urlApi+"deleteServerDetailsById/"}${serverId}`);
  }
  updateServerDetails(serverId:any,serverDetails:ServerDetails):Observable<Object>{
    return this.httpClient.put(`${this.urlApi+"updateServerDetails/"}${serverId}`,serverDetails);
  }
  getServerDetailsById(serverId:any):Observable<ServerDetails>{
    return this.httpClient.get<ServerDetails>(`${this.urlApi+"getServerDetailsById/"}${serverId}`);
  }

  addServerDetails(serverDetails:ServerDetails):Observable<Product>{
    return this.httpClient.post<Product>(`${this.urlApi + "addServerDetails"}`,serverDetails);
  }

  getActiveServerDetails():Observable<any>{
    return this.httpClient.get<any>(`${this.urlApi}getAllServersThatActive`,{
      headers : new HttpHeaders().set('Content-Type','application/json')
    })
  }
  getNotActiveServerDetails():Observable<any>{
    return this.httpClient.get<any>(`${this.urlApi}getNotActiveServers`,{
      headers : new HttpHeaders().set('Content-Type','application/json')
    })
  }
  generateReport2():Observable<any>{

    return this.httpClient.get( `${this.urlApi}generateReport`, { responseType: 'arraybuffer' })
     }
}
