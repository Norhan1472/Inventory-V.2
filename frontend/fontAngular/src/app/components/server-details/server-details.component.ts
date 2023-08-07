import { ServerDetails } from './../../interfaces/server-details';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Product } from 'src/app/interfaces/product';
import { ExcelService } from 'src/app/services/excel.service';
import { ProductService } from 'src/app/services/product.service';
import { ServerDetailsService } from 'src/app/services/server-details.service';

@Component({
  selector: 'app-server-details',
  templateUrl: './server-details.component.html',
  styleUrls: ['./server-details.component.css']
})
export class ServerDetailsComponent implements OnInit{
  serverDetails:ServerDetails[]=[];

  constructor(private serverDetailsService:ServerDetailsService,
              private router :Router){}

  ngOnInit(): void {
    this.getServerDetails();
  }
  getServerDetails(){
    this.serverDetailsService.getserverDetails().subscribe(
      data=>{
        console.log(data);
        this.serverDetails=data;
      },
      error=>{
        console.log(error);
      }
    )
  }
  isActive(status:any){
    if(status === "NOT_ACTIVE"){
      return false;
    }else{
      return true;
    }
  }
  /*searchProductName(text:any){
    let productName = text.target.value;
    console.log(productName);
    this.productService.searchProductName(productName).subscribe(
      data=>{
        console.log(data);
        this.products = data;
      },
      error=>{
        console.log(error);
      }
    )
  }*/
  onSelected(state:any){
    console.log(state);
    if(state==="Not_Active"){
      this.serverDetailsService.getNotActiveServerDetails().subscribe(
        data=>{
          this.serverDetails = data;
        },error=>{
          console.log(error);
        }
      )
    }else if(state === "Active"){
      console.log(state);
      this.serverDetailsService.getActiveServerDetails().subscribe(
        data=>{
          this.serverDetails = data;
          console.log(data);
        },error=>{
          console.log(error);
        }
      )
    }
    else if(state === "all"){
      this.getServerDetails();
    }
  }

  updateServerDetails(serverId:any){
    console.log("norhannnnn");
    console.log(serverId);
    this.router.navigate(["updateServerDetails",serverId]);
  }
  serverDataDetails(serverId:any){
    this.router.navigate(["serverDataDetails",serverId]);
  }

  deleteServerDetails(serverId:any){
    console.log("hellllo");
    console.log(serverId);
    this.serverDetailsService.deleteServerDetails(serverId).subscribe(
      resp=>{
        console.log(resp);
        this.getServerDetails();
      },
      error=>{
        console.log(error);
      },
      ()=>{
        console.log("Complete");
      }
    )
  }
  generateReport(){

    this.serverDetailsService.generateReport2().subscribe(
      (data) => {
        const blob = new Blob([data], { type: 'application/pdf' });
      const url = window.URL.createObjectURL(blob);
      const link = document.createElement('a');
      link.href = url;
      link.download = 'report.pdf';
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
      },error => {
        console.log(error);
      }

    )
}
}
