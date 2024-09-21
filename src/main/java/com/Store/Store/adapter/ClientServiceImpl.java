package com.Store.Store.adapter;

import com.Store.Store.application.ClientService;
import com.Store.Store.domain.dto.Client;
import com.Store.Store.domain.dto.ResponseMessage;
import com.Store.Store.domain.enums.ClientEnum;
import com.Store.Store.repository.ClientRepository;
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
        Client savedClient = clientRepository.findByDocumentTypeAndDocumentNumber(client.getDocumentType(), client.getDocumentNumber());
        if (savedClient == null) {
            savedClient = clientRepository.save(client.toBuilder()
                    .id(client.getId() == null || client.getId().isEmpty() ? UUID.randomUUID().toString() : client.getId())
                    .createdDate(client.getCreatedDate() == null || client.getCreatedDate().isEmpty() ? String.valueOf(System.currentTimeMillis()) : client.getCreatedDate())
                    .updatedDate(String.valueOf(System.currentTimeMillis()))
                    .build());
            log.info("{}: {}", ClientEnum.CLIENT_SAVED_SUCCESSFULLY.getValue(), savedClient);
            return new ResponseMessage(ClientEnum.CLIENT_SAVED_SUCCESSFULLY.getValue(), savedClient);
        } else {
            log.info(ClientEnum.CLIENT_ALREADY_EXISTS.getValue());
            return new ResponseMessage(ClientEnum.CLIENT_ALREADY_EXISTS.getValue(), null);
        }
    }

    @Override
    public ResponseMessage updateClient(Client client) {
        log.info("Actualizando cliente con tipo de documento: {} y numero de documento {} ", client.getDocumentType(), client.getDocumentNumber());
        Client existingClient = clientRepository.findByDocumentTypeAndDocumentNumber(client.getDocumentType(), client.getDocumentNumber());
        if (existingClient != null) {
            log.info("Cliente encontrado: {}", existingClient);
            client.toBuilder()
                    .id(existingClient.getId())
                    .createdDate(existingClient.getCreatedDate())
                    .updatedDate(String.valueOf(System.currentTimeMillis()))
                    .build();
            existingClient = clientRepository.save(client);
            log.info("{}: {}", ClientEnum.CLIENT_UPDATED_SUCCESSFULLY.getValue(), client);
            return new ResponseMessage(ClientEnum.CLIENT_UPDATED_SUCCESSFULLY.getValue(), existingClient);
        } else {
            log.info(ClientEnum.CLIENT_NOT_FOUND.getValue());
            return new ResponseMessage(ClientEnum.CLIENT_NOT_FOUND.getValue(), null);
        }
    }
}
