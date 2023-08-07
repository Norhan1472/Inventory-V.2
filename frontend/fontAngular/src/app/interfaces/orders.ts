import { Product } from "./product";

export class Orders {
  orderId!:number;
  empName!:string;
  nationalId!:number;
  email!:string;
  description!:string;
  createdDate!:Date;
  products!:Set<Product>;
}
