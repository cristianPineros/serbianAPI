package com.serbianAPI.service;

import com.serbianAPI.dao.entity.Product;
import com.serbianAPI.dao.entity.User;
import com.serbianAPI.dao.repository.ProductRepository;
import com.serbianAPI.dao.repository.UserRepository;
import com.serbianAPI.dto.NewProductRequest;
import com.serbianAPI.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public String createProduct(NewProductRequest newProductRequest, String userEmail) {

        User user = userRepository.findByEmail(userEmail).orElseThrow();

        Product product = productRepository.save(Product.builder()
                .name(newProductRequest.getName())
                .type(newProductRequest.getType())
                .user(user)
                .build());

        return product.getId();
    }

    public List<ProductDto> getAllProductsFromUser(String userEmail) {
        User user = userRepository.findByEmail(userEmail).orElseThrow();

        List<Product> products = productRepository.findByUserId(user.getId());

        return products.stream().map(p -> ProductDto.builder()
                .id(p.getId())
                .type(p.getType().toString())
                .name(p.getName())
                .build()).toList();
    }

    public void deleteProduct(String productId){
        productRepository.deleteById(productId);
    }

    public ProductDto getProduct(String id, String userEmail){
        Product product = productRepository.findById(id).orElseThrow();

        User user = userRepository.findByEmail(userEmail).orElseThrow();

        if(user.getId() != product.getUser().getId()){

        }

        return modelMapper.map(product, ProductDto.class);
    }
}
