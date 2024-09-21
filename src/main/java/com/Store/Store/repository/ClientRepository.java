package com.Store.Store.repository;


import com.Store.Store.domain.dto.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {

    Client findByDocumentTypeAndDocumentNumber(String documentType, String documentNumber);


}
