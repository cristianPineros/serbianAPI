package com.serbianAPI.controller;

import com.serbianAPI.dto.TelegramCodeDto;
import com.serbianAPI.service.TelegramService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/telegram")
public class TelegramController {

    private final TelegramService telegramService;
    @GetMapping("/generate-code")
    public ResponseEntity<TelegramCodeDto> generateCode(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        return ResponseEntity.ok(telegramService.generateCode(userEmail));
    }
}
