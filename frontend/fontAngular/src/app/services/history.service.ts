import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { History } from 'src/app/interfaces/history';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class HistoryService {
  baseLink = environment.historyURL;

  constructor(private http:HttpClient) { }

  getAllHistories():Observable<History[]>{
    return this.http.get<History[]>(`${this.baseLink}getAllHistories`).pipe(
      map(
        res=>{
          return res
        }
      )
    );
  }
  generatePdf():Observable<any>{
    return this.http.get<any>(`${this.baseLink}export-to-pdf`);
  }
  findByAnyData(value:any):Observable<History[]>{
    return this.http.get<History[]>(`${this.baseLink}getByAnyThing/value/${value}`);
  }
}
