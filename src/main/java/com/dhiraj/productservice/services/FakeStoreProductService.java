package com.dhiraj.productservice.services;

import com.dhiraj.productservice.dtos.FakeStoreProductDto;
import com.dhiraj.productservice.dtos.GenericProductDto;
import com.dhiraj.productservice.exceptions.NotFoundException;
import com.dhiraj.productservice.thirdpartyclients.productsservice.fakestore.FakeStoryProductServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class FakeStoreProductService implements  ProductService{

    private FakeStoryProductServiceClient fakeStoryProductServiceClient;

    @Autowired
    public FakeStoreProductService(FakeStoryProductServiceClient fakeStoryProductServiceClient) {
        this.fakeStoryProductServiceClient = fakeStoryProductServiceClient;
    }

    private GenericProductDto convertFakeStoreProductIntoGenericProduct(FakeStoreProductDto fakeStoreProductDto) {
        GenericProductDto product = new GenericProductDto();
        product.setId(fakeStoreProductDto.getId());
        product.setImage(fakeStoreProductDto.getImage());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setCategory(fakeStoreProductDto.getCategory());
        return product;
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        List<GenericProductDto> genProductDtos = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : fakeStoryProductServiceClient.getAllProducts()){
            genProductDtos.add(convertFakeStoreProductIntoGenericProduct(fakeStoreProductDto));
        }
        return genProductDtos;
    }

    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        return convertFakeStoreProductIntoGenericProduct(fakeStoryProductServiceClient.getSingleProduct(id));
    }

    @Override
    public GenericProductDto deleteProduct(Long id) {
        return convertFakeStoreProductIntoGenericProduct(fakeStoryProductServiceClient.deleteProduct(id));
    }

//    @Override
//    public GenericProductDto getProductById(Long id) {
//        GenericProductDto productDto = fakeStoryProductServiceClient.getSingleProduct(id);
//        return convertFakeStoreProductIntoGenericProduct(productDto);
//    }

}
