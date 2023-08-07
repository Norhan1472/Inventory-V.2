import { Injectable } from '@angular/core';
import { AuthenticationService } from './authentication.service';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HttpInterceptorAuthService implements HttpInterceptor{

  constructor(private auth:AuthenticationService) { }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
   req = req.clone({
    setHeaders:{
      Authorization : this.auth.getToken()?.toString()||""
    }
   }

   )
   return next.handle(req);
  }
}
