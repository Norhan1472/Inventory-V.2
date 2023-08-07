import { Category } from 'src/app/interfaces/category';
import { CategoryService } from './../../services/category.service';
import { AfterViewInit, Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { BrandService } from 'src/app/services/brand.service';
import { Brand } from 'src/app/interfaces/brand';
import { Router } from '@angular/router';
@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css']
})
export class CategoriesComponent implements OnInit{
  categories:Category[]=[];
  categoryForm :any = FormGroup;
  brands:Brand[]=[];
  ngOnInit(): void {
    this.popup();
    this.getCategories();
    this.dataForm();
    this.getBrands();
  }
  constructor(private categoryService:CategoryService,
              private formBuilder:FormBuilder,
              private brandService:BrandService,
              private router:Router){}

  getCategories(){
    this.categoryService.getCategories().subscribe(
      data=>{
        this.categories = data;
      },
      error=>{
        console.log(error);
      }
    )
  }
  dataForm(){
    this.categoryForm = this.formBuilder.group({
      categoryName:[null,Validators.required],
      brands:this.formBuilder.array([]),
    })
  }
  putData(){

  }
  getBrands(){
    this.brandService.getData().subscribe(
      data=>{
        this.brands=data;
        console.log(data);
      },
      error=>{
        console.log(error);
      }
    )
  }
  onSelected(event:any){
    console.log(event.target.value);
    console.log("hhh");
    const brands = this.categoryForm.controls['brands'] as FormArray;
    if(event.target.checked){
      const brand =this.formBuilder.group({
        'brandId':new FormControl(event.target.value)
      })
      brands.push(brand);

    }else{
      const index = brands.controls.findIndex(x=>x.value===event.target.value);
      brands.removeAt(index);
    }
  }
  submitData(){
    console.log(this.categoryForm.value);
    this.categoryService.addCategory(this.categoryForm.value).subscribe(
      data=>{
        console.log(data);
        this.popup();
        this.getCategories();

      },
      error=>{
        console.log(error);

      }
    )
  }
  popup(){
    const openBtn = document.querySelector('.open-btn');
    const closeBtn = document.querySelector('.close-btn');
    const saveBtn = document.querySelector('.save-btn');
    const popup = document.querySelector('.popup') as HTMLElement;

    openBtn?.addEventListener('click', () => {
      popup.style.display = 'block';

    });

    closeBtn?.addEventListener('click', ()=> {
      popup.style.display = 'none';
    });

    saveBtn?.addEventListener('click', ()=> {
      popup.style.display = 'none';
    });
  }
  getDetails(id:number){
    this.router.navigate(["getCategoryById",id]);
  }
  update(id:number){
    this.router.navigate(["updateCategory",id]);
  }
  delete(id:number){
    this.deleteById(id);
  }
  deleteById(id:number){
    this.categoryService.deleteCategory(id).subscribe(resp=>{
      console.log(resp);
      //reload page from database
      this.reloadPage();
    },
    error=>{
      console.log(error);
    }
    )
  }
  reloadPage(){
    this.getCategories();

  }
}
