package com.example.projectwork.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projectwork.entity.OrdineEntity;
import com.example.projectwork.entity.UtenteEntity;
import com.example.projectwork.entity.entityenum.Stato;

public interface OrdineRepository extends JpaRepository<OrdineEntity, Long>{
	
	Optional<OrdineEntity> findByUtenteAndStato(UtenteEntity utente, Stato stato);
	List<OrdineEntity> findByUtenteAndStatoIn(UtenteEntity utente, List<Stato> stati);
	Optional<OrdineEntity> findByUtenteAndId(UtenteEntity utente, Long id);
	List<OrdineEntity> findAll();
}