package com.harshitha.jpatutorial.jpatuts.controllers;


import com.harshitha.jpatutorial.jpatuts.entites.Product;
import com.harshitha.jpatutorial.jpatuts.repositories.ProductRepository;
import org.hibernate.query.Order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final int pageSize = 5;

    private final ProductRepository productRepository;
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /*@GetMapping
    public List<Product> OrderByPrice(){
        return productRepository.findByOrderByPrice();
    }*/

    /*@GetMapping
    public List<Product> findBy(@RequestParam(defaultValue = "id") String sortBy){
        return productRepository.findBy(Sort.by(sortBy));
    }*/

    /*@GetMapping
    public List<Product> GetAll(@RequestParam(defaultValue = "title") String sortBy){
        return productRepository.getBy(Sort.by(Sort.Direction.ASC, sortBy));
    }*/

    /*@GetMapping
    public List<Product> findAll(@RequestParam(defaultValue = "id") String sortBy) {
        return productRepository.findBy(Sort.by
                                (Sort.Order.asc("sku"),
                                        Sort.Order.asc("title")));
    }*/

    @GetMapping
    public Page<Product> findAll(@RequestParam(defaultValue = "id") String sortBy,
                                 @RequestParam Integer pagenum){

        Pageable pageable = PageRequest.of(pagenum, pageSize, Sort.by(Sort.Direction.ASC, sortBy));
        return productRepository.findAll(pageable);
    }

}
