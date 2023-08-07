import { Product } from 'src/app/interfaces/product';
import { ProductService } from './../../services/product.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ExcelService } from 'src/app/services/excel.service';

@Component({
  selector: 'app-dash-board',
  templateUrl: './dash-board.component.html',
  styleUrls: ['./dash-board.component.css']
})
export class DashBoardComponent implements OnInit{
  products:Product[]=[];
  activeCount:number = 0;
  notActiveCount:number=0;
  constructor(private productService:ProductService,
              private router :Router,
              private excelService:ExcelService){}

  ngOnInit(): void {
    this.getProducts();
    this.activeProducts();
    this.notActiveProducts();
  }
  getProducts(){
    this.productService.getProducts().subscribe(
      data=>{
        console.log(data);
        this.products=data;
      },
      error=>{
        console.log(error);
      }
    )
  }
  isActive(status:any){
    if(status === "NOT_ACTIVE"){
      return false;
    }else{
      return true;
    }
  }
  searchProductName(text:any){
    let productName = text.target.value;
    console.log(productName);
    this.productService.searchProductName(productName).subscribe(
      data=>{
        console.log(data);
        this.products = data;
      },
      error=>{
        console.log(error);
      }
    )
  }
  onSelected(state:any){
    console.log(state);
    if(state==="Not_Active"){
      this.productService.getNotActiveProducts().subscribe(
        data=>{
          this.products = data;
        },error=>{
          console.log(error);
        }
      )
    }else if(state === "Active"){
      console.log(state);
      this.productService.getActiveProducts().subscribe(
        data=>{
          this.products = data;
          console.log(data);
        },error=>{
          console.log(error);
        }
      )
    }
    else if(state === "all"){
      this.getProducts();
    }
  }
  activeProducts(){
    console.log("kk");
    this.productService.getActiveProducts().subscribe(
      data=>{

        this.activeCount = data.length;
      },error=>{
        console.log(error);
      }
    )
  }
  notActiveProducts(){
    this.productService.getNotActiveProducts().subscribe(
      data=>{
        this.notActiveCount = data.length;
      },error=>{
        console.log(error);
      }
    )
  }
  getAllProducts(){
    this.productService.getProducts().subscribe(data=>{
      console.log("list All Products");
      console.log(data);
      this.products=data;
  },
  (error)=>{
    console.log(error);
  }
  );
  }
  updateProduct(productId:any){
    console.log("norhannnnn");
    console.log(productId);
    this.router.navigate(["updateProduct",productId]);
  }
  productDetails(productId:any){
    this.router.navigate(["productDetails",productId]);
  }

  deleteProduct(productId:any){
    console.log("hellllo");
    console.log(productId);
    this.productService.deleteProduct(productId).subscribe(
      resp=>{
        console.log(resp);
        this.getProducts();
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
