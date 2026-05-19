package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Entities.Pagamento;
import com.example.demo.dto.PagamentoDTO;
import com.example.demo.mapper.PagamentoMapper;
import com.example.demo.repository.IPagamentoRepository;

@Service
public class PagamentoService {

    @Autowired
    private IPagamentoRepository pagamentoRepository;

    @Autowired
    private PagamentoMapper pagamentoMapper;

    public List<PagamentoDTO> listarTodos() {
        return pagamentoMapper.toDTOList(pagamentoRepository.findAll());
    }

    public Optional<PagamentoDTO> buscarPorId(Long id) {
        return pagamentoRepository.findById(id).map(pagamentoMapper::toDTO);
    }

    public PagamentoDTO salvar(PagamentoDTO pagamentoDTO) {
        Pagamento pagamento = pagamentoMapper.toEntity(pagamentoDTO);
        return pagamentoMapper.toDTO(pagamentoRepository.save(pagamento));
    }

    public PagamentoDTO atualizarStatus(Long id, String novoStatus) {
        return pagamentoRepository.findById(id).map(pagamento -> {
            pagamento.setStatus(novoStatus);
            return pagamentoMapper.toDTO(pagamentoRepository.save(pagamento));
        }).orElseThrow(() -> new IllegalArgumentException("Pagamento não encontrado com o ID: " + id));
    }

    public void deletar(Long id) {
        pagamentoRepository.deleteById(id);
    }
}