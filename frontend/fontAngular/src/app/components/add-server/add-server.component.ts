import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Observable, BehaviorSubject, map, startWith, catchError, of } from 'rxjs';
import { DataState } from 'src/app/enum/dataState.enum';
import { StatusServer } from 'src/app/enum/statusServer.enum';
import { AppState } from 'src/app/interfaces/appState';
import { CustomResponse } from 'src/app/interfaces/custom-response';
import { ServerService } from 'src/app/services/server.service';

@Component({
  selector: 'app-add-server',
  templateUrl: './add-server.component.html',
  styleUrls: ['./add-server.component.css']
})
export class AddServerComponent implements OnInit{
  constructor(private serverService:ServerService){}
  ngOnInit(): void {
  }
  saveServer(server:NgForm):void{
    console.log("kkkk");
    console.log(server.value);


    this.serverService.AddNewServer$(server.value).subscribe(
      response=>{
        console.log("jjj");

      },error=>{
        console.log(error);
      }
    )
   /* .pipe(
      map(

      ),

    )*/
  }


}
/**
 *  serversData$ : Observable<AppState<CustomResponse>> | undefined;
  readonly DataState = DataState;
  readonly StatusServer = StatusServer;
  private filterSubject = new BehaviorSubject<string>('');
  private dataSubject = new BehaviorSubject<CustomResponse>({
    message: '',
    developerMessage: '',
    httpStatus: '',
    timeStamp: new Date(),
    statusCode: 0,
    reason: '',
    data: {
      Servers: [],
      server: undefined,
    },
  });
  filterStatus$ = this.filterSubject.asObservable();
  private isLoading = new BehaviorSubject<boolean>(false);
  isLoading$ = this.isLoading.asObservable();

  constructor(private serverService:ServerService){
  }

  ngOnInit(): void {
    this.serversData$=this.serverService.getAllServers$
  .pipe(
    map(
      response=>{
        console.log(response);
        this.dataSubject.next(response) ;
        return { dataState: DataState.LOADED_STATE, appData : response}
      }
    ),
    startWith({
      dataState: DataState.LOADING_STATE}
    ),
    catchError((error:string)=>{
      console.log(error);
      return of( {dataState: DataState.ERROR_STATE, error })
    })
  );
  }

  pingServer(ipAddress:string):void{
    this.filterSubject.next(ipAddress);
    this.serversData$=this.serverService.ping$(ipAddress)
    .pipe(
      map(
        response=>{
          console.log("kkk");
          console.log(response.data.server!.id);
          let index = this.dataSubject.value.data.Servers!.findIndex(server => server.id === response.data.server!.id) || 0;
          console.log("kkk");
          //console.log(index);
          this.dataSubject.value.data.Servers![index] = response.data.server!;
          this.filterSubject.next('');
          return {dataState:DataState.LOADED_STATE,appData:this.dataSubject.value}
        }
      ),
      startWith({dataState:DataState.LOADED_STATE,appData:this.dataSubject.value}),
      catchError((error:string)=>{
        console.log(error);
        this.filterSubject.next('');
        return of({dataState:DataState.ERROR_STATE,error})
      }
      )
      )
  }

  filterServers(statusServer:StatusServer):void{
    this.serversData$=this.serverService.filter$(statusServer,this.dataSubject.value)
    .pipe(
      map(response=>{
        return {dataState:DataState.LOADED_STATE,appData:response}
      }
      ),
      startWith({dataState:DataState.LOADED_STATE,appData:this.dataSubject.value}),
      catchError((error:string)=>{
        return of({dataState:DataState.ERROR_STATE,error})
      })
    )
  }
  saveServer(server:NgForm):void{
    console.log("kkkk");
    console.log(server.value);
    this.isLoading.next(false);

    this.serversData$=this.serverService.AddNewServer$(server.value)
    .pipe(
      map(
        response=>{
          console.log("jjj");
          this.dataSubject.next(
            {
              ...response,
              data:{Servers:[...this.dataSubject.value.data.Servers??[], response.data.server!]}
            }
          )
          document.getElementById("closeModal")!.click();
          this.isLoading.next(false);
          server.resetForm({statusServer:this.StatusServer.SERVER_DOWN});
          return ({dataState: DataState.LOADED_STATE,appData: this.dataSubject.value })
        }
      ),
      startWith({dataState:DataState.LOADED_STATE,appData:this.dataSubject.value}),
      catchError((error:string)=>{
        this.isLoading.next(false);
        return of({dataState:DataState.ERROR_STATE,error})
      })
    )
  }


  deleteServer(id:number):void{
    console.log(id);
    this.serversData$=this.serverService.deleteServer$(id)
    .pipe(
      map(
        response=>{
          console.log("hhello");
          console.log(response.data.server);
         // const index = this.dataSubject.value.data.Servers.findIndex(server=>server.id === id);
          //console.log(index);
          this.dataSubject.next(
            {
              ...response,
              data:{Servers:this.dataSubject.value.data.Servers!.filter(
                s=>s.id !== id
              )}
            }
          )
          return {dataState:DataState.LOADED_STATE,appData:this.dataSubject.value}
        }
      ),
      startWith({dataState:DataState.LOADED_STATE,appData:this.dataSubject.value}),
      catchError((error:string)=>{
        return of({dataState:DataState.ERROR_STATE,error})
      })
    )
  }
  printReport(){
   // window.print();
    let dataType = 'application/vnd.ms-excel.sheet.macroEnabled.12';
    let tableSelect = document.getElementById('servers');
    let tableHtml = tableSelect!.outerHTML.replace(/ /g,'%20');
    let downloadLink = document.createElement('a');
    document.body.appendChild(downloadLink);
    downloadLink.href = 'data:' + dataType +',' +tableHtml;
    downloadLink.download = 'server-report.xls';
    downloadLink.click();
    document.body.removeChild(downloadLink);
  }
 */
