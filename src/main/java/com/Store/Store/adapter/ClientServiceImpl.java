package com.Store.Store.adapter;

import com.Store.Store.application.ClientService;
import com.Store.Store.domain.Client;
import com.Store.Store.domain.ResponseMessage;
import com.Store.Store.repository.ClientRepository;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public ResponseMessage save(Client client) {
        log.info("Guardando cliente: {}", client);
        Client savedClient = null;
        String message;
        if (validateClient(client.getDocumentType(), client.getDocumentNumber())) {
            message = "Cliente ya existe";
            log.info(message);
        } else {
            message = "Cliente guardado exitosamente";
            savedClient = clientRepository.save(client.toBuilder()
                    .id(client.getId() == null || client.getId().isEmpty() ? UUID.randomUUID().toString() : client.getId())
                    .createdDate(client.getCreatedDate() == null || client.getCreatedDate().isEmpty() ? String.valueOf(System.currentTimeMillis()) : client.getCreatedDate())
                    .build());
            log.info(message + ": {}", savedClient);
        }
        return new ResponseMessage(message, savedClient);
    }



    @Override
    public Client findByDocumentTypeAndDocumentNumber(String documentType, String documentNumber) {
        return clientRepository.findByDocumentTypeAndDocumentNumber(documentType, documentNumber);
    }

    public boolean validateClient(String documentType, String documentNumber) {
        return clientRepository.findByDocumentTypeAndDocumentNumber(documentType, documentNumber) != null;
    }
}
