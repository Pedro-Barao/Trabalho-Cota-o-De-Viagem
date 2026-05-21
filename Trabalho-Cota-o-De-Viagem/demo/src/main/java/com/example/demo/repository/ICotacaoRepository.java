package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entities.Cotacao;

import java.util.List;

@Repository
public interface ICotacaoRepository extends JpaRepository<Cotacao, Long> {

    List<Cotacao> findByClienteId(Long clienteId);

    List<Cotacao> findByDestinoId(Long destinoId);

    List<Cotacao> findByStatus(String status);
}
