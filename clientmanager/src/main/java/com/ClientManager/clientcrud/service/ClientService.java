package com.ClientManager.clientcrud.service;

import com.ClientManager.clientcrud.exceptions.CepInvalidoException;
import com.ClientManager.clientcrud.exceptions.EmailAlreadyExistsException;
import com.ClientManager.clientcrud.models.ClientModels;
import com.ClientManager.clientcrud.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class ClientService {

    public ClientModels findByEmail(String email) {
        return clientRepository.findByEmail(email)
                .orElseThrow(() -> new EmailAlreadyExistsException(email));
    }
    public List<ClientModels> fetchClients() {
        return clientRepository.findAll();
    }

    public ClientModels createClient(ClientModels newClient) {
        if (clientRepository.findByEmail(newClient.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException(newClient.getEmail());
        }

        try {
            newClient.validateCep();

            ClientModels endereco = CepServiceUtil.getCep(newClient.getCep());
            if (endereco != null) {
                newClient.setLogradouro(endereco.getLogradouro());
                newClient.setBairro(endereco.getBairro());
                newClient.setLocalidade(endereco.getLocalidade());
                newClient.setEstado(endereco.getEstado());
            }
        } catch (CepInvalidoException e) {
            throw new RuntimeException("Erro ao buscar informações de endereço para o CEP: " + newClient.getCep() + ". CEP inválido.", e);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao acessar o serviço de CEP para o CEP: " + newClient.getCep(), e);
        }
        newClient.validate();


        return clientRepository.save(newClient);
    }


    public ClientModels updateClient(String email, ClientModels upclient) {
        ClientModels client = findByEmail(email);

        Optional.ofNullable(upclient.getNome()).ifPresent(client::setNome);
        Optional.ofNullable(upclient.getEmail()).ifPresent(client::setEmail);
        Optional.ofNullable(upclient.getTelefone()).ifPresent(client::setTelefone);


        if (upclient.getCep() != null && !upclient.getCep().isEmpty()) {
            client.setCep(upclient.getCep());

            try {
                ClientModels endereco = CepServiceUtil.getCep(upclient.getCep());
                if (endereco != null) {
                    client.setLogradouro(endereco.getLogradouro());
                    client.setBairro(endereco.getBairro());
                    client.setLocalidade(endereco.getLocalidade());
                    client.setEstado(endereco.getEstado());
                }
            } catch (CepInvalidoException e) {
                throw new RuntimeException("Erro ao buscar informações de endereço para o CEP: " + upclient.getCep() + ". CEP inválido.", e);
            } catch (IOException e) {
                throw new RuntimeException("Erro ao acessar o serviço de CEP para o CEP: " + upclient.getCep(), e);
            }
        } else {
            Optional.ofNullable(upclient.getLogradouro()).ifPresent(client::setLogradouro);
            Optional.ofNullable(upclient.getBairro()).ifPresent(client::setBairro);
            Optional.ofNullable(upclient.getLocalidade()).ifPresent(client::setLocalidade);
            Optional.ofNullable(upclient.getEstado()).ifPresent(client::setEstado);
        }

        return clientRepository.save(client);
    }


    public void deleteClient(String email) {
        ClientModels delClient = findByEmail(email);
        clientRepository.delete(delClient);
    }

    @Autowired
    private ClientRepository clientRepository;
}
