package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.DescontoDTO;
import com.example.demo.service.DescontoService;
import com.example.demo.service.Utils.ApiResponse;
import com.example.demo.service.Utils.ErrorResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Tag(name = "Desconto", description = "Endpoints para gerenciamento de descontos")
@RestController
@RequestMapping("/api/descontos")
public class DescontoController {

    @Autowired
    private DescontoService descontoService;

    @Operation(summary = "Lista todos os descontos", description = "Cadastra um novo desconto no sistema")
    @GetMapping
    public ResponseEntity<List<DescontoDTO>> listarDescontos()
    {

        List<DescontoDTO> descontos = descontoService.listarTodos();

        return ResponseEntity.ok(descontos);

    }

    @Operation(summary = "Busca um desconto por 10", description = "Retorna os detalhes de um desconto")
    @GetMapping("/{id}")
    public ResponseEntity<DescontoDTO> buscarPorId(@PathVariable Long id)
    {

        Optional<DescontoDTO> descontoDTO = descontoService.buscarPorId(id);

        return descontoDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @Operation(summary = "Cria um novo desconto", description = "Cadastra um novo desconto no sistema")
    @PostMapping
    public ResponseEntity<ApiResponse<DescontoDTO>> criarDesconto(@Valid @RequestBody DescontoDTO descontoDTO) 
    {
        
        try
        {

            DescontoDTO savedDesconto = descontoService.salvar(descontoDTO);

            ApiResponse<DescontoDTO> response = new ApiResponse<>(savedDesconto);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
             
        } catch (IllegalArgumentException e)
        {

            ErrorResponse errorResponse = new ErrorResponse("Argumento Inválido", e.getMessage());

            ApiResponse<DescontoDTO> response = new ApiResponse<>(errorResponse);

            return ResponseEntity.badRequest().body(response);

        } catch (Exception e)
        {

            ErrorResponse errorResponse = new ErrorResponse("Erro Interno", e.getMessage());

            ApiResponse<DescontoDTO> response = new ApiResponse<>(errorResponse);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

        }

    }

    @Operation(summary = "Deleta um desconto", description = "Remove um desconto do sistema pelo id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDesconto(@PathVariable Long id)
    {

        descontoService.deletar(id);

        return ResponseEntity.noContent().build();

    }
    
}