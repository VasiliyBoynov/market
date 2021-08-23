package ru.geekbrains.msproducts.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.exceptions.ResourceNotFoundException;
import ru.geekbrains.msproducts.model.dtos.ProductDto;
import ru.geekbrains.msproducts.model.entities.Product;
import ru.geekbrains.msproducts.repositories.specifications.ProductSpecifications;
import ru.geekbrains.msproducts.services.ProductService;


import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    // GET http://localhost:XXXXX/products

    @GetMapping
    public Page<ProductDto> findAllProducts(
            @RequestParam MultiValueMap<String, String> params,
            @RequestParam(name = "p", defaultValue = "1") Integer pag,
            @RequestParam(name = "productsToPage", defaultValue = "10") Integer productsToPage
    ) {
        if (pag < 1) {
            pag=1;
        }
        if (productsToPage<0) {productsToPage=10;}
        return productService.findAll(ProductSpecifications.build(params), pag, productsToPage);
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id: " + id + " doesn't exist"));
    }

    @PostMapping
    public Product save(@RequestBody Product product) {
        product.setIdProduct(null);
        return productService.save(product);
    }

    @DeleteMapping("/{id}")
    public List<Product>  deleteById(@PathVariable Long id) {
        return  productService.deleteById(id);
    }





}
