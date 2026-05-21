package com.example.demo.dto;

import lombok.NoArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;

@Data
@NoArgsConstructor
public class CotacaoDTO {

    private Long id;

    @NotNull(message = "O cliente_id é obrigatório")
    private Long clienteId;

    @NotNull(message = "O destino_id é obrigatório")
    private Long destinoId;

    private LocalDateTime dataCotacao;

    private LocalDateTime dataViagem;

    private LocalDateTime dataRetorno;

    @NotNull(message = "O número de pessoas é obrigatório")
    @Min(value = 1, message = "O número de pessoas deve ser pelo menos 1")
    private Integer numeroDePessoas;

    private BigDecimal valorTotal;

    private String status;
}
