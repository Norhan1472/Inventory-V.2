package com.spring.products.service.implementation;

import com.spring.products.entity.Brand;
import com.spring.products.entity.Category;
import com.spring.products.entity.Product;
//import com.spring.products.exception.ProductNotFoundException;
import com.spring.products.exception.ProductNotFoundException;
import com.spring.products.repository.BrandRepo;
import com.spring.products.repository.CategoryRepo;
import com.spring.products.repository.ProductRepo;
import com.spring.products.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.Boolean.TRUE;


@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;
    private final BrandRepo brandRepo;
    @Override
    public Product addProduct(Product product) {
        return productRepo.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product updateProduct(String productSerialNumber,Product product) {
        Product checkexsitProduct = getProductById(productSerialNumber);
        checkexsitProduct.setProductName(product.getProductName());
        checkexsitProduct.setType(product.getType());
        checkexsitProduct.setModel(product.getModel());
        checkexsitProduct.setStatus(product.getStatus());
        checkexsitProduct.setSpecification(product.getSpecification());
        checkexsitProduct.setBrand(product.getBrand());
        checkexsitProduct.setCategory(product.getCategory());
        productRepo.save(checkexsitProduct);
        return checkexsitProduct;
    }

    @Override
    public Product getProductById(String productSerialNumber) {
        return productRepo.findById(productSerialNumber).orElseThrow(()->new ProductNotFoundException("this product is not exist with this productSerialNumber : "+productSerialNumber));
    }

    @Override
    public Boolean deleteProduct(String productSerialNumber) {
         productRepo.deleteById(productSerialNumber);
        return TRUE;
    }

    @Override
    public Product assignCategoryAndBrandToProduct(String productSerialNumber, long categoryId, long brandId) {
        Product product1 = productRepo.findById(productSerialNumber).get();
        System.out.println(product1);
        Category category1 = categoryRepo.findById(categoryId).get();
        Brand brand1 = brandRepo.findById(brandId).get();
        product1.setBrand(brand1);
        product1.setCategory(category1);
        return productRepo.save(product1);
    }

    @Override
    public List<Product> getAllProductThatActive() {
        return productRepo.getAllProductThatActive();
    }

}
