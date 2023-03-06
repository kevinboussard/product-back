package com.alten.api.shop.service;

import com.alten.api.shop.builder.ProductBuilderExtended;
import com.alten.api.shop.builder.ProductDtoBuilderExtended;
import com.alten.api.shop.dto.ProductDto;
import com.alten.api.shop.exception.AltenException;
import com.alten.api.shop.mapper.ProductMapper;
import com.alten.api.shop.model.Product;
import com.alten.api.shop.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.alten.api.shop.utils.Constants.PRODUCT_NOT_FOUND_ERROR_MESSAGE;
import static java.lang.String.format;
import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @InjectMocks
    private ProductService productService;
    @Mock
    private ProductRepository productRepositoryMocked;

    @Test
    void getProductById_checkMethodCalledTest() throws AltenException {
        //INIT
        Product product = ProductBuilderExtended.initWithDefaultValues();
        when(productRepositoryMocked.findById(product.getId())).thenReturn(Optional.of(product));
        //EXEC
        Product result = productService.getProductById(product.getId());
        //CHECK
        verify(productRepositoryMocked).findById(product.getId());
        assertEquals(product, result);
    }

    @Test
    void getProductById_checkExceptionIfProductNotInBddTest() {
        //INIT
        Product product = ProductBuilderExtended.initWithDefaultValues();
        when(productRepositoryMocked.findById(product.getId())).thenReturn(Optional.empty());
        //EXEC
        AltenException exception = Assertions.assertThrows(AltenException.class, () -> {
            productService.getProductById(product.getId());
        });
        //CHECK
        Assertions.assertEquals(format(PRODUCT_NOT_FOUND_ERROR_MESSAGE, product.getId()), exception.getMessage());
    }

    @Test
    void createProduct_checkMethodCalledTest(){
        //INIT
        ProductDto productDto = ProductDtoBuilderExtended.initWithDefaultValues();
        Product productToCreate = ProductMapper.INSTANCE.dtoToModel(productDto);
        productToCreate.setId(null);
        Product productCreated = ProductBuilderExtended.initWithDefaultValues();
        when(productRepositoryMocked.save(productToCreate)).thenReturn(productCreated);
        //EXEC
        Product result = productService.createProduct(productDto);
        //CHECK
        verify(productRepositoryMocked).save(productToCreate);
        assertEquals(productCreated, result);
    }

    @Test
    void updateProduct_checkMethodCalledIfProductHasNotBeenChangedTest() throws AltenException {
        //INIT
        Product product = ProductBuilderExtended.initWithDefaultValues();
        ProductDto productDto = ProductDtoBuilderExtended.initWithDefaultValues();
        Optional<Product> productOptional = Optional.of(product);
        when(productRepositoryMocked.findById(product.getId())).thenReturn(productOptional);
        //EXEC
        Product result = productService.updateProduct(productDto);
        //CHECK
        verify(productRepositoryMocked).findById(product.getId());
        verifyNoMoreInteractions(productRepositoryMocked);
        assertEquals(product, result);
    }

    @Test
    void updateProduct_checkMethodCalledIfProductHasBeenChangedTest() throws AltenException {
        //INIT
        String newDescription = "description2";
        Product product = ProductBuilderExtended.initWithDefaultValues();
        ProductDto productDto = ProductDtoBuilderExtended.initDefaultBuilder().description(newDescription).build();
        Product productToUpdate = ProductMapper.INSTANCE.dtoToModel(productDto);
        Product productUpdated = ProductBuilderExtended.initDefaultBuilder().description(newDescription).build();
        Optional<Product> productOptional = Optional.of(product);
        when(productRepositoryMocked.findById(product.getId())).thenReturn(productOptional);
        when(productRepositoryMocked.save(productToUpdate)).thenReturn(productUpdated);
        //EXEC
        Product result = productService.updateProduct(productDto);
        //CHECK
        verify(productRepositoryMocked).findById(product.getId());
        verify(productRepositoryMocked).save(productToUpdate);
        verifyNoMoreInteractions(productRepositoryMocked);
        assertEquals(productUpdated, result);
    }

    @Test
    void updateProduct_checkExceptionIfProductNotInBddTest() {
        //INIT
        Product product = ProductBuilderExtended.initWithDefaultValues();
        ProductDto productDto = ProductDtoBuilderExtended.initWithDefaultValues();
        Optional<Product> productOptional = Optional.empty();
        when(productRepositoryMocked.findById(product.getId())).thenReturn(productOptional);
        //EXEC
        AltenException exception = Assertions.assertThrows(AltenException.class, () -> {
            productService.updateProduct(productDto);
        });
        //CHECK
        Assertions.assertEquals(format(PRODUCT_NOT_FOUND_ERROR_MESSAGE, product.getId()), exception.getMessage());
    }

    @Test
    void deleteProductById_checkMethodCalledTest(){
        //INIT
        Long projectId = 10L;
        //EXEC
        productService.deleteProductById(projectId);
        //CHECK
        verify(productRepositoryMocked).deleteById(projectId);
    }

    @Test
    void deleteProductsByIdList_checkMethodCalledTest(){
        //INIT
        List<Long> projectIdList = of(10L);
        //EXEC
        productService.deleteProductsByIdList(projectIdList);
        //CHECK
        verify(productRepositoryMocked).deleteAllById(projectIdList);
    }

    @Test
    void getAllProducts_checkMethodCalledTest(){
        //INIT
        //EXEC
        productService.getAllProducts();
        //CHECK
        verify(productRepositoryMocked).findAll();
    }
}
