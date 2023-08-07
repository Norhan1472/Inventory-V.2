import { Category } from "./category";

export class Brand {
  brandId!:number;
  brandName!:string;
  category!: Set<Category>;// = new Set<Category>();
}
