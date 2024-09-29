package com.Store.Store.adapter;


import com.Store.Store.application.ProductService;
import com.Store.Store.domain.dto.Client;
import com.Store.Store.domain.dto.Product;
import com.Store.Store.domain.dto.ResponseMessage;
import com.Store.Store.domain.enums.ProductEnum;
import com.Store.Store.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    @Override
    public Flux<Product> findAll() {
        return Flux.fromIterable(productRepository.findAll());
    }

    @Override
    public ResponseMessage save(Product product) {
        log.info("Guardando producto: {}", product);
        return Mono.just(product)
                .map(savedProduct -> {
                    log.info(ProductEnum.PRODUCT_SAVED_SUCCESSFULLY.getValue());
                    return ResponseMessage.builder()
                            .message(ProductEnum.PRODUCT_SAVED_SUCCESSFULLY.getValue())
                            .object(productRepository.save(product.toBuilder()
                                    .id(product.getId() == null || product.getId().isEmpty() ? UUID.randomUUID().toString() : product.getId())
                                    .build()))
                            .build();
                })
                .block();
    }

    @Override
    public ResponseMessage updateProduct(Product product) {
        log.info("Actualizando producto: {}", product);
        return Mono.just(product)
                .map(updatedProduct -> {
                    log.info(ProductEnum.PRODUCT_UPDATED_SUCCESSFULLY.getValue());
                    return ResponseMessage.builder()
                            .message(ProductEnum.PRODUCT_UPDATED_SUCCESSFULLY.getValue())
                            .object(productRepository.save(product))
                            .build();
                })
                .block();
    }




}
