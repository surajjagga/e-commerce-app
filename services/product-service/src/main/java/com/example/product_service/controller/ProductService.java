package com.example.product_service.controller;

import com.example.product_service.dto.ProductRequest;
import com.example.product_service.exception.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.product_service.product.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    public Integer createProduct(ProductRequest  productRequest) {
    return productRepository.save(productMapper.toProduct(productRequest)).getId();
    }

    public ProductResponse findProductById(Integer id) {
        Product product = productRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Product not found"));
        return productMapper.fromProduct(product);
    }

    public List<ProductResponse> findAllProducts() {
       return  productRepository.findAll().stream().map(productMapper::fromProduct).collect(Collectors.toList());
    }

    public List<ProductPurchaseResponse> purchaseProduct(@Valid List<ProductPurchaseRequest> productPurchaseRequest) {
       List<Integer> requestedProductIds = productPurchaseRequest.stream().map(ProductPurchaseRequest::productId).toList();
       var storedProducts = productRepository.findAllByIdInOrderById((requestedProductIds));
       if(requestedProductIds.size()!=storedProducts.size()) {
           throw new ProductPurchaseException("Some requested products do not exists");
       }
       var storedRequest = productPurchaseRequest.stream().sorted(Comparator.comparing(ProductPurchaseRequest::productId)).toList();
        var  purchaseResponseArrayList = new ArrayList<ProductPurchaseResponse>();
        for(int i=0; i<=storedRequest.size();i++){
        var product = storedProducts.get(i);
        var productRequest = storedRequest.get(i);
        if(product.getAvailableQuantity()< productRequest.quantity())
            throw new ProductPurchaseException("Requested product quantity is not available "+productRequest.productId());
        var updatedQuantity = product.getAvailableQuantity() - productRequest.quantity();
        product.setAvailableQuantity(updatedQuantity);
        productRepository.save(product);
        purchaseResponseArrayList.add(productMapper.fromProductPurchase(product,productRequest.quantity()));
        }

        return purchaseResponseArrayList;
    }
}
