package com.dhiraj.productservice.dtos;

import com.dhiraj.productservice.models.Category;
import com.dhiraj.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;

    public Product toProduct() {
        Product product = new Product();
        product.setId(id);
        product.setTitle(title);
        product.setPrice(price);

        Category categoryDetails = new Category();
        categoryDetails.setId(id);
        categoryDetails.setTitle(category);

        product.setCategory(categoryDetails);
        product.setDescription(description);
        product.setImage(image);
        return product;
    }
}
