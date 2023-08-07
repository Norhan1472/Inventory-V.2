import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Brand } from 'src/app/interfaces/brand';
import { BrandService } from 'src/app/services/brand.service';
import { UpdateBrandComponent } from '../update-brand/update-brand.component';

@Component({
  selector: 'app-brands',
  templateUrl: './brands.component.html',
  styleUrls: ['./brands.component.css']
})
export class BrandsComponent implements OnInit {
  formBrand :any= FormGroup;
  brands:Brand[]=[];
  brand:Brand = new Brand();
  ngOnInit(): void {
    this.getAllBrands();
    this.brandFormData();
  }
constructor(private brandService:BrandService,
            private formBuilder:FormBuilder,
            private router:Router,
            private dialog:MatDialog
            ) {
}
getAllBrands(){
  this.brandService.getData().subscribe(
    data=>{
      this.brands = data;
      console.log(data);
    },
    error=>{
      console.log(error);
    }
  )
}
brandFormData(){
  this.formBrand = this.formBuilder.group({
    brandName:[null,Validators.required]
  });
}

submitData(){
  var formData = this.formBrand.value;
  var data = {
    brandName : formData.brandName
  };
this.brandService.addBrand(data).subscribe(
  data=>{
    console.log(data);
    this.refreshData();
  },error=>{
    console.log(error);
  }
)
}
refreshData(){
  this.getAllBrands();
  this.brandFormData();
}
deleteBrand(brandId:number){
  this.brandService.deleteBrand(brandId).subscribe(
    data=>{
      console.log(data);
      this.refreshData();
    },
    error=>{
      console.log(error);
    }
  )
}
openPopup(brandId:number) {
  this.brand.brandId = brandId;
  document.getElementById('popupContainer')!.style.display = 'block';
  //this.updateBrand();
  console.log(brandId)


}

// Function to close the pop-up
closePopup() {
  document.getElementById('popupContainer')!.style.display = 'none';
}

// Function to update the brand name and close the pop-up
updateBrand() {
  const newBrandName = (document.getElementById('brandInput') as HTMLInputElement).value;
  // Add your logic here to update the brand name with the newBrandName value
  console.log('Updated brand name:', newBrandName);
  this.brand.brandName=newBrandName;
this.brandService.updateBrand(this.brand.brandId,this.brand).subscribe(
  data=>{
    console.log(data);
    this.refreshData();
    this.closePopup();

  },
error=>{
  console.log(error);
}

)
}

}
