package com.ClientManager.clientcrud.service;

import com.ClientManager.clientcrud.models.ClientModels;
import com.ClientManager.clientcrud.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class ClientService {

    public ClientModels findById (Long id){
        return clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    public ClientModels findByEmail(String email) {
        return clientRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    public ClientModels createClient(ClientModels newClient) {

        if (clientRepository.findByEmail(newClient.getEmail()).isPresent()) {
            throw new RuntimeException("Cliente já existe com o email: " + newClient.getEmail());
        }

        try {
            ClientModels endereco = CepServiceUtil.getEndereco(newClient.getCep());
            if (endereco != null) {
                newClient.setLogradouro(endereco.getLogradouro());
                newClient.setBairro(endereco.getBairro());
                newClient.setLocalidade(endereco.getLocalidade());
                newClient.setEstado(endereco.getEstado());
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao buscar informações de endereço para o CEP: " + newClient.getCep(), e);
        }

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
                ClientModels endereco = CepServiceUtil.getEndereco(upclient.getCep());
                if (endereco != null) {
                    client.setLogradouro(endereco.getLogradouro());
                    client.setBairro(endereco.getBairro());
                    client.setLocalidade(endereco.getLocalidade());
                    client.setEstado(endereco.getEstado());
                }
            } catch (IOException e) {
                throw new RuntimeException("Erro ao buscar informações de endereço para o CEP: " + upclient.getCep(), e);
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
