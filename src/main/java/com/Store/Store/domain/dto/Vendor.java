package com.Store.Store.domain.dto;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "vendors")
@Builder(toBuilder = true)
@AllArgsConstructor
public class Vendor {

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "nit", nullable = false)
    private String nit;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "phone", nullable = false)
    private String phone;
    @Column(name = "created_date", nullable = false)
    private String createdDate;
    @Column(name = "updated_date", nullable = false)
    private String updatedDate;
}
