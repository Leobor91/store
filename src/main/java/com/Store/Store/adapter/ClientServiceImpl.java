package com.Store.Store.adapter;

import com.Store.Store.application.ClientService;
import com.Store.Store.domain.dto.Client;
import com.Store.Store.domain.dto.ResponseMessage;
import com.Store.Store.domain.dto.Vendor;
import com.Store.Store.domain.enums.ClientEnum;
import com.Store.Store.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public Flux<Client> findAll() {
        return Flux.fromIterable(clientRepository.findAll());
    }

    @Override
    public ResponseMessage save(Client client) {
        log.info("Guardando cliente: {}", client);
        return Mono.just(validateClient(clientRepository.findByDocumentTypeAndDocumentNumber(client.getDocumentType(), client.getDocumentNumber())))
                .filter(savedClient -> savedClient.getDocumentType().equalsIgnoreCase(client.getDocumentType()) && savedClient.getDocumentNumber().equalsIgnoreCase(client.getDocumentNumber()))
                .map(savedClient -> {
                    log.info(ClientEnum.CLIENT_ALREADY_EXISTS.getValue());
                    return ResponseMessage.builder()
                            .message(ClientEnum.CLIENT_ALREADY_EXISTS.getValue())
                            .object(null)
                            .build();
                })
                .switchIfEmpty(Mono.defer(() -> {
                    log.info(ClientEnum.CLIENT_SAVED_SUCCESSFULLY.getValue());
                    return Mono.just(ResponseMessage.builder()
                            .message(ClientEnum.CLIENT_SAVED_SUCCESSFULLY.getValue())
                            .object(clientRepository.save(client.toBuilder()
                                    .id(client.getId() == null || client.getId().isEmpty() ? UUID.randomUUID().toString() : client.getId())
                                    .createdDate(client.getCreatedDate() == null || client.getCreatedDate().isEmpty() ? String.valueOf(System.currentTimeMillis()) : client.getCreatedDate())
                                    .updatedDate(String.valueOf(System.currentTimeMillis()))
                                    .build()))
                            .build());
                }))
                .block();
    }

    @Override
    public ResponseMessage updateClient(Client client) {
        log.info("Actualizando cliente con tipo de documento: {} y numero de documento {} ", client.getDocumentType(), client.getDocumentNumber());

        return Mono.just(validateClient(clientRepository.findByDocumentTypeAndDocumentNumber(client.getDocumentType(), client.getDocumentNumber())))
                .filter(client1 -> client1.getDocumentType().equalsIgnoreCase(client.getDocumentType()) && client1.getDocumentNumber().equalsIgnoreCase(client.getDocumentNumber()))
                .map(client1 -> {
                    log.info(ClientEnum.CLIENT_UPDATED_SUCCESSFULLY.getValue());
                    return ResponseMessage.builder()
                            .message(ClientEnum.CLIENT_NOT_FOUND.getValue())
                            .object(clientRepository.save(client1.toBuilder()
                                    .name(client.getName())
                                    .lastName(client.getLastName())
                                    .email(client.getEmail())
                                    .phone(client.getPhone())
                                    .updatedDate(String.valueOf(System.currentTimeMillis()))
                                    .build())
                            )
                            .build();
                })
                .switchIfEmpty(Mono.defer(() -> {
                    return Mono.just(ResponseMessage.builder()
                            .message(ClientEnum.CLIENT_NOT_FOUND.getValue())
                            .object(null)
                            .build());
                }))
                .block();
    }

    @Override
    public ResponseMessage deleteClient(String documentType, String documentNumber) {
        log.info("Eliminando cliente con tipo de documento: {} y número de documento: {}", documentType, documentNumber);

        return Mono.just(validateClient(clientRepository.findByDocumentTypeAndDocumentNumber(documentType, documentNumber)))
                .filter(client -> !client.getId().isEmpty())
                .map(client -> {
                    clientRepository.delete(client);
                    log.info(ClientEnum.CLIENT_DELETED_SUCCESSFULLY.getValue());
                    return ResponseMessage.builder()
                            .message(ClientEnum.CLIENT_DELETED_SUCCESSFULLY.getValue())
                            .object(client)
                            .build();
                })
                .switchIfEmpty(Mono.defer(() -> {
                    log.info(ClientEnum.CLIENT_NOT_FOUND.getValue());
                    return Mono.just(ResponseMessage.builder()
                            .message(ClientEnum.CLIENT_NOT_FOUND.getValue())
                            .object(null)
                            .build());
                }))
                .block();
    }

    public Client validateClient(Client client) {
        if (client == null) {
            return Client.builder()
                    .id("")
                    .name("")
                    .lastName("")
                    .email("")
                    .phone("")
                    .documentType("")
                    .documentNumber("")
                    .createdDate("")
                    .updatedDate("")
                    .build();
        } else {
            return client;
        }
    }
}

