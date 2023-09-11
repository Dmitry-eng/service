package com.service.service;

import com.service.dto.client.Client;
import com.service.dto.client.ClientCreate;
import com.service.dto.client.ClientUpdate;

import java.util.List;

public interface ClientService {
    void createClient(ClientCreate clientCreate);

    List<Client> findAll(String value);

    Client findClientById(Long id);

    void updateClient(ClientUpdate clientUpdate);
}
