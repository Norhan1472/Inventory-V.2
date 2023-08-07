import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Orders } from 'src/app/interfaces/orders';
import { Product } from 'src/app/interfaces/product';
import { OrderService } from 'src/app/services/order.service';

@Component({
  selector: 'app-delete-order',
  templateUrl: './delete-order.component.html',
  styleUrls: ['./delete-order.component.css']
})
export class DeleteOrderComponent implements OnInit{
  order : Orders = new Orders();
  products! : Set<Product> ;
  reason : string= "";
  productId! :number;
   orderId = 0;
  constructor(private orderService:OrderService,
              private route:ActivatedRoute,
              private router:Router){}
  ngOnInit(): void {
     this.orderId = this.route.snapshot.params['id'];
    this.getOrderById();
  }
  getOrderById(){
    this.orderService.getOrderById(this.orderId).subscribe(
      data=>{
        this.order = data;
        this.products = this.order.products;
        console.log(this.order);
        console.log(this.order.products);
      },
      error=>{
        console.log(error);
      }
    )
  }
  deleteOneProduct2(productId:number){
    this.orderService.deleteOneItemOfOrder(this.orderId,productId,this.reason).subscribe(
      data=>{
        console.log(data);
        this.router.navigateByUrl("");
      },
      error=>{
        console.log(error);
      }
    )
  }
  openPopup(productId:number) {
    document.getElementById('popupContainer')!.style.display = 'block';
    //this.updateBrand();
    this.productId = productId;
    console.log("here");
    console.log(productId)


  }

  // Function to close the pop-up
  closePopup() {
    document.getElementById('popupContainer')!.style.display = 'none';
  }

  // Function to update the brand name and close the pop-up
  deleteOneProduct() {
    console.log("jk");
    console.log(this.productId);
    const reason = (document.getElementById('reason') as HTMLInputElement).value;
    // Add your logic here to update the brand name with the newBrandName value
    console.log('Updated brand name:', reason);
    this.orderService.deleteOneItemOfOrder(this.orderId,this.productId,reason).subscribe(
      data=>{
        console.log(data);
        console.log("Success")
        this.router.navigateByUrl("");
      },
      error=>{
        console.log(error);
      }
    )
  }
}
/*
this.brandService.updateBrand(this.brand.brandId,this.brand).subscribe(
    data=>{
      console.log(data);
      this.closePopup();

    },
  error=>{
    console.log(error);
  }

  )
*/
