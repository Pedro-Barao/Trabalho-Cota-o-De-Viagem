package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entities.Destinos;
import com.example.demo.dto.DestinoDTO;
import com.example.demo.mapper.DestinoMapper;
import com.example.demo.repository.IDestinoRepository;


@Service
public class DestinoService {
    
    @Autowired
    private IDestinoRepository destinoRepository;

    @Autowired
    private DestinoMapper destinoMapper;

    public List<DestinoDTO> listarTodos()
    {

        return destinoMapper.toDTOList(destinoRepository.findAll());

    }

    public Optional<DestinoDTO> buscarPorId(Long id)
    {

        return destinoRepository.findById(id).map(destinoMapper::toDTO);
        
    }

    public DestinoDTO salvar(DestinoDTO destinoDTO)
    {

        Destinos destino = destinoMapper.toEntity(destinoDTO);
        
        return destinoMapper.toDTO(destinoRepository.save(destino));
        
    }

    public Optional<DestinoDTO> atualizar(Long id, DestinoDTO destinoDTO)
    {

        return destinoRepository.findById(id).map(destinoExistente -> {

            destinoExistente.setNome(destinoDTO.getNome());
            destinoExistente.setDescricao(destinoDTO.getDescricao());
            destinoExistente.setLocalizacao(destinoDTO.getLocalizacao());
            destinoExistente.setPrecoPorPessoa(destinoDTO.getPrecoPorPessoa());

            Destinos destinoAtualizado = destinoRepository.save(destinoExistente);

            return destinoMapper.toDTO(destinoAtualizado);

        });

    }

    public void deletar(Long id)
    {

        destinoRepository.deleteById(id);
        
    }

}