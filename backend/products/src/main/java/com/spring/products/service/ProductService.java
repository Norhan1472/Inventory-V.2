package com.spring.products.service;

import com.spring.products.entity.Category;
import com.spring.products.entity.Product;

import java.util.List;

public interface ProductService {
    public Product addProduct(Product product);
    public List<Product> getAllProducts();
    public Product updateProduct(long productId,Product product);
    public Product getProductById(long productId);
    public Boolean deleteProduct(long productId);
    //public List<String>getUniqueBrands();
    public Product assignCategoryAndBrandToProduct(long productId,long categoryId,long brandId);
    public List<Product>getAllProductThatActive();
    public int countNotActiveProducts();
    public int countActiveProducts();
    public List<Product> searchProductName(String productName);

    public List<Product> getNotActiveProducts();
}
