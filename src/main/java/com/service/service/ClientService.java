package com.service.service;

import com.service.dto.account.Client;
import com.service.dto.account.ClientCreate;
import com.service.dto.account.ClientUpdate;

import java.util.List;

public interface ClientService {
    void createClient(ClientCreate clientCreate);

    List<Client> findAll(String value);

    Client findClientById(Long id);

    void updateClient(ClientUpdate clientUpdate);
}
