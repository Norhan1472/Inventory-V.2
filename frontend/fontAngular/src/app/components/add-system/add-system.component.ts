import { ServerDetails } from './../../interfaces/server-details';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Brand } from 'src/app/interfaces/brand';
import { Category } from 'src/app/interfaces/category';
import { Product } from 'src/app/interfaces/product';
import { Status } from 'src/app/interfaces/status.enum';
import { SystemInfo } from 'src/app/interfaces/system-info';
import { BrandService } from 'src/app/services/brand.service';
import { CategoryService } from 'src/app/services/category.service';
import { SystemInfoService } from 'src/app/services/system-info.service';

@Component({
  selector: 'app-add-system',
  templateUrl: './add-system.component.html',
  styleUrls: ['./add-system.component.css']
})
export class AddSystemComponent {

  systemInfo : SystemInfo=new SystemInfo();
  systemInfoTest:SystemInfo=new SystemInfo();
  ngOnInit(): void {

  }
  constructor(private systemInfoService:SystemInfoService,
              private router:Router){}

  saveSystem(){
    console.log("kk");
console.log(this.systemInfo);
    this.systemInfoService.addSystemInfo(this.systemInfo).subscribe(data=>{

      console.log(data);
      console.log("qqqqqqqqqq");

      this.goToAllSystems();

    },
    (error)=>{
      console.log(error);
    }
    );
  }

  onSubmit(){
    this.saveSystem();
  }



  goToAllSystems(){
    this.router.navigate(["system"]);
  }

}
