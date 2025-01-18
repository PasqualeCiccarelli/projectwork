package com.example.projectwork.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.projectwork.entity.ProdottoEntity;
import com.example.projectwork.entity.entityenum.Brand;
import com.example.projectwork.entity.entityenum.Categoria;
import com.example.projectwork.entity.entityenum.Rarita;
import com.example.projectwork.entity.entityenum.Stato;
import com.example.projectwork.entity.entityenum.TipoCategoria;

public interface ProdottoRepository extends JpaRepository<ProdottoEntity, Long>{
	
	 @Query("SELECT p FROM ProdottoEntity p " +
             "WHERE (:tipoCategoria IS NULL OR p.tipoCategoria = :tipoCategoria) " +
             "AND (:rarita IS NULL OR (p.rarita = :rarita)) " +
             "AND (:categoria IS NULL OR p.categoria = :categoria) " +
             "AND (:stato IS NULL OR p.tipo = :stato) " +
             "AND (:brand IS NULL OR p.brand = :brand) " +
             "AND (:prezzoMinimo IS NULL OR p.prezzo >= :prezzoMinimo) " + 
             "AND (:prezzoMassimo IS NULL OR p.prezzo <= :prezzoMassimo)")
  Page<ProdottoEntity> findAllWithFilters(
         @Param("tipoCategoria") TipoCategoria tipoCategoria,
         @Param("rarita") Rarita rarita,
         @Param("categoria") Categoria categoria,
         @Param("stato") Stato stato,
         @Param("brand") Brand brand,
         @Param("prezzoMinimo") Double prezzoMinimo,
         @Param("prezzoMassimo") Double prezzoMassimo,
         Pageable pageable);
	 
	 @Query("SELECT p FROM ProdottoEntity p WHERE LOWER(p.nome) LIKE LOWER(CONCAT('%', :query, '%'))")
	    List<ProdottoEntity> findByNomeContainingIgnoreCase(@Param("query") String query);
}
