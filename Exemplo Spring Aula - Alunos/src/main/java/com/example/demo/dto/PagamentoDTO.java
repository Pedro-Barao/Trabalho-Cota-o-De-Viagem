package com.example.demo.dto;

import lombok.NoArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Data
@NoArgsConstructor
public class PagamentoDTO {

    private Long id;

    @NotNull(message = "O id de cotação é obrigatório")
    private Long cotacaoId;

    @NotNull(message = "O valor pago é obrigatório")
    @Positive(message = "O valor pago deve ser maior que zero")
    private BigDecimal valorPago;

    @NotNull(message = "O status é obrigatório")
    private String status;

    @NotNull(message = "A data de pagamento é obrigatória")
    private LocalDateTime dataPagamento;

}