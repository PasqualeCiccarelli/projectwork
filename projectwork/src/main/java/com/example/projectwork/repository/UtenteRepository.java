package com.example.projectwork.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.projectwork.entity.UtenteEntity;
import com.example.projectwork.entity.entityenum.Ruolo;


@Repository
public interface UtenteRepository extends JpaRepository<UtenteEntity, Long>{

	Optional<UtenteEntity> findByEmail(String email);
	
	List<UtenteEntity> findByRuolo(Ruolo ruolo);
	
	@Query("SELECT u FROM UtenteEntity u WHERE " +
			"LOWER(u.nome) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
			"LOWER(u.cognome) LIKE LOWER(CONCAT('%', :search, '%'))")
	List<UtenteEntity> searchByNomeOrCognome(@Param("search") String search);
	
	List<UtenteEntity> findByAccettaPoliticheTrue();
}
