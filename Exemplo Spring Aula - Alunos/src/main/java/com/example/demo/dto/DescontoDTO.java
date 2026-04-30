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

    @ManyToMany
    @JoinColumn(name = "Cotacao_Id")
    @NotBlank(message = "O id de cotação é obrigatório")
    private Long cotacaoId;

    @NotBlank(message = "O valor do desconto é obrigatório")
    private BigDecimal valorDesconto;

    @NotBlank(message = "A descrição é obrigatória")
    private String descricao;

    @NotBlank(message = "A data de aplicação é obrigat")
    private LocalDateTime dataAplicacao;
    
}