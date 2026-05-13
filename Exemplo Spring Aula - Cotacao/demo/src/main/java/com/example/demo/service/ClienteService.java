package com.example.demo.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.ClienteDTO;
import com.example.demo.mapper.ClienteMapper;
import com.example.demo.Entities.Cliente;
import com.example.demo.repository.IClienteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final IClienteRepository clienteRepository;
    

    private final ClienteMapper clienteMapper; 

    public ClienteDTO cadastrarCliente(ClienteDTO clienteDTO) {
        
     
        if (clienteRepository.findByEmail(clienteDTO.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe um cliente com este e-mail.");
        }

        if (clienteRepository.findByDocumento(clienteDTO.getDocumento()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe um cliente com este documento.");
        }

    
        Cliente novoCliente = clienteMapper.toEntity(clienteDTO);

      
        Cliente clienteSalvo = clienteRepository.save(novoCliente);

        
        return clienteMapper.toDTO(clienteSalvo);
    }
}