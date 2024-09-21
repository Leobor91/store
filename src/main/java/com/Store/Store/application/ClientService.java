package com.Store.Store.application;

import com.Store.Store.domain.dto.Client;
import com.Store.Store.domain.dto.ResponseMessage;

import java.util.List;

public interface ClientService {

    List<Client> findAll();

    ResponseMessage save(Client client);

    ResponseMessage updateClient(Client client);
}
