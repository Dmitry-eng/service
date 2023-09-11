package com.service.controller;

import com.service.dto.client.Client;
import com.service.dto.client.ClientCreate;
import com.service.dto.client.ClientUpdate;
import com.service.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public void createClient(@RequestBody @Valid ClientCreate clientCreate) {
        clientService.createClient(clientCreate);
    }

    @GetMapping({"/all/{value}", "/all"})
    public List<Client> findAllClient(@PathVariable(required = false) String value) {
        return clientService.findAll(value);
    }

    @GetMapping("/{id}")
    public Client findClientById(@PathVariable Long id) {
        return clientService.findClientById(id);
    }

    @PutMapping
    public void updateClient(@RequestBody @Valid ClientUpdate clientUpdate) {
        clientService.updateClient(clientUpdate);
    }

}
