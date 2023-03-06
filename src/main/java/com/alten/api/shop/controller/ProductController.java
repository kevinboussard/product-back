package com.alten.api.shop.controller;

import com.alten.api.shop.dto.ProductDto;
import com.alten.api.shop.exception.AltenException;
import com.alten.api.shop.model.Product;
import com.alten.api.shop.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Operation(summary = "Get a product by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product found"),
            @ApiResponse(responseCode = "404", description = "Product not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(value = "/{id}")
    public Product getProductById(
            @Parameter(description = "id of product to be search")
            @PathVariable Long id
    ) throws AltenException {
        log.info("Get product with id : {}", id);
        return this.productService.getProductById(id);
    }

    @Operation(summary = "Create a product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product created"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping()
    @ResponseStatus(value = HttpStatus.CREATED)
    public Product createProduct(
            @Parameter(description = "DTO of product to be created")
            @RequestBody ProductDto productDto
    ) {
        log.info("Create a new product with name : {}", productDto.getName());
        log.debug("All product data : {}", productDto);
        return this.productService.createProduct(productDto);
    }

    @Operation(summary = "Update a product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product updated"),
            @ApiResponse(responseCode = "404", description = "Product not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PatchMapping()
    @ResponseStatus(value = HttpStatus.CREATED)
    public Product updateProduct(
            @Parameter(description = "DTO of product to be updated")
            @RequestBody ProductDto productDto
    ) throws AltenException {
        log.info("Update product with id : {}, name : {}", productDto.getId(), productDto.getName());
        log.debug("All product data : {}", productDto);
        return this.productService.updateProduct(productDto);
    }

    @Operation(summary = "Delete a product by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Product deleted"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteProductById(
            @Parameter(description = "id of product to be deleted")
            @PathVariable Long id
    ) {
        log.info("Delete product with id : {}", id);
        this.productService.deleteProductById(id);
    }

    @Operation(summary = "Delete all products by an id list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Products deleted"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping()
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteProductsByIds(
            @Parameter(description = "ids of products to be deleted")
            @RequestBody List<Long> ids
    ) {
        log.info("Delete products with id in : {}", ids);
        this.productService.deleteProductsByIdList(ids);
    }

    @Operation(summary = "Get all products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return all products"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping()
    public List<Product> getAllProducts() {
        log.info("Get all products");
        return this.productService.getAllProducts();
    }
}
