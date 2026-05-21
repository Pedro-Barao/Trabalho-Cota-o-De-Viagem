package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entities.Desconto;

public interface IDescontoRepository extends JpaRepository<Desconto, Long> {

    Optional<Desconto> findByCotacaoId(Long cotacaoId);

}