package com.Store.Store.delivery;


import com.Store.Store.application.ClientService;
import com.Store.Store.domain.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Service
@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientControler {


    private final ClientService clientService;

     @GetMapping("/list")
    public List<Client> findAll() {
         return clientService.findAll();
     }
}
