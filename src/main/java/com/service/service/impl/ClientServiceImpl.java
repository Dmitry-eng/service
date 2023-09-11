package com.service.service.impl;

import com.service.dto.account.Client;
import com.service.dto.account.ClientCreate;
import com.service.dto.account.ClientUpdate;
import com.service.entity.ClientEntity;
import com.service.exception.ServiceException;
import com.service.mapper.ClientMapper;
import com.service.repository.ClientRepository;
import com.service.security.Session;
import com.service.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.service.other.criteria.ClientUtils.findByValue;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final Session session;

    @Override
    public void createClient(ClientCreate clientCreate) {
        ClientEntity client = clientMapper.map(clientCreate);
        client.setCreateBy(session.getAccountSession());
        clientRepository.save(client);
    }

    @Override
    public Client findClientById(Long id) {
        return clientRepository.findById(id)
                .map(clientMapper::map)
                .orElseThrow(() -> new ServiceException("Client not found"));
    }

    @Override
    public List<Client> findAll(String value) {
        List<ClientEntity> clientEntities = null;

        if (StringUtils.isEmpty(value)) {
            clientEntities = clientRepository.findAll();
        } else {
            clientEntities = clientRepository.findAll(findByValue(value));
        }

        return clientEntities.stream()
                .map(clientMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public void updateClient(ClientUpdate clientUpdate) {
        ClientEntity client = clientRepository.findById(clientUpdate.getId())
                .orElseThrow(() -> new ServiceException("Client not found"));

        client.setName(clientUpdate.getName());
        client.setEmail(clientUpdate.getEmail());
        client.setPhoneNumber(clientUpdate.getPhoneNumber());

        clientRepository.save(client);
    }
}