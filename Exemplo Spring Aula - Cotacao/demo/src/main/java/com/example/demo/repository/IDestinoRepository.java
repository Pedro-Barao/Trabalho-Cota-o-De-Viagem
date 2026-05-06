package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entities.Destinos;

@Repository
public interface IDestinoRepository extends JpaRepository<Destinos, Long> {

    Optional<Destinos> findById(Long id);

}