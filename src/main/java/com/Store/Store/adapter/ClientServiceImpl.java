package com.Store.Store.adapter;

import com.Store.Store.application.ClientService;
import com.Store.Store.domain.Client;
import com.Store.Store.repository.ClientRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client findByDocumentTypeAndDocumentNumber(String documentType, String documentNumber) {
        return clientRepository.findByDocumentTypeAndDocumentNumber(documentType, documentNumber);
    }
}
