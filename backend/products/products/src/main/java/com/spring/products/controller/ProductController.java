package com.spring.products.controller;


import com.spring.products.entity.Category;
import com.spring.products.entity.Product;
import com.spring.products.service.CategoryService;
import com.spring.products.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product/v1")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class ProductController {
    private final ProductService productService;
    @PostMapping("addProduct")
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }
    @GetMapping("getALLProducts")
    public List<Product> getALLProducts(){
        return productService.getAllProducts();
    }
    @GetMapping("getProductById/{productSerialNumber}")
    public ResponseEntity<Product> getProductById(@PathVariable("productSerialNumber") String productSerialNumber){
        return ResponseEntity.ok(productService.getProductById(productSerialNumber));
    }
    @PutMapping("updateProduct/{productSerialNumber}")
    public ResponseEntity<Product> updateProduct(@PathVariable("productSerialNumber") String productSerialNumber, @RequestBody Product product){
        return ResponseEntity.ok(productService.updateProduct(productSerialNumber,product));
    }
    @DeleteMapping("deleteProduct/{productSerialNumber}")
    public Boolean deleteProduct(@PathVariable("productSerialNumber") String productSerialNumber){
        return productService.deleteProduct(productSerialNumber);
    }
    @PutMapping("assignCategoryAndBrandToProduct/{productSerialNumber}/category" +
            "/{categoryId}/brand/{brandId}")
    public Product assignCategoryAndBrandToProduct(@PathVariable String productSerialNumber,
                                                   @PathVariable long categoryId,
                                                   @PathVariable long brandId) {
        return productService.assignCategoryAndBrandToProduct(productSerialNumber,categoryId,brandId);
    }
    @GetMapping("getAllProductThatActive")
    public List<Product>getAllProductThatActive(){
        return productService.getAllProductThatActive();
    }
}
