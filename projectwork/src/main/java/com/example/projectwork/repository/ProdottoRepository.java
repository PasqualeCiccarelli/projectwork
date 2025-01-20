package com.example.projectwork.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.projectwork.entity.AccessorioEntity;
import com.example.projectwork.entity.BoxEntity;
import com.example.projectwork.entity.BustinaEntity;
import com.example.projectwork.entity.CardEntity;
import com.example.projectwork.entity.ProdottoEntity;
import com.example.projectwork.entity.entityenum.Brand;
import com.example.projectwork.entity.entityenum.Categoria;
import com.example.projectwork.entity.entityenum.Rarita;
import com.example.projectwork.entity.entityenum.Stato;
import com.example.projectwork.entity.entityenum.Tipo;
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
	 List<ProdottoEntity> findByBrandAndCategoria(Brand brand, Categoria categoria);
	 List<ProdottoEntity> findByBrandAndTipoCategoriaAndTipoIn(Brand brand, TipoCategoria tipoCategoria, List<Tipo> tipi);
	
	 @Query("""
			    SELECT c, COUNT(d.id) as vendite 
			    FROM CardEntity c 
			    JOIN c.dettagliOrdine d 
			    WHERE c.brand = :brand
			    GROUP BY c.id 
			    ORDER BY vendite DESC 
			    LIMIT 5
			    """)
			List<CardEntity> findTop5SellingCardsByBrand(@Param("brand") Brand brand);

	 @Query("""
			    SELECT b, COUNT(d.id) as vendite 
			    FROM BustinaEntity b 
			    JOIN b.dettagliOrdine d 
			    WHERE b.brand = :brand
			    GROUP BY b.id 
			    ORDER BY vendite DESC 
			    LIMIT 5
			    """)
			List<BustinaEntity> findTop5SellingBustineByBrand(@Param("brand") Brand brand);

	 @Query("""
			    SELECT b, COUNT(d.id) as vendite 
			    FROM BoxEntity b 
			    JOIN b.dettagliOrdine d 
			    WHERE b.brand = :brand
			    GROUP BY b.id 
			    ORDER BY vendite DESC 
			    LIMIT 5
			    """)
			List<BoxEntity> findTop5SellingBoxesByBrand(@Param("brand") Brand brand);

	 @Query("""
			    SELECT a, COUNT(d.id) as vendite 
			    FROM AccessorioEntity a 
			    JOIN a.dettagliOrdine d 
			    WHERE a.brand = :brand
			    GROUP BY a.id 
			    ORDER BY vendite DESC 
			    LIMIT 5
			    """)
			List<AccessorioEntity> findTop5SellingAccessoriByBrand(@Param("brand") Brand brand);
	 
}
