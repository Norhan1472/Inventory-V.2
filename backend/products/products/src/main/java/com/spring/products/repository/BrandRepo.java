package com.spring.products.repository;

import com.spring.products.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepo extends JpaRepository<Brand,Long> {
    List<Brand> findAllByBrandId(long brandId);
    Brand findByBrandName(String brandName);
    // to handle upper case and lower case
    @Query("from Brand b where b.brandName Like :brandName")
    public List<Brand> checkExistenceBrand(@Param("brandName")String brandName);
}
