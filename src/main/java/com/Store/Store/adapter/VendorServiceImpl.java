package com.Store.Store.adapter;


import com.Store.Store.application.VendorService;
import com.Store.Store.domain.dto.ResponseMessage;
import com.Store.Store.domain.dto.Vendor;
import com.Store.Store.domain.enums.VendorEnum;
import com.Store.Store.repository.VendorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static org.apache.logging.log4j.ThreadContext.isEmpty;

@Slf4j
@Service
@RequiredArgsConstructor
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;


    public Flux<Vendor> findAll() {
        return Flux.fromIterable(vendorRepository.findAll());
    }

    @Override
    public ResponseMessage save(Vendor vendor) {
        log.info("Guardando proveedor: {}", vendor);
        return Mono.just(validateVendor(vendorRepository.findByNit(vendor.getNit())))
                .filter(savedVendor -> savedVendor.getNit().equalsIgnoreCase(vendor.getNit()))
                .map(savedVendor -> {
                    log.info(VendorEnum.VENDOR_ALREADY_EXISTS.getValue());
                    return ResponseMessage.builder()
                            .message(VendorEnum.VENDOR_ALREADY_EXISTS.getValue())
                            .object(null)
                            .build();
                })
                .switchIfEmpty(Mono.defer(() -> {
                    log.info(VendorEnum.VENDOR_SAVED_SUCCESSFULLY.getValue());
                    return Mono.just(ResponseMessage.builder()
                            .message(VendorEnum.VENDOR_SAVED_SUCCESSFULLY.getValue())
                            .object(vendorRepository.save(vendor.toBuilder()
                                    .id(vendor.getId() == null || vendor.getId().isEmpty() ? UUID.randomUUID().toString() : vendor.getId())
                                    .createdDate(vendor.getCreatedDate() == null || vendor.getCreatedDate().isEmpty() ? String.valueOf(System.currentTimeMillis()) : vendor.getCreatedDate())
                                    .updatedDate(String.valueOf(System.currentTimeMillis()))
                                    .build()))
                            .build());
                }))
                .block();
    }

    @Override
    public ResponseMessage updateVendor(Vendor vendor) {
        log.info("Actualizando proveedor con nit: {} ", vendor.getNit());
        return Mono.just(validateVendor(vendorRepository.findByNit(vendor.getNit())))
                .filter(existingVendor -> existingVendor.getNit().equalsIgnoreCase(vendor.getNit()))
                .map(existingVendor -> {
                    log.info(VendorEnum.VENDOR_UPDATED_SUCCESSFULLY.getValue());
                    return ResponseMessage.builder()
                            .message(VendorEnum.VENDOR_SAVED_SUCCESSFULLY.getValue())
                            .object(vendorRepository.save(existingVendor.toBuilder()
                                    .name(vendor.getName())
                                    .email(vendor.getEmail())
                                    .nit(vendor.getNit())
                                    .updatedDate(String.valueOf(System.currentTimeMillis()))
                                    .build()))
                            .build();
                })
                .switchIfEmpty(Mono.defer(() -> {
                    log.info(VendorEnum.VENDOR_ALREADY_EXISTS.getValue());
                    return Mono.just(ResponseMessage.builder()
                            .message(VendorEnum.VENDOR_ALREADY_EXISTS.getValue())
                            .object(null)
                            .build());
                }))
                .block();
    }

    @Override
    public ResponseMessage deleteVendor(String nit) {
        log.info("Eliminando proveedor con nit: {} ", nit);
        return Mono.just(validateVendor(vendorRepository.findByNit(nit)))
                .filter(vendor -> !vendor.getId().isEmpty())
                .map(vendor -> {
                    vendorRepository.delete(vendor);
                    log.info(VendorEnum.VENDOR_DELETED_SUCCESSFULLY.getValue());
                    return ResponseMessage.builder()
                            .message(VendorEnum.VENDOR_DELETED_SUCCESSFULLY.getValue())
                            .object(vendor)
                            .build();
                })
                .switchIfEmpty(Mono.defer(() -> {
                    log.info(VendorEnum.VENDOR_NOT_FOUND.getValue());
                    return Mono.just(ResponseMessage.builder()
                            .message(VendorEnum.VENDOR_NOT_FOUND.getValue())
                            .object(null)
                            .build());
                }))
                .block();
    }

    public Vendor validateVendor(Vendor vendor) {
        if (vendor == null) {
            return Vendor.builder()
                    .id("")
                    .name("")
                    .email("")
                    .nit("")
                    .createdDate("")
                    .updatedDate("")
                    .build();
        } else {
            return vendor;
        }
    }
}
