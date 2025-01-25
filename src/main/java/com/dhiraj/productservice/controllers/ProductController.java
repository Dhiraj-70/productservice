package com.dhiraj.productservice.controllers;

import com.dhiraj.productservice.dtos.CreateProductDto;
import com.dhiraj.productservice.exceptions.NotFoundException;
import com.dhiraj.productservice.exceptions.ProductNotCreatedException;
import com.dhiraj.productservice.models.Product;
import com.dhiraj.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> product = productService.getAllProducts();
        if (product.isEmpty()) {
            return new ResponseEntity<>(
                    product,
                    HttpStatus.NOT_FOUND
            );
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) throws NotFoundException {
        Product product = productService.getProductById(id);
        if (product == null) {
            throw new NotFoundException("Product Does't Exist");
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProductById(@PathVariable Long id) throws NotFoundException {
        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.OK);
    }

//    @PostMapping
//    public Product createProduct(@RequestBody Product product) {
//        return productService.createProduct(product);
//    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductDto productD) throws ProductNotCreatedException {
        Product product = productService.createProduct(productD);
        if (product == null) {
            throw new ProductNotCreatedException("Product Not Created");
        }
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }
//    @ExceptionHandler(NotFoundException.class)
//    public ResponseEntity<ErrorDto> handleProductNotFoundException(NotFoundException exception){
//        ErrorDto errorDto = new ErrorDto();
//        errorDto.setMessage(exception.getMessage());
//        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
//    }

}