package com.serbianAPI.controller;

import com.serbianAPI.dto.MessageDto;
import com.serbianAPI.dto.NewProductRequest;
import com.serbianAPI.dto.ProductDto;
import com.serbianAPI.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/get-products")
    public ResponseEntity<?> getProducts() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userEmail = authentication.getName();
            return ResponseEntity.ok(productService.getAllProductsFromUser(userEmail));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageDto(ex.getMessage()));
        }
    }

    @PostMapping(value = "/create-product", produces = "application/json")
    public ResponseEntity<?> createProduct(@RequestBody NewProductRequest productRequest){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userEmail = authentication.getName();
            String newProductId = productService.createProduct(productRequest, userEmail);
            return ResponseEntity.created(URI.create("/products/" + newProductId)).body(newProductId);
        }catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/delete-product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id){
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable String id){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userEmail = authentication.getName();
            ProductDto product = productService.getProduct(id, userEmail);
            return ResponseEntity.ok(product);
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }
}
