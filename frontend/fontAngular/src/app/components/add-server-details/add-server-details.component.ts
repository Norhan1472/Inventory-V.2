import { ServerDetailsService } from 'src/app/services/server-details.service';
import { Component } from '@angular/core';
import { ServerDetails } from 'src/app/interfaces/server-details';
import { Router } from '@angular/router';
import { Status } from 'src/app/interfaces/status.enum';

@Component({
  selector: 'app-add-server-details',
  templateUrl: './add-server-details.component.html',
  styleUrls: ['./add-server-details.component.css']
})
export class AddServerDetailsComponent {
  statusData = Status;
  keys=Object.keys;

  server : ServerDetails=new ServerDetails();
  ngOnInit(): void {
  }
  constructor(private serverDetailsService:ServerDetailsService,
              private router:Router
              ){}

  onSelectedStatus(status:any){
    console.log("STATUS");
    console.log(status);
    this.server.statusServer=status;
   // this.server.statusServer=Status.ACTIVE;
    console.log(this.server.statusServer);

  }

  saveServer(){

    this.serverDetailsService.addServerDetails(this.server).subscribe(data=>{
      console.log("nnnn");

      console.log(data);
      console.log("qqqqqqqqqq");


      this.goToAllServers();
     // this.assignBrandAndCategoryToProduct(data.productSerialNumber,this.category.categoryId,this.brand.brandId);
      console.log("Here");
    },
    (error)=>{
      console.log(error);
    }
    );
  }

  onSubmit(){
    console.log(this.server);
    this.saveServer();
  }



  goToAllServers(){
    this.router.navigate(["serverDetails"]);
  }


}
