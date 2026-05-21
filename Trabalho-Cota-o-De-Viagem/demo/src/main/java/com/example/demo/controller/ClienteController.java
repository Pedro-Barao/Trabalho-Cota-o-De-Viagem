package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ClienteDTO;
import com.example.demo.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Clientes", description = "Endpoints para gerenciamento de clientes")
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    
    @PostMapping
    public ResponseEntity<ClienteDTO> cadastrar(@Valid @RequestBody ClienteDTO clienteDTO) {
        ClienteDTO clienteSalvo = clienteService.cadastrarCliente(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarTodos() {
        List<ClienteDTO> lista = clienteService.listarTodos();
        return ResponseEntity.ok(lista);
    }
   
    @Operation(summary = "Busca um cliente por ID", description = "Retorna os detalhes de um cliente")
    @GetMapping("/{email}")
    public ResponseEntity<ClienteDTO> buscarPorid(@PathVariable @NonNull Long id) {
        
        Optional<ClienteDTO> clienteDTO = clienteService.buscarPorId(id);
        
        return clienteDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
}
