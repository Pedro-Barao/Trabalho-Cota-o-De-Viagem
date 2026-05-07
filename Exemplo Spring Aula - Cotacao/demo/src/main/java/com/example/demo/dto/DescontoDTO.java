package com.example.demo.dto;

import lombok.NoArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;


import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class DescontoDTO {

    private Long id;

    
    @NotNull(message = "O id de cotação é obrigatório")
    private Long cotacaoId;

    @NotNull(message = "Informe o valor do desconto")
    @DecimalMin(value = "0.01", message = "O valor do desconto deve ser maior que zero")
    @DecimalMax(value = "0.5", message = "O valor máximo do desconto é 0.5")
    private BigDecimal valorDesconto;

    @NotBlank(message = "A descrição é obrigatória")
    @Size(min = 5, max = 600, message = "A descrição deve conter entre 5 e 600 caracteres")
    private String descricao;

    @NotNull(message = "A data de aplicação é obrigatória")
    private LocalDateTime dataAplicacao;

    
} 