package com.alten.api.shop.service;

import com.alten.api.shop.dto.ProductDto;
import com.alten.api.shop.exception.AltenException;
import com.alten.api.shop.mapper.ProductMapper;
import com.alten.api.shop.model.Product;
import com.alten.api.shop.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.alten.api.shop.utils.Constants.PRODUCT_NOT_CHANGED_MESSAGE;
import static com.alten.api.shop.utils.Constants.PRODUCT_NOT_FOUND_ERROR_MESSAGE;
import static java.lang.String.format;

@Slf4j
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    /**
     * Get product in BDD by its id
     *
     * @param id Product id
     * @return Product in BDD
     * @throws AltenException Error if product id is not in BDD
     */
    public Product getProductById(Long id) throws AltenException {
        return this.productRepository.findById(id)
                .orElseThrow(() -> new AltenException(format(PRODUCT_NOT_FOUND_ERROR_MESSAGE, id), HttpStatus.NOT_FOUND));
    }

    /**
     * Delete product in BDD by its id
     *
     * @param id Product id to be deleted
     */
    public void deleteProductById(Long id){
        this.productRepository.deleteById(id);
    }

    /**
     * Delete all product in BDD by an id list
     *
     * @param ids Product id list to be deleted
     */
    public void deleteProductsByIdList(List<Long> ids){
        this.productRepository.deleteAllById(ids);
    }

    /**
     * Create new product in BDD
     *
     * @param productDto Product DTO to be created
     * @return Product created
     */
    public Product createProduct(ProductDto productDto){
        Product productToCreate = ProductMapper.INSTANCE.dtoToModel(productDto);
        productToCreate.setId(null);
        return this.productRepository.save(productToCreate);
    }

    /**
     * Update product in BDD
     *
     * @param productDto Product DTO to be updated
     * @return Product updated
     * @throws AltenException Error if product id is not in BDD
     */
    public Product updateProduct(ProductDto productDto) throws AltenException {
        Product productToUpdate = ProductMapper.INSTANCE.dtoToModel(productDto);
        Product productUpdated = this.productRepository.findById(productDto.getId())
                .orElseThrow(() ->
                        new AltenException(
                                format(PRODUCT_NOT_FOUND_ERROR_MESSAGE, productDto.getId()),
                                HttpStatus.NOT_FOUND)
                );
        if(!productUpdated.equals(productToUpdate)){
            return this.productRepository.save(productToUpdate);
        }else{
            log.debug(PRODUCT_NOT_CHANGED_MESSAGE, productDto.getId());
            return productToUpdate;
        }
    }

    /**
     * Get all products in BDD
     *
     * @return All products in BDD
     */
    public List<Product> getAllProducts(){
        return (List<Product>) this.productRepository.findAll();
    }
}
