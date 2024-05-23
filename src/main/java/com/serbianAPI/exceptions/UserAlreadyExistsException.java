package com.serbianAPI.exceptions;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserAlreadyExistsException extends RuntimeException{
    private String message;
}
