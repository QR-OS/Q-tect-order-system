package com.smallB.QOS.productCategory.controller;

import com.smallB.QOS.productCategory.dao.ProductCategoryDao;
import com.smallB.QOS.productCategory.domain.ProductCategoryDto;
import com.smallB.QOS.productCategory.service.ProductCategoryService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@EnableAutoConfiguration
@MapperScan(basePackages = "com.smallB.QOS.productCategory.dao")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/productcategory/{store_id}")
    public ResponseEntity<?> getProductCategory(@PathVariable @Valid String store_id)throws Exception{
        Object res = productCategoryService.getProducts(store_id);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PostMapping("/productcategory")
    public ResponseEntity<?> createProductCategory(@RequestBody @Valid ProductCategoryDto productCategoryDto) throws Exception{
        Boolean res = productCategoryService.CreateProduct(productCategoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

}
