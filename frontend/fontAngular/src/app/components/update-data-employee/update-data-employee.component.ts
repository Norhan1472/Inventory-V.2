import { ActivatedRoute, Router } from '@angular/router';
import { Orders } from 'src/app/interfaces/orders';
import { Component, OnInit } from '@angular/core';
import { OrderService } from 'src/app/services/order.service';

@Component({
  selector: 'app-update-data-employee',
  templateUrl: './update-data-employee.component.html',
  styleUrls: ['./update-data-employee.component.css']
})
export class UpdateDataEmployeeComponent implements OnInit{
  order:Orders = new Orders();
constructor(private orderService:OrderService,
            private route:ActivatedRoute,
            private router:Router){}
  ngOnInit(): void {
    this.getOrderById();
  }
  onSubmit(){
    const orderId=this.route.snapshot.params['id'];
this.orderService.updateEmployeeData(orderId,this.order).subscribe(
  data=>{
    console.log(data);
    this.router.navigateByUrl('/orders');
  },
  error=>{
    console.log(error);
  }
)
  }
getOrderById(){
 const orderId=this.route.snapshot.params['id'];
  this.orderService.getOrderById(orderId).subscribe(
      data=>{
        console.log(data);
        this.order=data;
       // this.products = this.orders.products;
       // this.getAllProductsOfOrder();
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
