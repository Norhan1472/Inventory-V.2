import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ServerDetails } from 'src/app/interfaces/server-details';
import { CategoryService } from 'src/app/services/category.service';
import { ServerDetailsService } from 'src/app/services/server-details.service';

@Component({
  selector: 'app-server-details-data',
  templateUrl: './server-details-data.component.html',
  styleUrls: ['./server-details-data.component.css']
})
export class ServerDetailsDataComponent implements OnInit{
  id!:number;
  server:ServerDetails=new ServerDetails();
  ngOnInit(): void {

    this.id=this.routerActive.snapshot.params['id'];
    console.log("hello");
    console.log(this.id);
    this.getServerDetails();
  }
  constructor(private routerActive:ActivatedRoute,
              private serverDetailsService:ServerDetailsService){

  }
  getServerDetails(){
    return this.serverDetailsService.getServerDetailsById(this.id).subscribe(
      resp=>{
        console.log(resp);
        this.server=resp;
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
