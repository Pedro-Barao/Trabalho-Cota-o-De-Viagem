package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.dto.PagamentoDTO;
import com.example.demo.service.PagamentoService;
import com.example.demo.service.Utils.ApiResponse;
import com.example.demo.service.Utils.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Pagamento", description = "Endpoints para gerenciar pagamentos de cotações")
@RestController
@RequestMapping("/api/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @Operation(summary = "Registrar Pagamento", description = "Registra um novo pagamento no sistema")
    @PostMapping
    public ResponseEntity<ApiResponse<PagamentoDTO>> registrarPagamento(@Valid @RequestBody PagamentoDTO pagamentoDTO) {
        try {
            PagamentoDTO salvo = pagamentoService.salvar(pagamentoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(salvo));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(new ErrorResponse("Erro ao registrar", e.getMessage())));
        }
    }

    @Operation(summary = "Obter Pagamento por ID")
    @GetMapping("/{id}")
    public ResponseEntity<PagamentoDTO> obterPorId(@PathVariable Long id) {
        return pagamentoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Atualizar Status do Pagamento")
    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<PagamentoDTO>> atualizarStatus(@PathVariable Long id, @RequestBody String status) {
        try {
            PagamentoDTO atualizado = pagamentoService.atualizarStatus(id, status);
            return ResponseEntity.ok(new ApiResponse<>(atualizado));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(new ErrorResponse("Erro na atualização", e.getMessage())));
        }
    }

    @Operation(summary = "Remover Pagamento")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        pagamentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}