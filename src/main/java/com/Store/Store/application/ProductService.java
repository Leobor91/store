package com.Store.Store.application;

import com.Store.Store.domain.dto.Product;
import com.Store.Store.domain.dto.ResponseMessage;
import reactor.core.publisher.Flux;

public interface ProductService {

    Flux<Product>findAll();

    ResponseMessage save(Product product);

    ResponseMessage updateProduct(Product product);


}
