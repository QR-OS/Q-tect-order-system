package com.smallB.QOS.product.controller;

import com.smallB.QOS.product.domain.ProductDto;
import com.smallB.QOS.product.service.ProductService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@EnableAutoConfiguration
@MapperScan(basePackages = "com.smallB.QOS.product.dao")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product/{product_id}/{store_id}")
    public ProductDto getProduct(@PathVariable @Valid String product_id,@PathVariable @Valid String store_id) throws Exception{
        return productService.getProduct(product_id,store_id);
    }

    @GetMapping("/product/{store_id}")
    public List<ProductDto> getProducts(@PathVariable @Valid String store_id) throws Exception{
        return productService.getProducts(store_id);
    }

    @GetMapping("/product/{store_id}/category")
    public List<String> getCategory(@PathVariable @Valid String store_id) throws Exception{
        return productService.getCategory(store_id);
    }

    @PostMapping("/product")
    public ResponseEntity<?> createProducts(@RequestBody ProductDto productDto) throws Exception{
        Object res = productService.createProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @PatchMapping("/product")
    public boolean updateProduct(@RequestBody @Valid ProductDto productDto) throws Exception{
        return productService.updateProduct(productDto);
    }

    @DeleteMapping("/product/{product_id}/{store_id}")
    public ResponseEntity<?> deleteProduct(@PathVariable @Valid String product_id, @PathVariable @Valid String store_id) throws Exception{
        Object res =  productService.deleteProduct(product_id,store_id);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
