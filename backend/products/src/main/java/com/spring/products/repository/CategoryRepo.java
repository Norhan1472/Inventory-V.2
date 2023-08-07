package com.spring.products.repository;

import com.spring.products.entity.Brand;
import com.spring.products.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {
    List<Category> findAllByCategoryId(Long categoryId);
    Category findByCategoryName(String categoryName);
    @Query("from Category c where c.categoryName Like :categoryName")
    public List<Category> checkExistenceCategory(@Param("categoryName")String categoryName);
}
