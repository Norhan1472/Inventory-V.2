import { Brand } from "./brand";
import { Category } from "./category";
import { Status } from "./status.enum";

export class Product {
  productId! : number;
  productName! : string;
  type! : string;
  model!:string;
  specification!:string;
  status!:Status;
  serialNumber!:string;
  brand! : Brand;
  category! : Category;

  constructor(productName: string, brand: Brand, category: Category) {
      this.productName = productName;
      this.brand = brand;
      this.category = category;
    }
}
