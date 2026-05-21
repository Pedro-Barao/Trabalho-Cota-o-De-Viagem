package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.ClienteDTO;
import com.example.demo.mapper.ClienteMapper;
import com.example.demo.repository.IClienteRepository;
import com.example.demo.Entities.Cliente;

@Service
public class ClienteService {

    @Autowired
    private IClienteRepository clienteRepository;

    @Autowired
    private ClienteMapper clienteMapper; 

   
    public ClienteDTO cadastrarCliente(ClienteDTO clienteDTO) {
        if (clienteRepository.findByEmail(clienteDTO.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe um cliente com este e-mail.");
        }
        if (clienteRepository.findByDocumento(clienteDTO.getDocumento()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe um cliente com este documento.");
        }

        Cliente novoCliente = clienteMapper.toEntity(clienteDTO);
        @SuppressWarnings("null")
        Cliente clienteSalvo = clienteRepository.save(novoCliente);
        return clienteMapper.toDTO(clienteSalvo);
    }

    
    public List<ClienteDTO> listarTodos() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(clienteMapper::toDTO)
                .collect(Collectors.toList());
                
    }

    public Optional<ClienteDTO> buscarPorId(@NonNull Long id)
    {

        return clienteRepository.findById(id).map(clienteMapper::toDTO);
        
    }
    
}
