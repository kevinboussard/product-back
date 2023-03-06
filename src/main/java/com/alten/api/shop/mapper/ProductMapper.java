package com.alten.api.shop.mapper;

import com.alten.api.shop.dto.ProductDto;
import com.alten.api.shop.model.Product;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @InheritInverseConfiguration
    Product dtoToModel(ProductDto target);
}
