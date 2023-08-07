import { Brand } from 'src/app/interfaces/brand';
import { Component, OnInit, Renderer2 } from '@angular/core';
import { FormArray, FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Orders } from 'src/app/interfaces/orders';
import { Product } from 'src/app/interfaces/product';
import { OrderService } from 'src/app/services/order.service';
import { ProductService } from 'src/app/services/product.service';
import { Category } from 'src/app/interfaces/category';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit{
  orders!:Orders[];
  products!:Product[];
  orderId!:number;
  ngOnInit(): void {
    this.getAllOrders();
    this.myFunction();
    this.closing();
  }
  constructor(private orderService:OrderService,
              private router:Router,
              private renderer:Renderer2){
  }
  getAllOrders(){
    this.orderService.getAllOrders().subscribe(
      data=>{
        console.log(data);
        this.orders=data;
      },
      error=>{
        console.log(error);
      },
      ()=>{
        console.log("Complete");
      }
    )
  }

  getOrderDetails(orderId:number){
    this.router.navigate(["orderDetails",orderId]);
  }
  /*addOrder(){
    this.router.navigate(["addOrder"]);
  }*/
  deleteOrder(orderId:number){
    this.router.navigateByUrl(`deleteOrder/${orderId}`);
    /*this.orderService.deleteOrderById(orderId).subscribe(
      response=>{
        console.log(response);
        this.refreshPage();
      },
      error=>{
        console.log(error);
      }
    )*/
  }
  refreshPage(){
    this.orderService.getAllOrders().subscribe(
      response=>{
        console.log(response);
        this.orders=response;
      },
      error=>{
        console.log(error);
      }
    )
  }
  onSelected(state:any){
    console.log(state);
    /*if(state==="Not_Active"){
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
    }*/
  }

   myFunction() {
    document.getElementById("myDropdown")!.classList.toggle("show");

  }

  // Close the dropdown menu if the user clicks outside of it

  closing(){
    this.renderer.listen('window', 'click', (event: Event) => {
      if (!(event.target as HTMLElement).matches('.dropbtn')) {
        const dropdowns = document.getElementsByClassName('dropdown-content');
        for (let i = 0; i < dropdowns.length; i++) {
          const openDropdown = dropdowns[i];
          if (openDropdown.classList.contains('show')) {
            this.renderer.removeClass(openDropdown, 'show');
          }
        }
      }
    });
  }
  deleteWholeOrder2(orderId:number){
    let reason ="kkk";
    this.orderService.deleteOrderById(orderId,reason).subscribe(
      data=>{
        console.log(data);
      },
      error=>{
        console.log(error);
      }
    )

  }
  openPopup(orderId:number) {
    this.orderId = orderId;
    document.getElementById('popupContainer')!.style.display = 'block';
    //this.updateBrand();
    console.log(orderId)


  }

  // Function to close the pop-up
  closePopup() {
    document.getElementById('popupContainer')!.style.display = 'none';
  }

  // Function to update the brand name and close the pop-up
  deleteWholeOrder() {
    const reason = (document.getElementById('reason') as HTMLInputElement).value;
    // Add your logic here to update the brand name with the newBrandName value
    console.log('Updated brand name:', reason);
  this.orderService.deleteOrderById(this.orderId,reason).subscribe(
    data=>{
      console.log(data);
      this.refreshPage();
      this.closePopup();

    },
  error=>{
    console.log(error);
  }

  )
  }
  generateReport(orderId:number){

      this.orderService.generateReport2(orderId).subscribe(
        (data) => {
          const blob = new Blob([data], { type: 'application/pdf' });
        const url = window.URL.createObjectURL(blob);
        const link = document.createElement('a');
        link.href = url;
        link.download = 'report.pdf';
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
        },error => {
          console.log(error);
        }

      )
  }
}
