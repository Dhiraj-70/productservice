package com.dhiraj.productservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenericProductDto {
    private Long id;
    private String title;
    private String description;
    private double price;
    private String category;
    private String image;

}
