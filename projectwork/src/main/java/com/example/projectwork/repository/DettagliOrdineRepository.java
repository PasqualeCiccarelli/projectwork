package com.example.projectwork.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.projectwork.entity.DettaglioOrdineEntity;

public interface DettagliOrdineRepository extends JpaRepository<DettaglioOrdineEntity, Long>{

	List<DettaglioOrdineEntity> findByOrdineId(Long ordineId);
	List<DettaglioOrdineEntity> findByCartaId(Long cartaId);
	List<DettaglioOrdineEntity> findByBoxId(Long boxId);
	List<DettaglioOrdineEntity> findByBustinaId(Long bustinaId);
	List<DettaglioOrdineEntity> findByAccessorioId(Long accessorioId);
	@Query("SELECT SUM(d.quantita) FROM DettaglioOrdineEntity d WHERE d.carta.id = :cartaId")
    Integer sumQuantitaByCartaId(@Param("cartaId") Long cartaId);
}
