package com.ClientManager.clientcrud.repository;

import com.ClientManager.clientcrud.models.ClientModels;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<ClientModels, Long> {
    Optional<ClientModels> findByEmail(String email);

}
