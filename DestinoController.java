//alisson
package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.DestinoDTO;
import com.example.demo.service.DestinoService;
import com.example.demo.service.Utils.ApiResponse;
import com.example.demo.service.Utils.ErrorResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@Tag(name = "Destinos", description = "Endpoints para gerenciamento de destinos")
@RestController
@RequestMapping("api/destino")
public class DestinoController {

    @Autowired
    private DestinoService destinoService;

    @Operation(summary = "Lista todos os destinos", description = "Cadastra um novo destino no sistema")
    @GetMapping
    public ResponseEntity<List<DestinoDTO>> listarDestinos()
    {

        List<DestinoDTO> destinos = destinoService.listarTodos();

        return ResponseEntity.ok(destinos);

    }

    @Operation(summary = "Busca um destino por ID", description = "Retorna os detalhes de um destino")
    @GetMapping("/{id}")
    public ResponseEntity<DestinoDTO> buscarPorId(@PathVariable Long id)
    {

        Optional<DestinoDTO> destinoDTO = destinoService.buscarPorId(id);

        return destinoDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @Operation(summary = "Cria um novo destino", description = "Cadastra um novo destino no sistema")
    @PostMapping
    public ResponseEntity<ApiResponse<DestinoDTO>> criarDestino(@Valid @RequestBody DestinoDTO destinoDTO)
    {
        
        try
        {

            DestinoDTO savedDestino = destinoService.salvar(destinoDTO);

            ApiResponse<DestinoDTO> response = new ApiResponse<>(savedDestino);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
            
        } catch (IllegalArgumentException e)
        {

            ErrorResponse errorResponse = new ErrorResponse("Argumento Inválido", e.getMessage());

            ApiResponse<DestinoDTO> response = new ApiResponse<>(errorResponse);

            return ResponseEntity.badRequest().body(response);

        } catch (Exception e)
        {

            ErrorResponse errorResponse = new ErrorResponse("Erro Interno", e.getMessage());

            ApiResponse<DestinoDTO> response = new ApiResponse<>(errorResponse);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

        }

    }

    @Operation(summary = "Atualiza um destino", description = "Atualiza as informações de um destino cadastrado pelo id")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DestinoDTO>> atualizarDestino(@PathVariable Long id, @Valid @RequestBody DestinoDTO destinoDTO)
    {

        try
        {

            Optional<DestinoDTO> destinoAtualizado = destinoService.atualizar(id, destinoDTO);

            if (destinoAtualizado.isPresent())
            {

                ApiResponse<DestinoDTO> response = new ApiResponse<>(destinoAtualizado.get());

                return ResponseEntity.ok(response);

            } else
            {

                ErrorResponse errorResponse = new ErrorResponse("Destino não encontrado", "Não existe destino com o id informado: " + id);

                ApiResponse<DestinoDTO> response = new ApiResponse<>(errorResponse);

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

            }

        } catch (IllegalArgumentException e)
        {

            ErrorResponse errorResponse = new ErrorResponse("Argumento Inválido", e.getMessage());

            ApiResponse<DestinoDTO> response = new ApiResponse<>(errorResponse);

            return ResponseEntity.badRequest().body(response);

        } catch (Exception e)
        {

            ErrorResponse errorResponse = new ErrorResponse("Erro Interno", e.getMessage());

            ApiResponse<DestinoDTO> response = new ApiResponse<>(errorResponse);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

        }

    }

    @Operation(summary = "Deleta um destino", description = "Remove um destino do sistema pelo id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDestino(@PathVariable Long id)
    {

        destinoService.deletar(id);

        return ResponseEntity.noContent().build();

    }
    
}
