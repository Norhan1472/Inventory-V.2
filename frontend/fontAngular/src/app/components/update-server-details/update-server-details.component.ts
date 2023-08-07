import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Brand } from 'src/app/interfaces/brand';
import { Category } from 'src/app/interfaces/category';
import { Product } from 'src/app/interfaces/product';
import { ServerDetails } from 'src/app/interfaces/server-details';
import { Status } from 'src/app/interfaces/status.enum';
import { BrandService } from 'src/app/services/brand.service';
import { CategoryService } from 'src/app/services/category.service';
import { ProductService } from 'src/app/services/product.service';
import { ServerDetailsService } from 'src/app/services/server-details.service';

@Component({
  selector: 'app-update-server-details',
  templateUrl: './update-server-details.component.html',
  styleUrls: ['./update-server-details.component.css']
})
export class UpdateServerDetailsComponent implements OnInit{
  keys= Object.keys;
  statusData = Status;
  serverId!:number;
  server:ServerDetails=new ServerDetails();

  form:FormGroup;

  constructor(private routerActive:ActivatedRoute,
              private router:Router,
              private serverService:ServerDetailsService,
              private fb:FormBuilder){
                this.form=fb.group({
                  categoryName:new FormControl('')
                })

  }
  ngOnInit(): void {
    this.serverId=this.routerActive.snapshot.params['id'];
    this.getServerById(this.serverId);

    console.log(this.serverId);
  }
  onSubmit(){
    this.updateServer();
  }
  updateServer(){
    this.serverService.updateServerDetails(this.serverId,this.server).subscribe(
      resp=>{
        console.log("hhhhhhhhh");

        console.log(resp);
        this.goToAllServers();

      },
      error=>{
        console.log(error);
      },
    ()=>{
      console.log("Complete");
    }
    )
  }
  onSelectedStatus(status:any){
    this.server.statusServer=status;

  }

  getServerById(serverId:any){
    this.serverService.getServerDetailsById(serverId).subscribe(
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
    );
  }

  goToAllServers(){
    this.router.navigate(["serverDetails"]);
  }
}
