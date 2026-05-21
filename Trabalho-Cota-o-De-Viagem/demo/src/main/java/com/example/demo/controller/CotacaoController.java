package com.example.demo.controller;

import com.example.demo.dto.CotacaoDTO;
import com.example.demo.service.CotacaoService;
import com.example.demo.service.Utils.ApiResponse;
import com.example.demo.service.Utils.ErrorResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Tag(name = "Cotacoes", description = "Endpoints para gerenciamento de cotações")
@RestController
@RequestMapping("/api/cotacoes")
public class CotacaoController {

    @Autowired
    private CotacaoService cotacaoService;

    // 1 - Criar Cotação: POST /api/cotacoes
    @Operation(summary = "Cria uma nova cotação", description = "Cadastra uma nova cotação no sistema")
    @PostMapping
    public ResponseEntity<ApiResponse<CotacaoDTO>> criarCotacao(@Valid @RequestBody CotacaoDTO cotacaoDTO) {
        try {
            ApiResponse<CotacaoDTO> response = cotacaoService.criar(cotacaoDTO);
            if (response.isSuccess()) {
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            } else {
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("Erro interno", e.getMessage());
            ApiResponse<CotacaoDTO> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // 2 - Listar Cotações: GET /api/cotacoes
    @Operation(summary = "Lista todas as cotações", description = "Retorna uma lista com todas as cotações")
    @GetMapping
    public ResponseEntity<List<CotacaoDTO>> listarCotacoes() {
        List<CotacaoDTO> cotacoes = cotacaoService.listarTodas();
        return ResponseEntity.ok(cotacoes);
    }

    // 3 - Consultar Cotação por ID: GET /api/cotacoes/{id}
    @Operation(summary = "Busca uma cotação por ID", description = "Retorna os detalhes de uma cotação")
    @GetMapping("/{id}")
    public ResponseEntity<CotacaoDTO> buscarPorId(@PathVariable @NonNull Long id) {
        Optional<CotacaoDTO> cotacaoDTO = cotacaoService.buscarPorId(id);
        return cotacaoDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 4 - Atualizar Status da Cotação: PATCH /api/cotacoes/{id}/status
    @Operation(summary = "Atualiza o status de uma cotação", description = "Atualiza o status (PENDENTE/APROVADA/REJEITADA)")
    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<CotacaoDTO>> atualizarStatus(@PathVariable @NonNull Long id,
                                                                     @RequestBody Map<String, String> body) {
        String status = body.get("status");
        if (status == null || status.isBlank()) {
            ErrorResponse errorResponse = new ErrorResponse("Validacao", "O campo status é obrigatório");
            return ResponseEntity.badRequest().body(new ApiResponse<>(errorResponse));
        }

        ApiResponse<CotacaoDTO> response = cotacaoService.atualizarStatus(id, status);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    // 5 - Remover Cotação: DELETE /api/cotacoes/{id}
    @Operation(summary = "Remove uma cotação", description = "Remove uma cotação do sistema pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerCotacao(@PathVariable @NonNull Long id) {
        ApiResponse<Void> response = cotacaoService.remover(id);
        if (response.isSuccess()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
