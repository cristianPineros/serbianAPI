package com.serbianAPI.dto;

import com.serbianAPI.dao.entity.ProductType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewProductRequest {
    private ProductType type;
    private String name;
}
