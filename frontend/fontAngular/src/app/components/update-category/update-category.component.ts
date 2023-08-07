import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormArray, FormControl } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Brand } from 'src/app/interfaces/brand';
import { Category } from 'src/app/interfaces/category';
import { BrandService } from 'src/app/services/brand.service';
import { CategoryService } from 'src/app/services/category.service';

@Component({
  selector: 'app-update-category',
  templateUrl: './update-category.component.html',
  styleUrls: ['./update-category.component.css']
})
export class UpdateCategoryComponent implements OnInit{
  form:FormGroup;
  category:Category=new Category();
  brandss!:Brand[];
  id!:number;
  ngOnInit(): void {
   this.id=this.routerActive.snapshot.params['id'];
   this.getDataById();
   this.getAllBrands();
  }
  constructor(private fb:FormBuilder,
              private categoryService:CategoryService,
              private routerActive:ActivatedRoute,
              private brandService:BrandService,
              private router:Router){
      this.form=fb.group({
        selectedBrands:new FormArray([]),
        categoryName: new FormControl('')
      })
  }
  updateCategory(){
    this.categoryService.updateCategory(this.id,this.category).subscribe(resp=>{
      console.log(this.category);
      this.loopFunction();
      this.goToAllCategory();
    },
    (error)=>{
      console.log(error);
    },
    ()=>{
      console.log("Complete");
    }
    )
  }
onSubmit(){
this.updateCategory();
}
assignBrandToCategory(brandId:number){
  this.categoryService.assignBrandToCategory(this.id,brandId).subscribe(resp=>{
    console.log(resp)
  },error=>{
    console.log(error);
  },
  ()=>{
    console.log("Complete");
  }
  )
}
loopFunction(){
  for (let idBrand of this.form.value.selectedBrands){
    this.assignBrandToCategory(idBrand);
    console.log(idBrand);
  }
}
onCheckboxChange(event:any){
const selectedBrands = this.form.controls['selectedBrands'] as FormArray;
if(event.target.checked){
  console.log();
  selectedBrands.push(new FormControl(event.target.value));
}
else{
  const index = selectedBrands.controls
  .findIndex(x => x.value === event.target.value);
  selectedBrands.removeAt(index);
}
}
checkIfExist(brand:Brand):boolean{
  for(let b of this.category.brands){

  console.log(brand.brandName);
  console.log(b.brandName);
    if(b.brandName==brand.brandName){
      console.log("kkkkkkkkkkkkkkkkkk");
      console.log(b.brandName);
      console.log("trueeeeeeee");
      return true;
    }
  }
  console.log("falseee");
  return false;
}
getAllBrands(){
  this.brandService.getData().subscribe(
    resp=>{
      console.log(resp);
      this.brandss=resp;
    },
    error=>{
      console.log(error);
    },
    ()=>{
      console.log("complete");
    }
  )
}
getDataById(){
  this.categoryService.getCategoryById(this.id).subscribe(resp=>{
    console.log(resp);
    this.category=resp;
    console.log(this.category.brands);
  },
  error=>{
    console.log(error);
  },
  ()=>{
    console.log("Complete");
  }
  )
}
goToAllCategory(){
  this.router.navigate(['category']);
}
}
