package com.dhiraj.productservice.advices;

import com.dhiraj.productservice.dtos.ErrorDto;
import com.dhiraj.productservice.exceptions.NotFoundException;
import com.dhiraj.productservice.exceptions.ProductNotCreatedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDto> handleProductNotFoundException(NotFoundException exception){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(exception.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductNotCreatedException.class)
    public ResponseEntity<ErrorDto> handleProductNotCreatedException(ProductNotCreatedException exception){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(exception.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }
}
