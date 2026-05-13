package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClienteDTO {

    private Long id;

    @NotBlank(message = "O nome do cliente é obrigatório.")
    private String nome;
    
    @NotBlank(message = "O e-mail é obrigatório.")
    private String email;

    @NotBlank(message = "O telefone é obrigatório.")
    private String telefone;

    @NotBlank(message = "O documento é obrigatório.")
    @Size(min = 11, max = 14, message = "O documento deve ter entre 11 e 14 caracteres.")
    private String documento;

}