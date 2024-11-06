package com.ClientManager.clientcrud.controller;

import com.ClientManager.clientcrud.models.ClientModels;
import com.ClientManager.clientcrud.repository.ClientRepository;
import com.ClientManager.clientcrud.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiController {

    @GetMapping(path = "/clientes")
    public List<ClientModels> getClient (){
        return clientRepository.findAll();
    }

    @PostMapping(path = "/clientes")
    public ResponseEntity<ClientModels> postClient (@RequestBody ClientModels client){
        ClientModels createClient = clientService.createClient(client);

        return new ResponseEntity<>(createClient,  HttpStatus.CREATED);
    }

    @PutMapping(path = "/clientes/{email}")
    public ClientModels putClient (@PathVariable String email, @RequestBody ClientModels client){
        return clientService.updateClient(email, client);
    }

    @DeleteMapping("/clientes/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable String email){
        clientService.deleteClient(email);
    }

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientRepository clientRepository;
}
