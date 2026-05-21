package com.example.demo.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.Entities.Pagamento;

public interface IPagamentoRepository extends JpaRepository<Pagamento, Long> {
    Optional<Pagamento> findByCotacaoId(Long cotacaoId);
}