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

@Tag(name = "Pagamento", description = "Endpoints para gerenciamento de pagamentos")
@RestController
@RequestMapping("/api/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @Operation(summary = "Lista todos os pagamentos")
    @GetMapping
    public ResponseEntity<List<PagamentoDTO>> listarPagamentos() {
        List<PagamentoDTO> pagamentos = pagamentoService.listarTodos();
        return ResponseEntity.ok(pagamentos);
    }

    @Operation(summary = "Busca um pagamento por ID")
    @GetMapping("/{id}")
    public ResponseEntity<PagamentoDTO> buscarPorId(@PathVariable Long id) {
        Optional<PagamentoDTO> pagamentoDTO = pagamentoService.buscarPorId(id);
        return pagamentoDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Registrar um novo pagamento")
    @PostMapping
    public ResponseEntity<ApiResponse<PagamentoDTO>> registrarPagamento(@Valid @RequestBody PagamentoDTO pagamentoDTO) {
        try {
            PagamentoDTO savedPagamento = pagamentoService.salvar(pagamentoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(savedPagamento));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(new ErrorResponse("Argumento Inválido", e.getMessage())));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(new ErrorResponse("Erro Interno", e.getMessage())));
        }
    }

    @Operation(summary = "Atualiza o status de um pagamento")
    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<PagamentoDTO>> atualizarStatus(@PathVariable Long id, @RequestBody String status) {
        try {
            PagamentoDTO atualizado = pagamentoService.atualizarStatus(id, status);
            return ResponseEntity.ok(new ApiResponse<>(atualizado));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(new ErrorResponse("Erro ao atualizar status", e.getMessage())));
        }
    }

    @Operation(summary = "Deleta um pagamento")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPagamento(@PathVariable Long id) {
        pagamentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}