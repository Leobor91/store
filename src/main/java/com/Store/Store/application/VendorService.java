package com.Store.Store.application;

import com.Store.Store.domain.dto.ResponseMessage;
import com.Store.Store.domain.dto.Vendor;
import reactor.core.publisher.Flux;

public interface VendorService {

    Flux<Vendor> findAll();

    ResponseMessage save(Vendor vendor);

    ResponseMessage updateVendor(Vendor vendor);

    ResponseMessage deleteVendor(String nit);
}
