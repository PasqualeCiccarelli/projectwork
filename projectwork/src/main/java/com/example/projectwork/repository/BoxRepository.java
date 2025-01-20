package com.example.projectwork.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.projectwork.entity.AdminEntity;
import com.example.projectwork.entity.BoxEntity;
import com.example.projectwork.entity.entityenum.Brand;
import com.example.projectwork.entity.entityenum.Categoria;
import com.example.projectwork.entity.entityenum.Tipo;

public interface BoxRepository extends JpaRepository<BoxEntity, Long>{
	
	List<BoxEntity> findByBrand(Brand brand);
	
	List<BoxEntity> findByCategoria(Categoria categoria);
	List<BoxEntity> findByTipo(Tipo tipo);
	List<BoxEntity> findByDisponibilita(boolean disponibilita);
	List<BoxEntity> findByRimanenzaGreaterThan (int rimanenza);
	List<BoxEntity> findByPrezzoScontatoGreaterThan(double prezzoScontato);
	List<BoxEntity> findByNomeContainingIgnoreCase (String nome);
	List<BoxEntity> findByPrezzoBetween(double minPrezzo, double maxPrezzo);
	List<BoxEntity> findByBrandAndCategoria(Brand brand, Categoria categoria);
	Page<BoxEntity> findByAdmin(AdminEntity admin, Pageable pageable);
	Page<BoxEntity> findByAdmin_Email(String email, Pageable pageable);
	
//	 @Query("""
//		        SELECT b, COUNT(d.id) as vendite 
//		        FROM BoxEntity b 
//		        JOIN b.dettagliOrdine d 
//		        GROUP BY b.id 
//		        ORDER BY vendite DESC 
//		        LIMIT 5
//		        """)
//		    List<BoxEntity> findTop5SellingBoxes();

}
