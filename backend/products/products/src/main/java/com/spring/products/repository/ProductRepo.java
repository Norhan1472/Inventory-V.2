package com.spring.products.repository;

import com.spring.products.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,String> {
    List<Product>findAllByProductSerialNumber(String productSerialNumber);
    //@Query("Select distinct b.brand from Product b")
    //List<String> getUniqueBrands();
    @Query("from Product p where p.status = 0")
    List<Product>getAllProductThatActive();
}
