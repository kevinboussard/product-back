package com.alten.api.shop.builder;

import com.alten.api.shop.dto.ProductDto;
import com.alten.api.shop.model.Product;

public class ProductDtoBuilderExtended extends ProductDto {

    public static ProductDtoBuilder initDefaultBuilder(){
        return ProductDto.builder()
                .id(1L)
                .code("nvklal433")
                .name("Black Watch")
                .description("Product Description")
                .image("bw.png")
                .price(72)
                .category("Accessories")
                .quantity(61)
                .inventoryStatus("INSTOCK")
                .rating(1);
    }

    public static ProductDto initWithDefaultValues(){
        return initDefaultBuilder().build();
    }
}
