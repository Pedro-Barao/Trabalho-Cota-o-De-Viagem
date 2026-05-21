package com.example.demo.dto;

import lombok.NoArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Data
@NoArgsConstructor
public class DescontoDTO {

    private Long id;

    
    @NotNull(message = "O id de cotação é obrigatório")
    private Long cotacaoId;

    @NotNull(message = "Informe o valor do desconto")
    @Positive(message = "O valor do desconto deve ser maior que zero")
    private BigDecimal valorDesconto;

    @NotBlank(message = "A descrição do desconto é obrigatória")
    private String descricao;

    @NotBlank(message = "Informe a data de aplicação do desconto")
    private LocalDateTime dataAplicacao; 
    
}