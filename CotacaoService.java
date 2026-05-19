package com.example.demo.service.Utils;

import com.example.demo.Entities.Cotacao;
import com.example.demo.dto.CotacaoDTO;
import com.example.demo.mapper.CotacaoMapper;
import com.example.demo.repository.ICotacaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
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
    private ClienteRepository clienteRepository;     

    @Autowired
    private IDestinoRepository destinoRepository;    

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

        //Buscar Cliente e Destinos pelos IDs 
        Optional<Cliente> clienteOpt = clienteRepository.findById(dto.getClienteId());
        if (clienteOpt.isEmpty()) {
            return new ApiResponse<>(new ErrorResponse("Não encontrado",
                    "Cliente não encontrado com o ID: " + dto.getClienteId()));
        }

        Optional<Destinos> destinoOpt = destinoRepository.findById(dto.getDestinoId());
        if (destinoOpt.isEmpty()) {
            return new ApiResponse<>(new ErrorResponse("Não encontrado",
                    "Destino não encontrado com o ID: " + dto.getDestinoId()));
        }

        Cotacao cotacao = cotacaoMapper.toEntity(dto);
        cotacao.setCliente(clienteOpt.get());       
        cotacao.setDestino(destinoOpt.get());        
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
    public Optional<CotacaoDTO> buscarPorId(Long id) {
        return cotacaoRepository.findById(id).map(cotacaoMapper::toDTO);
    }

    // Atualizar status da cotação (PENDENTE, APROVADA, REJEITADA)
    public ApiResponse<CotacaoDTO> atualizarStatus(Long id, String novoStatus) {
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
    public ApiResponse<Void> remover(Long id) {
        if (!cotacaoRepository.existsById(id)) {
            return new ApiResponse<>(new ErrorResponse("Não encontrado", "Cotação não encontrada com o ID: " + id));
        }
        cotacaoRepository.deleteById(id);
        return new ApiResponse<>((Void) null);
    }
}
