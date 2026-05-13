package com.example.demo.mapper;

import org.springframework.stereotype.Component;

import com.example.demo.dto.ClienteDTO;
import com.example.demo.Entities.Cliente;

@Component 
public class ClienteMapper {

    
    public Cliente toEntity(ClienteDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefone(dto.getTelefone());
        cliente.setDocumento(dto.getDocumento());
        
        return cliente;
    }

    
    public ClienteDTO toDTO(Cliente cliente) {
        if (cliente == null) {
            return null;
        }
        
        ClienteDTO dto = new ClienteDTO();
        dto.setId(cliente.getId());
        dto.setNome(cliente.getNome());
        dto.setEmail(cliente.getEmail());
        dto.setTelefone(cliente.getTelefone());
        dto.setDocumento(cliente.getDocumento());
        
        return dto;
    }
}