package com.example.product_service.controller;

import com.example.product_service.dto.ProductRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService prodservice;

    @PostMapping
    public ResponseEntity<Integer> createProduct(@RequestBody @Valid ProductRequest productreq) {

        return ResponseEntity.ok(prodservice.createProduct(productreq));
    }
    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResponse>findProductById(@PathVariable("product-id") Integer id){
        return ResponseEntity.ok(prodservice.findProductById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>>getAllProducts(){
        return ResponseEntity.ok(prodservice.findAllProducts());
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProduct(@RequestBody @Valid List<ProductPurchaseRequest> productPurchaseRequest) {

        return ResponseEntity.ok(prodservice.purchaseProduct(productPurchaseRequest));
    }

}
