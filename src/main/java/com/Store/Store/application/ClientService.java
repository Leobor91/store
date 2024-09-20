package com.Store.Store.application;

import com.Store.Store.domain.Client;

import java.util.List;

public interface ClientService {

    List<Client> findAll();

    Client save(Client client);

    Client findByDocumentTypeAndDocumentNumber(String documentType, String documentNumber);
}
