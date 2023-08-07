import { Component, OnInit, Renderer2 } from '@angular/core';
import { FormGroup, FormArray, FormControl } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Brand } from 'src/app/interfaces/brand';
import { Category } from 'src/app/interfaces/category';
import { Orders } from 'src/app/interfaces/orders';
import { Product } from 'src/app/interfaces/product';
import { OrderService } from 'src/app/services/order.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-add-order-product',
  templateUrl: './add-order-product.component.html',
  styleUrls: ['./add-order-product.component.css']
})
export class AddOrderProductComponent implements OnInit{
  orders : Orders = new Orders();
  form!: FormGroup;
  product: Product[] = [];
  products!:Product[];
  productDetails:Product = new Product("",new Brand(),new Category());
  ngOnInit() {
    this.getAllProductThatActive();
    this.product.push(new Product("",new Brand(),new Category()));
    this.form = new FormGroup({
      product: new FormArray(
        this.product.map(p => {
          return new FormGroup({
            productName: new FormControl(p.productName),
            brand: new FormControl(p.brand),
            category: new FormControl(p.category)
          });
        })
      )
    });
  }
  constructor(private productService:ProductService,
             private orderService:OrderService,
             private router:Router,
            private route : ActivatedRoute){

  }

  get productArray(): FormArray {
    return this.form.get('product') as FormArray;
  }

  addProduct() {
    console.log("hellllllloooooo");
    this.productArray.push(
      new FormGroup({
        productName: new FormControl(''),
        brand: new FormControl(''),
        category: new FormControl('')
      })
    ); }

    onSelectedProduct(product:any,index:number){
      console.log("productsss");
      console.log(product.target.value);
      this.getProductById(product.target.value,index);

    }
    getAllProductThatActive(){
      this.productService.getAllProductThatActive().subscribe(
        data=>{
          console.log(data);
          this.products=data;

        },
        error=>{
          console.log(error);
        },
        ()=>{
          console.log("Complete");
        }
      )
    }
    getCategoryAndBrandFromDataBase(index:number){
      const data = this.getProductDetails();
      console.log("kkkk");
      console.log(data.category);
      this.productArray.controls[index].get('category')?.setValue(data.category);
      this.productArray.controls[index].get('brand')?.setValue(data.brand);

    }

    getProductDetails(){
      return {
        productName: (this.productDetails.productName),
        brand:  (this.productDetails.brand.brandName),
        category: (this.productDetails.category.categoryName)
      }
    }
    getProductById(productId:any,index:number){
      this.productService.getProductById(productId).subscribe(
        data=>{
          console.log(data);
          this.productDetails=data;
         this.getCategoryAndBrandFromDataBase(index);
                },
        error=>{
          console.log(error);
        },
        ()=>{
          console.log("Complete");
        }
      )
    }
    assignOrderWithProduct(orderId:number,productSerialNumber:string){
      console.log("productSerialNumber");
      console.log(productSerialNumber);
      this.orderService.assignOrderWithProduct(orderId,productSerialNumber).subscribe(
        response=>{
          console.log(response);
        },
        error=>{
          console.log(error);
        }
      )
    }
    loopOnProductArrray(orderId:number){
      for(let prod of this.productArray.value){
        this.assignOrderWithProduct(orderId,prod.productName)
      }
      this.rollBackToAllOrders();
    }
    rollBackToAllOrders(){
      this.router.navigate(["orders"]);
    }
  onSubmit(){
    const orderId=this.route.snapshot.params['id'];
    console.log(this.productArray.value);
    this.orderService.getOrderById(orderId).subscribe(
      data=>{
        console.log(data);
        console.log("xxxxxxxxxxxxx1234");
        console.log(this.productArray.value);
        this.loopOnProductArrray(orderId);
      },
      error=>{
        console.log(error);
      }
    )
  }
}
