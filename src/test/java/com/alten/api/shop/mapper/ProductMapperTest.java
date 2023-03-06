package com.alten.api.shop.mapper;

import com.alten.api.shop.builder.ProductDtoBuilderExtended;
import com.alten.api.shop.dto.ProductDto;
import com.alten.api.shop.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class ProductMapperTest {

    @Test
    void dtoToModel_checkConversionTest(){
        //INIT
        ProductDto productDto = ProductDtoBuilderExtended.initWithDefaultValues();
        //EXEC
        Product result = ProductMapper.INSTANCE.dtoToModel(productDto);
        //CHECK
        assertThat(result).usingRecursiveComparison().isEqualTo(productDto);
    }
}
