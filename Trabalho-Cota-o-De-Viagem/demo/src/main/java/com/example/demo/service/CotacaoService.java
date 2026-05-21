package com.example.demo.service;

import com.example.demo.Entities.Cotacao;
import com.example.demo.dto.CotacaoDTO;
import com.example.demo.mapper.CotacaoMapper;
import com.example.demo.repository.ICotacaoRepository;
import com.example.demo.service.Utils.ApiResponse;
import com.example.demo.service.Utils.ErrorResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CotacaoService {

    @Autowired
    private ICotacaoRepository cotacaoRepository;

    @Autowired
    private CotacaoMapper cotacaoMapper;

    // Criar 
    public ApiResponse<CotacaoDTO> criar(CotacaoDTO dto) {
        if (dto.getClienteId() == null) {
            return new ApiResponse<>(new ErrorResponse("Validacao", "cliente_id é obrigatório"));
        }
        if (dto.getDestinoId() == null) {
            return new ApiResponse<>(new ErrorResponse("Validacao", "destino_id é obrigatório"));
        }
        if (dto.getNumeroDePessoas() == null || dto.getNumeroDePessoas() < 1) {
            return new ApiResponse<>(new ErrorResponse("Validacao", "numero_de_pessoas deve ser pelo menos 1"));
        }

        Cotacao cotacao = cotacaoMapper.toEntity(dto);
        cotacao.setDataCotacao(LocalDateTime.now());
        cotacao.setStatus("PENDENTE");

        if (cotacao.getValorTotal() == null) {
            cotacao.setValorTotal(BigDecimal.ZERO);
        }

        Cotacao salva = cotacaoRepository.save(cotacao);
        return new ApiResponse<>(cotacaoMapper.toDTO(salva));
    }

    // Listar todas as cotações
    public List<CotacaoDTO> listarTodas() {
        return cotacaoMapper.toDTOList(cotacaoRepository.findAll());
    }

    // Buscar cotação por ID
    public Optional<CotacaoDTO> buscarPorId(@NonNull Long id) {
        return cotacaoRepository.findById(id).map(cotacaoMapper::toDTO);
    }

    // Atualizar status da cotação (PENDENTE, APROVADA, REJEITADA)
    public ApiResponse<CotacaoDTO> atualizarStatus(@NonNull Long id, String novoStatus) {
        String statusUpper = novoStatus.toUpperCase();

        if (!statusUpper.equals("PENDENTE") && !statusUpper.equals("APROVADA") && !statusUpper.equals("REJEITADA")) {
            return new ApiResponse<>(new ErrorResponse("Validacao", "Status inválido. Use: PENDENTE, APROVADA ou REJEITADA"));
        }

        Optional<Cotacao> optionalCotacao = cotacaoRepository.findById(id);
        if (optionalCotacao.isEmpty()) {
            return new ApiResponse<>(new ErrorResponse("Não encontrado", "Cotação não encontrada com o ID: " + id));
        }

        Cotacao cotacao = optionalCotacao.get();
        cotacao.setStatus(statusUpper);
        Cotacao atualizada = cotacaoRepository.save(cotacao);
        return new ApiResponse<>(cotacaoMapper.toDTO(atualizada));
    }

    // Remover cotação
    public ApiResponse<Void> remover(@NonNull Long id) {
        if (!cotacaoRepository.existsById(id)) {
            return new ApiResponse<>(new ErrorResponse("Não encontrado", "Cotação não encontrada com o ID: " + id));
        }
        cotacaoRepository.deleteById(id);
        return new ApiResponse<>((Void) null);
    }
}
