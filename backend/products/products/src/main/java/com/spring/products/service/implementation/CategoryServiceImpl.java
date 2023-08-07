package com.spring.products.service.implementation;

import com.spring.products.entity.Brand;
import com.spring.products.entity.Category;
import com.spring.products.exception.BrandAlreadyExist;
import com.spring.products.exception.CategoryAlreadyExist;
import com.spring.products.exception.CategoryNotFoundException;
import com.spring.products.repository.BrandRepo;
import com.spring.products.repository.CategoryRepo;
import com.spring.products.service.BrandService;
import com.spring.products.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static java.lang.Boolean.TRUE;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;
    private final BrandRepo brandRepo;
    @Override
    public Category addCategory(Category category) {
        //System.out.println(category.getBrands().get(0).getBrandName());
        if(!checkExistenceCategory(category.getCategoryName())){
            return categoryRepo.save(category);
        }
        throw new CategoryAlreadyExist("Category Name AlreadyExist");
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepo.findAll();
    }

    @Override
    public Category updateCategory(long id,Category category) {
        Category checkCategoryExist = getCategoryById(id);
        checkCategoryExist.setCategoryName(category.getCategoryName());
        categoryRepo.save(checkCategoryExist);
        return checkCategoryExist;

    }
    /*@Override
    public List<Category> getCategoryById(long id) {
        return categoryRepo.findAllByCategoryId(id);//.orElseThrow(()->new CategoryNotFoundException("category not found with this id "+id));
    }*/
    @Override
    public Category getCategoryById(long id) throws CategoryNotFoundException{
        return categoryRepo.findById(id).orElseThrow(()->new CategoryNotFoundException("category not found with this id "+id));
    }

    @Override
    public Boolean deleteCategory(long id) {
        categoryRepo.deleteById(id);
        return TRUE;
    }

    @Override
    public Boolean checkExistenceCategory(String categoryName) {

        return !categoryRepo.checkExistenceCategory(categoryName).isEmpty();
    }

    @Override
    public Category assignBrandToCategory(Long categoryId, Long brandId) {
        Set<Brand> brandSet = null;
        Category category = categoryRepo.findById(categoryId).get();
        Brand brand = brandRepo.findById(brandId).get();
        System.out.println(brand);
        brandSet =  category.getBrands();
        /*if(brandSet.contains(brand)){
            throw new BrandAlreadyExist("brand already exist in this category");
        }*/
        brandSet.add(brand);
        category.setBrands(brandSet);

        return categoryRepo.save(category);
    }

    @Override
    public Category getCategoryByName(String categoryName) {
        return categoryRepo.findByCategoryName(categoryName);
    }

    @Override
    public Category updateBrandOfCategory(long categoryId,long brandId){
        Set<Brand> brandSet = null;
        Category category = categoryRepo.findById(categoryId).get();
        Brand brand = brandRepo.findById(brandId).get();
        System.out.println(brand);
        brandSet =  category.getBrands();
        /*if(brandSet.contains(brand)){
            throw new BrandAlreadyExist("brand already exist in this category");
        }*/
        brandSet.add(brand);
        category.setBrands(brandSet);

        return categoryRepo.save(category);
        //return null;
    }
}
