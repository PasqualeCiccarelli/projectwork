package com.example.projectwork.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.projectwork.entity.UtenteEntity;


@Repository
public interface UtenteRepository extends JpaRepository<UtenteEntity, Long>{

	Optional<UtenteEntity> findByEmail(String email);
}
