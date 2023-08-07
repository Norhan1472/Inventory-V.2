package com.spring.products.service;

import com.spring.products.entity.Category;
import com.spring.products.entity.Product;

import java.util.List;

public interface ProductService {
    public Product addProduct(Product product);
    public List<Product> getAllProducts();
    public Product updateProduct(String productSerialNumber,Product product);
    public Product getProductById(String productSerialNumber);
    public Boolean deleteProduct(String productSerialNumber);
    //public List<String>getUniqueBrands();
    public Product assignCategoryAndBrandToProduct(String productSerialNumber,long categoryId,long brandId);
    public List<Product>getAllProductThatActive();
}
