package com.serbianAPI.service;

import com.serbianAPI.dto.TelegramCodeDto;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class TelegramService {

    public TelegramCodeDto generateCode(String userEmail){

        Random random = new Random();
        StringBuilder code = new StringBuilder(7);

        for (int i = 0; i < 7; i++) {
            String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            int index = random.nextInt(UPPERCASE_LETTERS.length());
            code.append(UPPERCASE_LETTERS.charAt(index));
        }

        return new TelegramCodeDto(code.toString());
    }
}
