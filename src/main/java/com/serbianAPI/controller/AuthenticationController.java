package com.serbianAPI.controller;

import com.serbianAPI.dto.AuthenticationRequest;
import com.serbianAPI.dto.AuthenticationResponse;
import com.serbianAPI.dto.MessageDto;
import com.serbianAPI.dto.RegisterRequest;
import com.serbianAPI.exceptions.UserAlreadyExistsException;
import com.serbianAPI.exceptions.UserNotFoundException;
import com.serbianAPI.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest request
    ) {
        try {
            AuthenticationResponse res = service.register(request);
            return ResponseEntity.ok(res);
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageDto(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new MessageDto(e.getMessage()));
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        try {
            AuthenticationResponse res = service.authenticate(request);
            return ResponseEntity.ok(res);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageDto(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new MessageDto(e.getMessage()));
        }
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }


}