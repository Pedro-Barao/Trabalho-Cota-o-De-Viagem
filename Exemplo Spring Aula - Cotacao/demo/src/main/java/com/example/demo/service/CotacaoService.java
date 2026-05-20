package com.example.demo.service;

import com.example.demo.Entities.Cotacao;
import com.example.demo.Entities.Destinos;
import com.example.demo.dto.CotacaoDTO;
import com.example.demo.mapper.CotacaoMapper;
import com.example.demo.repository.IClienteRepository;
import com.example.demo.repository.ICotacaoRepository;
import com.example.demo.repository.IDestinoRepository;
import com.example.demo.service.Utils.ApiResponse;
import com.example.demo.service.Utils.ErrorResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class CotacaoService {

    @Autowired
    private ICotacaoRepository cotacaoRepository;

    @Autowired
    private IClienteRepository clienteRepository;

    @Autowired
    private IDestinoRepository destinoRepository;

    @Autowired
    private CotacaoMapper cotacaoMapper;

    //Criar 
    public ApiResponse<CotacaoDTO> criar(CotacaoDTO dto) {

        // Validações básicas
        if (dto.getClienteId() == null) {
            return new ApiResponse<>(new ErrorResponse("Validacao", "cliente_id é obrigatório"));
        }
        if (dto.getDestinoId() == null) {
            return new ApiResponse<>(new ErrorResponse("Validacao", "destino_id é obrigatório"));
        }
        if (dto.getNumeroDePessoas() == null || dto.getNumeroDePessoas() < 1) {
            return new ApiResponse<>(new ErrorResponse("Validacao", "numero_de_pessoas deve ser pelo menos 1"));
        }

        //Cliente 
        if (!clienteRepository.existsById(dto.getClienteId())) {
            return new ApiResponse<>(new ErrorResponse("Não encontrado",
                    "Cliente não encontrado com o ID: " + dto.getClienteId()));
        }

        //Destino 
        Optional<Destinos> destinoOpt = destinoRepository.findById(dto.getDestinoId());
        if (destinoOpt.isEmpty()) {
            return new ApiResponse<>(new ErrorResponse("Não encontrado",
                    "Destino não encontrado com o ID: " + dto.getDestinoId()));
        }
        Destinos destino = destinoOpt.get();

        // Monta a entidade
        Cotacao cotacao = cotacaoMapper.toEntity(dto);
        cotacao.setDataCotacao(LocalDateTime.now());
        cotacao.setStatus("PENDENTE");

        //Regra de negócio
        BigDecimal valorTotal = destino.getPrecoPorPessoa()
                .multiply(BigDecimal.valueOf(dto.getNumeroDePessoas()));

        //Aplica desconto conforme o período da viagem e número de pessoas
        BigDecimal percentualDesconto = calcularPercentualDesconto(
                dto.getDataViagem(),
                dto.getNumeroDePessoas());

        if (percentualDesconto.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal valorDesconto = valorTotal.multiply(percentualDesconto);
            valorTotal = valorTotal.subtract(valorDesconto);
        }

        cotacao.setValorTotal(valorTotal.setScale(2, RoundingMode.HALF_UP));

        Cotacao salva = cotacaoRepository.save(cotacao);
        return new ApiResponse<>(cotacaoMapper.toDTO(salva));
    }

        //Desconto
        if (numeroPessoas != null && numeroPessoas >= 5) {
            desconto = desconto.add(new BigDecimal("0.10"));
        }

        if (dataViagem != null) {
            Month mes = dataViagem.getMonth();
            boolean baixaTemporada =
                    mes == Month.MARCH || mes == Month.APRIL || mes == Month.MAY ||
                    mes == Month.AUGUST || mes == Month.SEPTEMBER || mes == Month.OCTOBER;
            if (baixaTemporada) {
                desconto = desconto.add(new BigDecimal("0.05"));
            }
        }

        return desconto;
    }

    //Listar 
    public List<CotacaoDTO> listarTodas() {
        return cotacaoMapper.toDTOList(cotacaoRepository.findAll());
    }

    //Buscar por ID
    public Optional<CotacaoDTO> buscarPorId(Long id) {
        return cotacaoRepository.findById(id).map(cotacaoMapper::toDTO);
    }

    //Atualizar status (PENDENTE, APROVADA, REJEITADA)
    public ApiResponse<CotacaoDTO> atualizarStatus(Long id, String novoStatus) {
        if (novoStatus == null || novoStatus.isBlank()) {
            return new ApiResponse<>(new ErrorResponse("Validacao", "O campo status é obrigatório"));
        }

        String statusUpper = novoStatus.toUpperCase();
        if (!statusUpper.equals("PENDENTE") && !statusUpper.equals("APROVADA") && !statusUpper.equals("REJEITADA")) {
            return new ApiResponse<>(new ErrorResponse("Validacao",
                    "Status inválido. Use: PENDENTE, APROVADA ou REJEITADA"));
        }

        Optional<Cotacao> optionalCotacao = cotacaoRepository.findById(id);
        if (optionalCotacao.isEmpty()) {
            return new ApiResponse<>(new ErrorResponse("Não encontrado",
                    "Cotação não encontrada com o ID: " + id));
        }

        Cotacao cotacao = optionalCotacao.get();
        cotacao.setStatus(statusUpper);
        Cotacao atualizada = cotacaoRepository.save(cotacao);
        return new ApiResponse<>(cotacaoMapper.toDTO(atualizada));
    }

    //Remover
    public ApiResponse<Void> remover(Long id) {
        if (!cotacaoRepository.existsById(id)) {
            return new ApiResponse<>(new ErrorResponse("Não encontrado",
                    "Cotação não encontrada com o ID: " + id));
        }
        cotacaoRepository.deleteById(id);
        return new ApiResponse<>((Void) null);
    }
}
