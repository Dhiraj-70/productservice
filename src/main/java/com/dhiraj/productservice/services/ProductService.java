package com.dhiraj.productservice.services;

import com.dhiraj.productservice.dtos.GenericProductDto;

import java.util.List;

public interface ProductService {

    List<GenericProductDto> getAllProducts();
}
