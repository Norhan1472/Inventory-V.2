import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-side-bar',
  templateUrl: './side-bar.component.html',
  styleUrls: ['./side-bar.component.css']
})
export class SideBarComponent {
  constructor(private auth:AuthenticationService,
    private router:Router){}
  logOut(){
    sessionStorage.removeItem("email");
    sessionStorage.removeItem("token");

    this.router.navigate(["/signIn"]);
  }
  isLogin(){
    return this.auth.isLogin();
  }
}
