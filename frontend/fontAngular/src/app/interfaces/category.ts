import { Brand } from "./brand";

export class Category {
    categoryId!: number;
    categoryName!: string;
    brands!: Set<Brand>;
}
