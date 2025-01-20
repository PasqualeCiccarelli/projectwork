package com.example.projectwork.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.projectwork.entity.AccessorioEntity;
import com.example.projectwork.entity.BoxEntity;
import com.example.projectwork.entity.BustinaEntity;
import com.example.projectwork.entity.CardEntity;
import com.example.projectwork.entity.DettaglioOrdineEntity;
import com.example.projectwork.entity.OrdineEntity;

public interface DettagliOrdineRepository extends JpaRepository<DettaglioOrdineEntity, Long> {

    List<DettaglioOrdineEntity> findByOrdine(OrdineEntity ordine);
    List<DettaglioOrdineEntity> findByCarta(CardEntity carta);
    List<DettaglioOrdineEntity> findByBox(BoxEntity box);
    List<DettaglioOrdineEntity> findByBustina(BustinaEntity bustina);
    List<DettaglioOrdineEntity> findByAccessorio(AccessorioEntity accessorio);

    @Query("SELECT SUM(d.quantita) FROM DettaglioOrdineEntity d WHERE d.carta.id = :cartaId")
    Integer sumQuantitaByCartaId(@Param("cartaId") Long cartaId);
}