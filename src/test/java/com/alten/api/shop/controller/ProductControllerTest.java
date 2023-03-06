package com.alten.api.shop.controller;

import com.alten.api.shop.builder.ProductDtoBuilderExtended;
import com.alten.api.shop.dto.ProductDto;
import com.alten.api.shop.exception.AltenException;
import com.alten.api.shop.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static java.util.List.of;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
    @InjectMocks
    private ProductController productController;
    @Mock
    private ProductService productServiceMocked;

    @Test
    void getProductById_checkMethodCalledTest() throws AltenException {
        //INIT
        Long productId = 10L;
        //EXEC
        productController.getProductById(productId);
        //CHECK
        verify(productServiceMocked).getProductById(productId);
        verifyNoMoreInteractions(productServiceMocked);
    }

    @Test
    void createProduct_checkMethodCalledTest() {
        //INIT
        ProductDto productDto = ProductDtoBuilderExtended.initWithDefaultValues();
        //EXEC
        productController.createProduct(productDto);
        //CHECK
        verify(productServiceMocked).createProduct(productDto);
        verifyNoMoreInteractions(productServiceMocked);
    }

    @Test
    void updateProduct_checkMethodCalledTest() throws AltenException {
        //INIT
        ProductDto productDto = ProductDtoBuilderExtended.initWithDefaultValues();
        //EXEC
        productController.updateProduct(productDto);
        //CHECK
        verify(productServiceMocked).updateProduct(productDto);
        verifyNoMoreInteractions(productServiceMocked);
    }

    @Test
    void deleteProductById_checkMethodCalledTest() {
        //INIT
        Long productId = 10L;
        //EXEC
        productController.deleteProductById(productId);
        //CHECK
        verify(productServiceMocked).deleteProductById(productId);
        verifyNoMoreInteractions(productServiceMocked);
    }

    @Test
    void deleteProductsByIds_checkMethodCalledTest() {
        //INIT
        List<Long> productIdList = of(10L);
        //EXEC
        productController.deleteProductsByIds(productIdList);
        //CHECK
        verify(productServiceMocked).deleteProductsByIdList(productIdList);
        verifyNoMoreInteractions(productServiceMocked);
    }

    @Test
    void getAllProducts_checkMethodCalledTest() {
        //INIT
        //EXEC
        productController.getAllProducts();
        //CHECK
        verify(productServiceMocked).getAllProducts();
        verifyNoMoreInteractions(productServiceMocked);
    }
}
