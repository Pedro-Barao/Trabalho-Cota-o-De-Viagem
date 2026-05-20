package com.example.demo.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "cotacoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cotacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //FK Cliente 
    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", referencedColumnName = "id",
                insertable = false, updatable = false)
    @JsonIgnore
    private Cliente cliente;

    //FK Destinos
    @Column(name = "destino_id", nullable = false)
    private Long destinoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destino_id", referencedColumnName = "id",
                insertable = false, updatable = false)
    @JsonIgnore
    private Destinos destino;

    @Column(name = "data_cotacao", nullable = false)
    private LocalDateTime dataCotacao;

    @Column(name = "data_viagem")
    private LocalDateTime dataViagem;

    @Column(name = "data_retorno")
    private LocalDateTime dataRetorno;

    @Column(name = "numero_de_pessoas", nullable = false)
    private Integer numeroDePessoas;

    @Column(name = "valor_total", nullable = false)
    private BigDecimal valorTotal;

    @Column(nullable = false)
    private String status;
}
