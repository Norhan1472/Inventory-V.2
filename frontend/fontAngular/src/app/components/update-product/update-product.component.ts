import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Brand } from 'src/app/interfaces/brand';
import { Category } from 'src/app/interfaces/category';
import { Product } from 'src/app/interfaces/product';
import { Status } from 'src/app/interfaces/status.enum';
import { BrandService } from 'src/app/services/brand.service';
import { CategoryService } from 'src/app/services/category.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrls: ['./update-product.component.css']
})
export class UpdateProductComponent implements OnInit{
  keys= Object.keys;
  statusData = Status;
  productSerialNumber!:string;
  products:Product=new Product('',new Brand(),new Category());
  categorysList!:Category[];
  brandNames!:Set<Brand>;
  category:Category=new Category();
  form:FormGroup;
  selectedBrand:Brand=new Brand();
  brand:Brand=new Brand();
  constructor(private routerActive:ActivatedRoute,
              private router:Router,
              private productService:ProductService,
              private categoryService:CategoryService,
              private brandService:BrandService,
              private fb:FormBuilder){
                this.form=fb.group({
                  categoryName:new FormControl('')
                })

  }
  ngOnInit(): void {
    this.productSerialNumber=this.routerActive.snapshot.params['id'];
    this.getProductById(this.productSerialNumber);

    console.log(this.productSerialNumber);
  }
  onSubmit(){
    console.log("Product data");
    console.log(this.products.productName);
    console.log(this.products.serialNumber);
    this.updateProduct();
  }
  updateProduct(){
    this.products.category=this.category;
    this.products.brand=this.brand;
    this.productService.updateProduct(this.products.productId,this.products).subscribe(
      resp=>{
        console.log("hhhhhhhhh");
        console.log(this.category);
        console.log(this.brand);
        console.log(resp);
        this.goToAllProducts();

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
    this.products.status=status;

  }
  onSelectedCategory(category:any){
    console.log("jjjjjjjj");
    console.log(category);
    console.log(category.target.value);
     this.categoryService.getCategoryByName(category.target.value).subscribe(
      resp=>{
        console.log(resp);
        this.category=resp;
        this.getAllBrandsOfCategory();
      },
      error=>{
        console.log(error);
      },
      ()=>{
        console.log("Complete");
      }
    )

  }
  onSelectedBrand(brand:any){
    console.log(brand);

    this.getBrandByName(brand.target.value);
  }
  getBrandByName(brandName:string){
    this.brandService.getBrandByName(brandName).subscribe(
      resp=>{
        console.log(resp);
        this.brand=resp;
        console.log("hhhhhhhppppp");
      },
      error=>{
        console.log(error);
      },
      ()=>{
        console.log("Complete");
      }
    )
  }
  getProductById(productId:any){
    this.productService.getProductById(productId).subscribe(
      resp=>{
        console.log(resp);
        this.products=resp;
        this.category=this.products.category;
        console.log("jjiiuuyyttt");
        console.log(this.products.brand);
        this.brand=this.products.brand;
        this.getAllCategory();
        this.getAllBrandsOfCategory();
    console.log("category of product");
    console.log(this.products.category.categoryName);
      },
      error=>{
        console.log(error);
      },
      ()=>{
        console.log("Complete");
      }
    );
  }
  getAllCategory(){
    this.categoryService.getCategories().subscribe(
      resp=>{

        console.log(resp);
        this.categorysList=resp;

      },
      error=>{
        console.log(error);
      },
      ()=>{
        console.log("Complete");
      }
    )
  }
  getAllBrandsOfCategory(){
    console.log("ggggggguuuu");
    this.selectedBrand=this.products.brand;
    this.brandNames=this.category.brands;
    console.log("PPPPPPPPPPP");
    console.log(this.brandNames);
  }
  goToAllProducts(){
    this.router.navigate(["getALLProducts"]);
  }
}
