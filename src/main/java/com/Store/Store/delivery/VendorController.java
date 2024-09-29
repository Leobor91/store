package com.Store.Store.delivery;

import com.Store.Store.application.VendorService;
import com.Store.Store.domain.dto.ResponseMessage;
import com.Store.Store.domain.dto.Vendor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@CrossOrigin(origins = {"http://localhost:3000", "https://lstoreapp.netlify.app", "https://store-customer-app-react.netlify.app","http://localhost:3001"})
@Service
@RestController
@RequestMapping("/api/vendors")
@RequiredArgsConstructor
public class VendorController {

    private final VendorService vendorService;

    @GetMapping("/list")
    public Flux<Vendor> findAll() {
        return vendorService.findAll();
    }

    @PostMapping("/create")
    public ResponseMessage save(@RequestBody Vendor vendor) {
        return vendorService.save(vendor);
    }

    @PutMapping("/update")
    public ResponseMessage updateVendor(@RequestBody Vendor vendor) {
        return vendorService.updateVendor(vendor);
    }

    @DeleteMapping("/delete/nit/{nit}")
    public ResponseMessage deleteVendor(@PathVariable String nit) {
        return vendorService.deleteVendor(nit);
    }
}
