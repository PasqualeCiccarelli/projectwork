package com.example.projectwork.repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.projectwork.entity.OrdineEntity;
import com.example.projectwork.entity.UtenteEntity;
import com.example.projectwork.entity.entityenum.Stato;

public interface OrdineRepository extends JpaRepository<OrdineEntity, Long>{
	
	Optional<OrdineEntity> findByUtenteAndStato(UtenteEntity utente, Stato stato);
}
