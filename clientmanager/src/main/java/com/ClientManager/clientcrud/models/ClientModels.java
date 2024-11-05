package com.ClientManager.clientcrud.models;

import com.ClientManager.clientcrud.exceptions.FieldRequiredException;
import jakarta.persistence.*;

@Entity
@Table(name = "Clientes")
public class ClientModels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    private String telefone;

    @Column(nullable = false)
    private String cep;

    @Column(nullable = false)
    private String logradouro;

    @Column(nullable = false)
    private String bairro;

    @Column(nullable = false)
    private String localidade;

    @Column(nullable = false)
    private String estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    // Método para validar campos obrigatórios
    public void validate() {
        if (nome == null || nome.trim().isEmpty()) {
            throw new FieldRequiredException("nome");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new FieldRequiredException("email");
        }
        if (cep == null || cep.trim().isEmpty()) {
            throw new FieldRequiredException("cep");
        }
        if (logradouro == null || logradouro.trim().isEmpty()) {
            throw new FieldRequiredException("logradouro");
        }
        if (bairro == null || bairro.trim().isEmpty()) {
            throw new FieldRequiredException("bairro");
        }
        if (localidade == null || localidade.trim().isEmpty()) {
            throw new FieldRequiredException("localidade");
        }
        if (estado == null || estado.trim().isEmpty()) {
            throw new FieldRequiredException("estado");
        }
    }

    public void validateCep() {
        if (cep == null || cep.trim().isEmpty()) {
            throw new FieldRequiredException("cep");
        }
    }


    @Override
    public String toString() {
        return "ClientModels{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", cep='" + cep + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + localidade + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
