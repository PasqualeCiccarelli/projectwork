package com.example.projectwork.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projectwork.entity.ProdottoEntity;

public interface ProdottoRepository extends JpaRepository<ProdottoEntity, Long>{
	
	Optional<ProdottoEntity> findById(Long id);

}
