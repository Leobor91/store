package com.Store.Store.domain.dto;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "products")
@Builder(toBuilder = true)
@AllArgsConstructor
public class Product {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private byte status;

    @Column(name = "stock")
    private long stock;

    @Column(name = "precio_costo")
    private String precioCosto;

    @Column(name = "precio_venta")
    private String precioVenta;

    @Column(name = "vendor_id")
    private String vendorId;

    @Column(name = "image")
    private String image;
}