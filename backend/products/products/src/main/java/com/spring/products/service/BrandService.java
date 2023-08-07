package com.spring.products.service;

import com.spring.products.entity.Brand;

import java.util.List;

public interface BrandService {
    public Brand addBrand(Brand brand);
    public Brand updateBrand(long id ,Brand brand);
    public Boolean deleteBrand(long id);
    public List<Brand>getAllBrands();
    public Brand getBrandById(long id);
    public Boolean checkExistenceBrand(String brandName);
    public Brand getBrandByName(String brandName);

}
