package com.Store.Store.application;

import com.Store.Store.domain.Client;
import com.Store.Store.domain.ResponseMessage;

import java.util.List;

public interface ClientService {

    List<Client> findAll();

    ResponseMessage save(Client client);

    Client findByDocumentTypeAndDocumentNumber(String documentType, String documentNumber);
}
