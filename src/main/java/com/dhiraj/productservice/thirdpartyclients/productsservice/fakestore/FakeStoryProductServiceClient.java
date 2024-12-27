package com.dhiraj.productservice.thirdpartyclients.productsservice.fakestore;

import com.dhiraj.productservice.dtos.FakeStoreProductDto;
import com.dhiraj.productservice.dtos.GenericProductDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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
//        List<FakeStoreProductDto> products = Arrays.asList(res.getBody());
        return Arrays.stream(res.getBody()).collect(Collectors.toList());
//        return List.of(Arrays.stream(res.getBody()).toArray(FakeStoreProductDto[]::new));
    }
}
