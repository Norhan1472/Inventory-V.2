import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Brand } from 'src/app/interfaces/brand';
import { Category } from 'src/app/interfaces/category';
import { Product } from 'src/app/interfaces/product';
import { Status } from 'src/app/interfaces/status.enum';
import { BrandService } from 'src/app/services/brand.service';
import { CategoryService } from 'src/app/services/category.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent {
  statusData = Status;
  keys=Object.keys;
  brandNames!:Set<Brand>;
  brand!:Brand;
  categorysList!:Category[];
  category!:Category;
  products : Product=new Product("",new Brand(),new Category());
  productTest:Product=new Product("",new Brand(),new Category());
  ngOnInit(): void {
    this.getAllCategorys();

  }
  constructor(private productService:ProductService,
              private router:Router,
              private brandService:BrandService,
              private categoryService:CategoryService){}
  getAllCategorys(){
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
      })
  }
  onSelectedStatus(status:any){
    console.log("STATUS");
    console.log(status);
    this.products.status=status;
    console.log(this.products.status);
  }
  onSelectedCategory(category:string){
    console.log(category);
    this.categoryService.getCategoryByName(category).subscribe(
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
  saveProduct(){
    this.products.category=this.category;
    this.products.brand=this.brand;
    console.log(this.products.category);
    console.log(this.products.brand);
    this.productService.addProduct(this.products).subscribe(data=>{
      console.log("nnnn");
      console.log(this.products.serialNumber);
      console.log(data);
      console.log("qqqqqqqqqq");
      console.log(data.serialNumber);
      console.log(this.category.categoryId);
      console.log(this.brand.brandId);
      console.log();

      this.goToAllProducts();
     // this.assignBrandAndCategoryToProduct(data.productSerialNumber,this.category.categoryId,this.brand.brandId);
      console.log("Here");
      console.log(this.brand.brandId);
    },
    (error)=>{
      console.log(error);
    }
    );
  }

  onSubmit(){
    this.saveProduct();
  }

  onSelectedBrand(value:string):void {
    console.log(value);

    this.getBrandByName(value);

	}

  goToAllProducts(){
    this.router.navigate(["dashBoard"]);
  }

  getAllBrandsOfCategory(){
   this.brandNames=this.category.brands;
   console.log(this.brandNames);
  }

  getBrandByName(brandName:string){
    this.brandService.getBrandByName(brandName).subscribe(
      resp=>{
        console.log(resp);
        this.brand=resp;
      },
      error=>{
        console.log(error);
      },
      ()=>{
        console.log("Complete");
      }
    )
  }
}
