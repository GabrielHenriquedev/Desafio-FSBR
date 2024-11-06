package com.ClientManager.clientcrud.controller;

import com.ClientManager.clientcrud.models.ClientModels;
import com.ClientManager.clientcrud.repository.ClientRepository;
import com.ClientManager.clientcrud.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class ClientController {

    @GetMapping("/")
    public ModelAndView getCliente() {
        List<ClientModels> clients = clientService.fetchClients();

        ModelAndView mv = new ModelAndView("cadastro/usuarios");
        mv.addObject("clientes", clients);
        mv.addObject("cliente", new ClientModels());
        return mv;
    }

    @PostMapping("/")
    public ModelAndView cadastroCliente(ClientModels cliente) {
        ModelAndView mvc = new ModelAndView();

        clientService.createClient(cliente);
        mvc.setViewName("redirect:/");
        return mvc;
    }

    @GetMapping("/editar/{email}")
    public ModelAndView editarClienteForm(@PathVariable("email") String email) {
        ClientModels cliente = clientService.findByEmail(email);

        if (cliente == null) {
            ModelAndView mv = new ModelAndView("erro");
            mv.addObject("message", "Cliente não encontrado!");
            return mv;
        }
        ModelAndView mv = new ModelAndView("cadastro/editar");
        mv.addObject("cliente", cliente);
        return mv;
    }

    @PostMapping("/editar/{email}")
    public ModelAndView editarCliente(@PathVariable("email") String email, ClientModels cliente) {
        clientService.updateClient(email, cliente);

        return new ModelAndView("redirect:/");
    }

    @GetMapping("/deletar/{email}")
    public ModelAndView excluirCliente(@PathVariable("email") String email){
        clientService.deleteClient(email);

        return new ModelAndView("redirect:/");
    }

    @Autowired
    private ClientService clientService;
}
