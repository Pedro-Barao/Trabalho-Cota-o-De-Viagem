package com.example.demo.repository;

    import java.util.Optional;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;

import com.example.demo.Entities.Cliente;

    
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByEmail(String email);
    Optional<Cliente> findByDocumento(String documento);
}

