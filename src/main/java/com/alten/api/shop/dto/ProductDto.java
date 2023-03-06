package com.alten.api.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String code;
    private String name;
    private String description;
    private String image;
    private Integer price;
    private String category;
    private Integer quantity;
    private String inventoryStatus;
    private Integer rating;
}
