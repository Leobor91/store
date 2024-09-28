package com.Store.Store.application;

import com.Store.Store.domain.dto.Client;
import com.Store.Store.domain.dto.ResponseMessage;
import reactor.core.publisher.Flux;

import java.util.List;

public interface ClientService {

    Flux<Client> findAll();

    ResponseMessage save(Client client);

    ResponseMessage updateClient(Client client);

    ResponseMessage deleteClient(String documentType, String documentNumber);
}
