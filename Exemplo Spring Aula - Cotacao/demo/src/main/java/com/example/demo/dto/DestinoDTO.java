package com.example.demo.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class DestinoDTO {

    private long id;

    @NotBlank(message = "O nome é um campo obrigatorio.")
    private String nome;

    @NotNull(message = "A descrição é um campo obrigatório.")
    private String descricao;


    @NotNull(message = "O preço por pessoa é obrigatório")
    private BigDecimal precoPorPessoa;
    
}