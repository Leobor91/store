package com.Store.Store.delivery;

import com.Store.Store.application.ProductService;
import com.Store.Store.domain.dto.Product;
import com.Store.Store.domain.dto.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@CrossOrigin(origins = {"http://localhost:3000", "https://lstoreapp.netlify.app", "https://store-customer-app-react.netlify.app","http://localhost:3001"})
@Service
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/list")
    public Flux<Product> findAll() {
        return productService.findAll();
    }

    @PostMapping("/create")
    public ResponseMessage save(@RequestBody Product product) {
        return productService.save(product);
    }

    @PutMapping("/update")
    public ResponseMessage updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }


}
