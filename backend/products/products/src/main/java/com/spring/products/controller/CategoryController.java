package com.spring.products.controller;

import com.spring.products.entity.Category;
import com.spring.products.exception.CategoryNotFoundException;
import com.spring.products.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/category/v2")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class CategoryController {
    private final CategoryService categoryService;
    @PostMapping("addCategory")
    public ResponseEntity<Category> addCategory(@RequestBody Category category){
         Category category1 = categoryService.addCategory(category);
         //<Category>(HttpStatus.CREATED);
        return ResponseEntity.ok(category1);
    }
    @GetMapping("getALLCategory")
    public List<Category> getALLCategory(){
        return categoryService.getAllCategory();
    }
    @GetMapping("getCategoryById/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") long id)throws CategoryNotFoundException {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }
    @PutMapping("updateCategory/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") long id, @RequestBody Category category){
        return ResponseEntity.ok(categoryService.updateCategory(id,category));
    }
    @DeleteMapping("deleteCategory/{id}")
    public ResponseEntity<Boolean>  deleteCategory(@PathVariable("id") long id){
        return ResponseEntity.ok(categoryService.deleteCategory(id));
    }
    @PutMapping("/assignBrandToCategory/{categoryId}/brand/{brandId}")
    public Category assignBrandToCategory(
            @PathVariable Long categoryId,
            @PathVariable Long brandId
    ){
        return categoryService.assignBrandToCategory(categoryId, brandId);
    }
    @GetMapping("getCategoryByName/{categoryName}")
    public Category getCategoryByName(@PathVariable String categoryName){
        return categoryService.getCategoryByName(categoryName);
    }
}