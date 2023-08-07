import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Brand } from 'src/app/interfaces/brand';
import { Category } from 'src/app/interfaces/category';
import { Product } from 'src/app/interfaces/product';
import { Status } from 'src/app/interfaces/status.enum';
import { SystemInfo } from 'src/app/interfaces/system-info';
import { BrandService } from 'src/app/services/brand.service';
import { CategoryService } from 'src/app/services/category.service';
import { ProductService } from 'src/app/services/product.service';
import { SystemInfoService } from 'src/app/services/system-info.service';

@Component({
  selector: 'app-update-system',
  templateUrl: './update-system.component.html',
  styleUrls: ['./update-system.component.css']
})
export class UpdateSystemComponent implements OnInit{
  systemId!:number;
  systemInfo:SystemInfo=new SystemInfo();
  form:FormGroup;
  constructor(private routerActive:ActivatedRoute,
              private router:Router,
              private systemService:SystemInfoService,

              private fb:FormBuilder){
                this.form=fb.group({
                  categoryName:new FormControl('')
                })

  }
  ngOnInit(): void {
    this.systemId=this.routerActive.snapshot.params['id'];
    this.getSystemById(this.systemId);

    console.log(this.systemId);
  }
  onSubmit(){

    this.updateSystem();
  }
  updateSystem(){

    this.systemService.updateSystemInfo(this.systemId,this.systemInfo).subscribe(
      resp=>{
      ;
        console.log(resp);
        this.goToAllSystems();

      },
      error=>{
        console.log(error);
      },
    ()=>{
      console.log("Complete");
    }
    )
  }

  getSystemById(systemId:any){
    this.systemService.getSystemInfoById(systemId).subscribe(
      resp=>{
        console.log(resp);
        this.systemInfo=resp;

      },
      error=>{
        console.log(error);
      },
      ()=>{
        console.log("Complete");
      }
    );
  }
  goToAllSystems(){
    this.router.navigate(["system"]);
  }
}
