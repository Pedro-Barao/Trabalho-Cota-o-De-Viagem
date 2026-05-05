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

    public PagamentoDTO salvar(PagamentoDTO dto) {
        Pagamento entidade = pagamentoMapper.toEntity(dto);
        return pagamentoMapper.toDTO(pagamentoRepository.save(entidade));
    }

    public Optional<PagamentoDTO> buscarPorId(Long id) {
        return pagamentoRepository.findById(id).map(pagamentoMapper::toDTO);
    }

    public PagamentoDTO atualizarStatus(Long id, String status) {
        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));
        pagamento.setStatus(status);
        return pagamentoMapper.toDTO(pagamentoRepository.save(pagamento));
    }

    public void deletar(Long id) {
        pagamentoRepository.deleteById(id);
    }
}