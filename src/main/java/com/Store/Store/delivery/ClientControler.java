package com.Store.Store.delivery;


import com.Store.Store.application.ClientService;
import com.Store.Store.domain.Client;
import com.Store.Store.domain.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

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


     @PostMapping("/create")
     public ResponseMessage save(@RequestBody Client client) {
         return clientService.save(client);
     }
}
