package com.example.demo.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.Entities.Pagamento;
import com.example.demo.dto.PagamentoDTO;
import com.example.demo.mapper.PagamentoMapper;
import com.example.demo.repository.ICotacaoRepository;
import com.example.demo.repository.IPagamentoRepository;

@Service
public class PagamentoService {

    @Autowired
    private IPagamentoRepository pagamentoRepository;

    @Autowired
    private ICotacaoRepository cotacaoRepository;

    @Autowired
    private PagamentoMapper pagamentoMapper;

    public List<PagamentoDTO> listarTodos() {
        return pagamentoMapper.toDTOList(pagamentoRepository.findAll());
    }

    @SuppressWarnings("null")
    public Optional<PagamentoDTO> buscarPorId(Long id) {
        return pagamentoRepository.findById(id).map(pagamentoMapper::toDTO);
    }

    @SuppressWarnings("null")
    public PagamentoDTO salvar(PagamentoDTO pagamentoDTO) {

        if (pagamentoDTO.getValorPago() == null
                || pagamentoDTO.getValorPago().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor pago deve ser maior que zero");
        }

        if (!cotacaoRepository.existsById(pagamentoDTO.getCotacaoId())) {
            throw new IllegalArgumentException(
                    "Cotação não encontrada com o ID: " + pagamentoDTO.getCotacaoId());
        }

        Pagamento pagamento = pagamentoMapper.toEntity(pagamentoDTO);

        if (pagamento.getStatus() == null || pagamento.getStatus().isBlank()) {
            pagamento.setStatus("PENDENTE");
        } else {
            pagamento.setStatus(pagamento.getStatus().toUpperCase());
        }

        if (pagamento.getDataPagamento() == null) {
            pagamento.setDataPagamento(LocalDateTime.now());
        }

        return pagamentoMapper.toDTO(pagamentoRepository.save(pagamento));
    }

    @SuppressWarnings("null")
    public PagamentoDTO atualizarStatus(Long id, String novoStatus) {
        if (novoStatus == null || novoStatus.isBlank()) {
            throw new IllegalArgumentException("O campo status é obrigatório");
        }

        String statusUpper = novoStatus.toUpperCase();
        if (!statusUpper.equals("PENDENTE") && !statusUpper.equals("PAGO")) {
            throw new IllegalArgumentException("Status inválido. Use: PENDENTE ou PAGO");
        }

        return pagamentoRepository.findById(id).map(pagamento -> {
            pagamento.setStatus(statusUpper);
            return pagamentoMapper.toDTO(pagamentoRepository.save(pagamento));
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Pagamento não encontrado com o ID: " + id));
    }

    @SuppressWarnings("null")
    public void deletar(Long id) {
        if (!pagamentoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Pagamento não encontrado com o ID: " + id);
        }
        pagamentoRepository.deleteById(id);
    }
}
