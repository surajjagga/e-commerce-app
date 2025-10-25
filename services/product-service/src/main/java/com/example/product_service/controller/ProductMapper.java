package com.example.product_service.controller;

import com.example.product_service.dto.ProductRequest;
import org.springframework.stereotype.Service;
import com.example.product_service.product.Category;
import com.example.product_service.product.Product;

@Service
public class ProductMapper {
    public Product toProduct(ProductRequest productRequest ) {
        return  Product.builder().id(productRequest.id())
                .name(productRequest.name())
                .availableQuantity(productRequest.availableQuantity())
                .description(productRequest.description())
                .price(productRequest.price())
                .category(Category.builder().id(productRequest.categoryId()).build()).build();
    }
    public ProductResponse fromProduct(Product product) {
        return  new ProductResponse(product.getId(), product.getName(),product.getDescription(), product.getPrice(),product.getAvailableQuantity(),product.getCategory().getId(),product.getCategory().getName(),product.getCategory().getDescription());
    }


    public ProductPurchaseResponse fromProductPurchase(Product product,double quantity ) {
        return  new ProductPurchaseResponse(product.getId(), product.getName(),  product.getPrice(),quantity);
    }
}
