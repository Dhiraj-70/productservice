package com.dhiraj.productservice.thirdpartyclients.productsservice.fakestore;

import com.dhiraj.productservice.dtos.FakeStoreProductDto;
import com.dhiraj.productservice.dtos.GenericProductDto;
import com.dhiraj.productservice.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FakeStoryProductServiceClient {
    @Value("${fakestore.api.url}")
    private String fakeStoreApiUrl;

    @Value("${fakestore.api.paths.product}")
    private String fakeStoreProductsApiPath;

    private String specificProductRequestUrl ;
    private String productRequestsBaseUrl ;
    private RestTemplate restTemplate;

    public FakeStoryProductServiceClient(
            RestTemplate restTemplate,
            @Value("${fakestore.api.url}") String fakeStoreApiUrl,
            @Value("${fakestore.api.paths.product}") String fakeStoreProductsApiPath) {
        this.restTemplate = restTemplate;
        this.productRequestsBaseUrl  = fakeStoreApiUrl + fakeStoreProductsApiPath;
        this.specificProductRequestUrl = productRequestsBaseUrl + "/{id}";
    }

    public List<FakeStoreProductDto> getAllProducts() {
        ResponseEntity<FakeStoreProductDto[]> res = restTemplate.getForEntity(productRequestsBaseUrl, FakeStoreProductDto[].class);
        return Arrays.stream(res.getBody()).collect(Collectors.toList());
    }

    public FakeStoreProductDto getSingleProduct(Long id) throws NotFoundException {
        ResponseEntity<FakeStoreProductDto> resp = restTemplate.getForEntity(productRequestsBaseUrl + "/" + id, FakeStoreProductDto.class);
        FakeStoreProductDto fakeStoreProductDto = resp.getBody();
        if (fakeStoreProductDto == null) {
            throw new NotFoundException("Product with id: " + id + " doesn't exist.");
        }
        return  fakeStoreProductDto;
    }

    public FakeStoreProductDto deleteProduct(Long id) {
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor =
                restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.execute(specificProductRequestUrl, HttpMethod.DELETE,
                requestCallback, responseExtractor, id);

        return response.getBody();
    }

    public FakeStoreProductDto createProduct(GenericProductDto product) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(
                productRequestsBaseUrl, product, FakeStoreProductDto.class
        );
        return response.getBody();
    }

//    public GenericProductDto getSingleProduct(Long id) {
//        ResponseEntity<FakeStoreProductDto> res = restTemplate.getForEntity(productRequestsBaseUrl + "/" + id, FakeStoreProductDto.class);
//        // Convert the fetched product to a GenericProductDto
//        FakeStoreProductDto fakeProduct = res.getBody();
//        return convertToGenericProductDto(fakeProduct);
//    }

}
