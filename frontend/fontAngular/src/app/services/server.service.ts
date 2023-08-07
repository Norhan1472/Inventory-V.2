import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap, catchError, throwError } from 'rxjs';
import { StatusServer } from '../enum/statusServer.enum';
import { CustomResponse } from '../interfaces/custom-response';
import { Server } from '../interfaces/server';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ServerService {
  private readonly baseLink= environment.serverURL;

  constructor(private httpClient:HttpClient) { }

  getAllServers$=<Observable<CustomResponse>>
  this.httpClient.get<CustomResponse>(`${this.baseLink+"getAllServers"}`)
  .pipe(
    tap(console.log),
    catchError(this.handleError)
  )
  getAllServersUp$=<Observable<CustomResponse>>
  this.httpClient.get<CustomResponse>(`${this.baseLink+"getAllServersUp"}`)
  .pipe(
    tap(console.log),
    catchError(this.handleError)
  )
  getAllServersDown$=<Observable<CustomResponse>>
  this.httpClient.get<CustomResponse>(`${this.baseLink+"getAllServersDown"}`)
  .pipe(
    tap(console.log),
    catchError(this.handleError)
  )

  AddNewServer$=(server : Server)=><Observable<CustomResponse>>
  this.httpClient.post<CustomResponse>(`${this.baseLink+"AddNewServer"}`,server)
  .pipe(
    tap(console.log),
    catchError(this.handleError)
  )
  getServerById$ = (id:number)=><Observable<CustomResponse>>
  this.httpClient.get<CustomResponse>(`${this.baseLink+"getServerById"}/${id}`)
  .pipe(
    tap(console.log),
    catchError(this.handleError)
  )

  deleteServer$ = (id:number)=><Observable<CustomResponse>>
  this.httpClient.delete<CustomResponse>(`${this.baseLink+"deleteById"}/${id}`)
  .pipe(
    tap(console.log),
    catchError(this.handleError)
  )

  ping$ = (ipAddress:string)=><Observable<CustomResponse>>
  this.httpClient.get<CustomResponse>(`${this.baseLink+"ping"}/${ipAddress}`)
  .pipe(
    tap(console.log),
    catchError(this.handleError)
  )

 /* $filter= (statusServer:StatusServer,response:CustomResponse)=> <Observable<CustomResponse>>
  new Observable<CustomResponse>(
    subscriber=>{
      console.log(response);
      subscriber.next(
        statusServer===StatusServer.ALL?
        {
          ...response,
          message:`Servers filtered by ${statusServer} status`
      }:
      {
          ...response,
          message:response.data.servers
          .filter(servers=>servers.statusServer===statusServer).length > 0?
          `servers filtered by ${statusServer===StatusServer.SERVER_DOWN?'SERVER DOWN':'SERVER UP'}`:
          `No Server Available of type ${statusServer}`,
          data:{
            servers:response.data.servers
          .filter(servers=>servers.statusServer===statusServer)
        }
      }
      );
      subscriber.complete();
    }
  )*/
  filter$=(statusServer:StatusServer,response:CustomResponse)=><Observable<CustomResponse>>
  new Observable<CustomResponse>(
    subscriber=>{
      console.log(response);
      subscriber.next(
        statusServer===StatusServer.ALL?{...response,message:`servers filtered by ${statusServer} servers`}:
        {
          ...response,
          message:response.data.Servers!
          .filter(servers=>servers.statusServer===statusServer).length>0?`servers filtered by
          ${statusServer===StatusServer.SERVER_DOWN?'Server Down':'Server Up'} server`:`No Data of this ${statusServer} found`,
          data:{
            Servers:response.data.Servers!
            .filter(servers=>servers.statusServer===statusServer)
          }
        }
      );
    }
  )

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(`An error occured - Error code : ${error.status}`);
  }
}
