package com.dhiraj.productservice.services;

import com.dhiraj.productservice.dtos.CreateProductDto;
import com.dhiraj.productservice.dtos.GenericProductDto;
import com.dhiraj.productservice.exceptions.NotFoundException;
import com.dhiraj.productservice.exceptions.ProductNotCreatedException;
import com.dhiraj.productservice.models.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product getProductById(Long id) throws NotFoundException;

    Product deleteProduct(Long id);

    Product createProduct(CreateProductDto product) throws ProductNotCreatedException;
}
