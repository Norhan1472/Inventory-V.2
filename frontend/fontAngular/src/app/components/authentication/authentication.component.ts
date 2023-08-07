import { Component, ElementRef, OnInit, Renderer2 } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/interfaces/user';
import { AuthenticationService } from 'src/app/services/authentication.service';


@Component({
  selector: 'app-authentication',
  templateUrl: './authentication.component.html',
  styleUrls: ['./authentication.component.css']
})
export class AuthenticationComponent implements OnInit{
  user:User=new User();
  signUpParentForm! : FormGroup;
  signInParentForm! : FormGroup;

  constructor(
              private signUpChildForm:FormBuilder,
              private signInChildForm:FormBuilder,
              private auth:AuthenticationService,
              private router:Router,
              private renderer: Renderer2,
                 private el: ElementRef) { }


  ngOnInit(): void {
    const sign_in_btn = this.el.nativeElement.querySelector("#sign-in-btn") as HTMLButtonElement;
    const sign_up_btn = this.el.nativeElement.querySelector("#sign-up-btn") as HTMLButtonElement;
    const container = this.el.nativeElement.querySelector(".container") as HTMLElement;

    sign_up_btn.addEventListener("click", () => {
      this.renderer.addClass(container, "sign-up-mode");
    });

    sign_in_btn.addEventListener("click", () => {
      this.renderer.removeClass(container, "sign-up-mode");
    });

    this.signIn();
    this.signUp();

  }

  signUp(){
    this.signUpParentForm=this.signUpChildForm.group({
      user : this.signUpChildForm.group({
          email:[''],
          password:['']
      })
    }
    )
  }
  submitSignUp(){
    console.log("gggggggggg");
    console.log(this.signUpParentForm.controls['user'].value.email);
    this.auth.signUp(this.signUpParentForm.controls['user'].value.email,
    this.signUpParentForm.controls['user'].value.password).subscribe(
      response=>{
        console.log(response);
        this.router.navigateByUrl("signIn");
      },
      error=>{
        console.log(error);
      }
    )

  }
  signIn(){
    this.signInParentForm=this.signInChildForm.group({
      userSignIn : this.signInChildForm.group({
          email:[''],
          password:['']
      })
    }
    )
  }
  userlogin()
  {
    this.auth.signIn(this.signInParentForm.controls['userSignIn'].value.email,
    this.signInParentForm.controls['userSignIn'].value.password).subscribe(
      response=>{
        console.log(response);
        console.log("success")
        this.goToProducts()
      },
      error=>{
        console.log(error);
      }
    )
    //alert(this.signInParentForm.controls["userSignIn"].value.email);
    //alert(this.signInParentForm.controls["userSignIn"].value.password);
    //console.log(this.user)
    //this.userservice.loginUser(this.user).
  //  subscribe(data=>{alert("loginSuccessfullty")},error=>alert("8alat"));

  }
  goToProducts(){
    this.router.navigate(["/dashBoard"]);
  }
}
