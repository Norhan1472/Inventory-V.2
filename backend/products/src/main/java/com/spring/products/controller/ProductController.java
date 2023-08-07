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
@RequestMapping("api/product/v3")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class ProductController {
    private final ProductService productService;

    //api/product/v1/addProduct
    @PostMapping("addProduct")
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }
    //api/product/v1/getALLProducts
    @GetMapping(value = "getALLProducts",produces = "application/json")
    public List<Product> getALLProducts(){
        return productService.getAllProducts();
    }
    //api/product/v1/getProductById/{productId}
    @GetMapping("getProductById/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable("productId") long productId){
        return ResponseEntity.ok(productService.getProductById(productId));
    }

    @PutMapping("updateProduct/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable("productId") long productId, @RequestBody Product product){
        return ResponseEntity.ok(productService.updateProduct(productId,product));
    }
    @DeleteMapping("deleteProduct/{productId}")
    public Boolean deleteProduct(@PathVariable("productId") long productId){
        return productService.deleteProduct(productId);
    }
    @PutMapping("assignCategoryAndBrandToProduct/{productId}/category" +
            "/{categoryId}/brand/{brandId}")
    public Product assignCategoryAndBrandToProduct(@PathVariable long productId,
                                                   @PathVariable long categoryId,
                                                   @PathVariable long brandId) {
        return productService.assignCategoryAndBrandToProduct(productId,categoryId,brandId);
    }
    @GetMapping("getAllProductThatActive")
    public List<Product>getAllProductThatActive(){
        return productService.getAllProductThatActive();
    }
    //api/product/v1/countNotActiveProduct
    @GetMapping("countNotActiveProduct")
    public int getCountOfNotActiveProduct(){
        return productService.countNotActiveProducts();
    }
    @GetMapping("countActiveProducts")
    public int getCountActiveProducts(){
        return productService.countActiveProducts();
    }
    //api/product/v1/searchProductName/{productName}
    @GetMapping("searchProductName/{productName}")
    public List<Product>searchProductName(@PathVariable String productName){
        return productService.searchProductName(productName);
    }
    @GetMapping("getNotActiveProducts")
    public List<Product>getNotActiveProducts(){
        return productService.getNotActiveProducts();
    }
}
