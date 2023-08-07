package com.spring.products.controller;

import ch.qos.logback.classic.spi.IThrowableProxy;
import com.spring.products.entity.Brand;
import com.spring.products.exception.BrandAlreadyExist;
import com.spring.products.service.BrandService;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/brand/v3")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class BrandController {
    private final BrandService brandService;
    //api/brand/v3/addBrand
    @PostMapping("addBrand")
    public ResponseEntity<Brand> addBrand(@RequestBody Brand brand)throws BrandAlreadyExist{
        brand.setBrandName(brand.getBrandName().toLowerCase());
        brandService.addBrand(brand);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    @GetMapping("getAllBrands")
    public List<Brand> getAllBrands(){
        return brandService.getAllBrands();
    }
    @GetMapping("getBrandById/{id}")
    public ResponseEntity<Brand> getBrandById(@PathVariable long id){
        return ResponseEntity.ok(brandService.getBrandById(id));
    }
    @DeleteMapping("deleteBrandById/{id}")
    public ResponseEntity<Boolean> deleteBrandById(@PathVariable long id){
        return ResponseEntity.ok(brandService.deleteBrand(id));
    }
    @PutMapping("updateBrand/{id}")
    public ResponseEntity<Brand> updateBrand(@PathVariable long id,@RequestBody Brand brand){
        return ResponseEntity.ok(brandService.updateBrand(id,brand));
    }
    @GetMapping("getBrandByName/{brandName}")
    public Brand getBrandByName(@PathVariable String brandName){
        return brandService.getBrandByName(brandName);
    }
}
