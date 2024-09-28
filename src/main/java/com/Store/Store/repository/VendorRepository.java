package com.Store.Store.repository;

import com.Store.Store.domain.dto.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, String> {

    Vendor findByNit(String nit);
}
