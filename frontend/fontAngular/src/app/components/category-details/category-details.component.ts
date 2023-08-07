import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Category } from 'src/app/interfaces/category';
import { CategoryService } from 'src/app/services/category.service';

@Component({
  selector: 'app-category-details',
  templateUrl: './category-details.component.html',
  styleUrls: ['./category-details.component.css']
})
export class CategoryDetailsComponent implements OnInit{
  id!:number;
  category:Category=new Category();
  ngOnInit(): void {

    this.id=this.routerActive.snapshot.params['id'];
    console.log("hello");
    console.log(this.id);
    this.getCategoryDetails();
  }
  constructor(private routerActive:ActivatedRoute,
              private categoryService:CategoryService){

  }
  getCategoryDetails(){
    return this.categoryService.getCategoryById(this.id).subscribe(
      resp=>{
        console.log(resp);
        this.category=resp;
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
