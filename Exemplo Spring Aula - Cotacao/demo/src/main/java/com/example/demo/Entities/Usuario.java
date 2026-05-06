package com.example.demo.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.boot.persistence.autoconfigure.EntityScan;

import java.time.LocalDate;

import tech.tablesaw.*;
import tech.tablesaw.aggregate.*;
import tech.tablesaw.io.csv.*;

import jakarta.persistence.column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@EntityScan
@Table(name = "usuarios")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor

public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Column(nullable = false)
    private String telefone;

}