package com.example.projectwork.repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.projectwork.entity.OrdineEntity;
import com.example.projectwork.entity.entityenum.Stato;

public interface OrdineRepository extends JpaRepository<OrdineEntity, Long>{
	
	
	Optional<OrdineEntity> findByUtenteIdAndStato_consegna(Long userId, Stato stato);
	
	List<OrdineEntity> findByUtenteId(Long userId);
	
	List<OrdineEntity> findByStato_consegna(Stato stato);
	
	List<OrdineEntity> findAllByStatoNotIn(Collection<Stato> stati);
	
	@Query("SELECT o FROM OrdineEntity o WHERE o.data BETWEEN :startDate AND :endDate")
    List<OrdineEntity> findByDataBetween(
        @Param("startDate") LocalDate startDate, 
        @Param("endDate") LocalDate endDate
    );
	
	long countByStato_consegna(Stato stato);
}
