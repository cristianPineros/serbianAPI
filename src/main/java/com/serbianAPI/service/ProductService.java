package com.serbianAPI.service;

import com.serbianAPI.dao.entity.Product;
import com.serbianAPI.dao.repository.ProductRepository;
import com.serbianAPI.dto.NewProductRequest;
import com.serbianAPI.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public void createProduct(NewProductRequest newProductRequest, String userName){
        Product product = Product.builder()
                .userId(userName)
                .name(newProductRequest.getName())
                .type(newProductRequest.getType())
                .build();
        productRepository.save(product);
    }

    public List<ProductDto> getAllProducts(String userId){
        List<Product> products =  productRepository.findAllByUserId(userId);
        return products.stream().map(x -> ProductDto.builder().name(x.getName()).type(x.getType()).build()).collect(Collectors.toList());
    }
}
