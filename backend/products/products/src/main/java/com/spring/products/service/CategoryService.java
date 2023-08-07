package com.spring.products.service;

import com.spring.products.entity.Category;

import java.util.List;

public interface CategoryService {
    public Category addCategory(Category category);
    public List<Category> getAllCategory();
    public Category updateCategory(long id,Category category);
    public Category getCategoryById(long id);
    public Boolean deleteCategory(long id);
    public Boolean checkExistenceCategory(String brandName);
    public Category updateBrandOfCategory(long categoryId,long brandId);
    public Category assignBrandToCategory(Long categoryId, Long brandId);
    public Category getCategoryByName(String categoryName);
}
