package com.serbianAPI.controller;

import com.serbianAPI.dto.ChangePasswordRequest;
import com.serbianAPI.dto.NewProductRequest;
import com.serbianAPI.dto.ProductDto;
import com.serbianAPI.service.ProductService;
import com.serbianAPI.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final ProductService productService;

    private final UserService service;

    @PatchMapping
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordRequest request,
            Principal connectedUser
    ) {
        service.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }
    @PostMapping(value = "/create-product", produces = "application/json")
    public ResponseEntity<?> createProduct(@RequestBody NewProductRequest productRequest){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String user = authentication.getName();
            productService.createProduct(productRequest, user);
            return ResponseEntity.ok().build();
        }catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @RequestMapping(method = RequestMethod.GET, value = "/get-products")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String user = authentication.getName();
            return ResponseEntity.ok(productService.getAllProducts(user));
        }catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}