package com.dhiraj.productservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class ProductNotCreatedException extends Exception {
    public ProductNotCreatedException(String msg) {
        super(msg);
    }
}
