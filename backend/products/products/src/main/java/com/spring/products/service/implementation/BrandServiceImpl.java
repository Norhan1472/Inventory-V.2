package com.spring.products.service.implementation;

import com.spring.products.entity.Brand;
import com.spring.products.exception.BrandAlreadyExist;
import com.spring.products.exception.BrandNotFoundException;
import com.spring.products.repository.BrandRepo;
import com.spring.products.service.BrandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import static java.lang.Boolean.TRUE;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandRepo brandRepo;
    @Override
    public Brand addBrand(Brand brand)throws BrandAlreadyExist {
        if(!checkExistenceBrand(brand.getBrandName()))
            return brandRepo.save(brand);
         throw new BrandAlreadyExist("Brand Already Exist");
    }
    @Override
    public Brand updateBrand(long id, Brand brand) {
        Brand checkExist = getBrandById(id);
        System.out.println("brand Name "+brand.getBrandName());
        checkExist.setBrandName(brand.getBrandName());
        List<Brand>allBrands= getAllBrands();
        //if(allBrands.contains(brand.getBrandName())){
            //System.out.println("helllllllo");
          //  throw new BrandAlreadyExist("Brand Already Exist");
        //}
        return brandRepo.save(checkExist);
    }
    @Override
    public Boolean deleteBrand(long id) {
        brandRepo.deleteById(id);
        return TRUE;
    }
    @Override
    public List<Brand> getAllBrands() {
        return brandRepo.findAll();
    }

    /*@Override
    public List<Brand> getBrandById(long id) {
        return brandRepo.findAllByBrandId(id);//.orElseThrow(()->new BrandNotFoundException("Brand not found"));
    }*/
    @Override
    public Brand getBrandById(long id) {
        return brandRepo.findById(id).orElseThrow(()->new BrandNotFoundException("Brand not found"));
    }

    @Override
    public Boolean checkExistenceBrand(String brandName) {
        String brandNameAfterRemoveSpace = brandName.replaceAll("\\s+", "");
        return !brandRepo.checkExistenceBrand(brandNameAfterRemoveSpace).isEmpty();
    }

    @Override
    public Brand getBrandByName(String brandName) {

        return brandRepo.findByBrandName(brandName);
    }
}
