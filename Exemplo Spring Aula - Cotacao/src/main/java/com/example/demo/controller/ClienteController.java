package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ClienteDTO;
import com.example.demo.service.ClienteService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/api/clientes") 
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

   
    @PostMapping
    public ResponseEntity<ClienteDTO> cadastrar(@Valid @RequestBody ClienteDTO clienteDTO) {
        
       
        ClienteDTO clienteSalvo = clienteService.cadastrarCliente(clienteDTO);
        
       
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
    }
}