package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entities.Desconto;
import com.example.demo.dto.DescontoDTO;
import com.example.demo.mapper.DescontoMapper;
import com.example.demo.repository.IDescontoRepository;

@Service
public class DescontoService {
    
    @Autowired
    private IDescontoRepository descontoRepository;

    @Autowired 
    private DescontoMapper descontoMapper;

    public List<DescontoDTO> listarTodos() 
    {

        return descontoMapper.toDTOList(descontoRepository.findAll());
        
    }

    public Optional<DescontoDTO> buscarPorId(Long id) 
    {

        return descontoRepository.findById(id).map(descontoMapper::toDTO);
        
    }

    public DescontoDTO salvar(DescontoDTO descontoDTO)
    {

        Desconto desconto = descontoMapper.toEntity(descontoDTO);
        
        return descontoMapper.toDTO(descontoRepository.save(desconto));
        
    }

    public void deletar(Long id)
    {

        descontoRepository.deleteById(id);
        
    }

}
