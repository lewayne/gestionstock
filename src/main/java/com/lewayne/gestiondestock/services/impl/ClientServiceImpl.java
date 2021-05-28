package com.lewayne.gestiondestock.services.impl;

import com.lewayne.gestiondestock.dto.ClientDTO;
import com.lewayne.gestiondestock.exception.EntityNotFoundException;
import com.lewayne.gestiondestock.exception.ErrorCodes;
import com.lewayne.gestiondestock.exception.InvalidEntityException;
import com.lewayne.gestiondestock.model.Categorie;
import com.lewayne.gestiondestock.model.Client;
import com.lewayne.gestiondestock.repository.ClientRepository;
import com.lewayne.gestiondestock.services.ClientService;
import com.lewayne.gestiondestock.validator.ClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {
    private ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientDTO save(ClientDTO clientDTO) {
        List<String> errors = ClientValidator.validate(clientDTO);
        if(!errors.isEmpty()){
            log.error("cllient is not valid", errors);
            throw new InvalidEntityException("Le client n'est pas vadide", ErrorCodes.CLIENT_NOT_VALID, errors);
        }

        Client client = clientRepository.save(ClientDTO.toEntity(clientDTO));

        return ClientDTO.fromEntity(client);
    }

    @Override
    public ClientDTO findByNom(String name) {
        if(!StringUtils.hasLength(name)){
            log.error("customer NAME is null");
            return null;
        }

        Optional<Client> client = clientRepository.findClientByNom(name);
        ClientDTO clientDTO = ClientDTO.fromEntity(client.get());

        return Optional.of(clientDTO).orElseThrow(()-> new EntityNotFoundException(
                "Le client dont le nom est " +  name +  "n'a pas été retrouvé", ErrorCodes.CLIENT_NOT_FOUND
                )
        );
    }

    @Override
    public ClientDTO findById(Integer id) {
        if(id == null){
            log.error("ID is null");
            return null;
        }
        Optional<Client> client = clientRepository.findClientById(id);
        ClientDTO clientDTO = ClientDTO.fromEntity(client.get());

        return Optional.of(clientDTO).orElseThrow(()-> new EntityNotFoundException(
                        "Le client dont le ID est " +  id +  "n'a pas été retrouvé", ErrorCodes.CLIENT_NOT_FOUND
                )
        );
    }

    @Override
    public List<ClientDTO> findAll() {
        return clientRepository.findAll().stream()
                .map(ClientDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
           if(id==null){
               log.error("ID is null");
           }
           clientRepository.deleteById(id);
    }
}
