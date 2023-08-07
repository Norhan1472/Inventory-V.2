import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SystemInfo } from 'src/app/interfaces/system-info';
import { SystemInfoService } from 'src/app/services/system-info.service';

@Component({
  selector: 'app-system-info',
  templateUrl: './system-info.component.html',
  styleUrls: ['./system-info.component.css']
})
export class SystemInfoComponent implements OnInit{
  systemInfos:SystemInfo[]=[];

  constructor(private systemInfoService:SystemInfoService,
              private router :Router,
              ){}

  ngOnInit(): void {
    this.getSystemInfos();
  }
  getSystemInfos(){
    this.systemInfoService.getSystemInfo().subscribe(
      data=>{
        console.log(data);
        this.systemInfos=data;
      },
      error=>{
        console.log(error);
      }
    )
  }

  /*searchProductName(text:any){
    let productName = text.target.value;
    console.log(productName);
    this.systemInfoService.searchProductName(productName).subscribe(
      data=>{
        console.log(data);
        this. = data;
      },
      error=>{
        console.log(error);
      }
    )
  }*/

  /*getAllProducts(){
    this.systemInfoService.getSystemInfos().subscribe(data=>{
      console.log("list All Products");
      console.log(data);
      this.systemInfo=data;
  },
  (error)=>{
    console.log(error);
  }
  );
  }*/
  updateSystemInfo(systemId:any){
    console.log("norhannnnn");
    console.log(systemId);
    this.router.navigate(["updateSystem",systemId]);
  }
  systemInfoDetails(systemId:any){
    this.router.navigate(["systemInfoDetails",systemId]);
  }

  deleteSystemInfo(systemId:any){
    console.log("hellllo");
    console.log(systemId);
    this.systemInfoService.deleteSystemInfo(systemId).subscribe(
      resp=>{
        console.log(resp);
        this.getSystemInfos();
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

    this.systemInfoService.generateReport2().subscribe(
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
