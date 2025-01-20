package com.example.projectwork.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.projectwork.entity.AdminEntity;
import com.example.projectwork.entity.BustinaEntity;
import com.example.projectwork.entity.entityenum.Brand;
import com.example.projectwork.entity.entityenum.Categoria;
import com.example.projectwork.entity.entityenum.Tipo;

public interface BustineRepository extends JpaRepository<BustinaEntity, Long>{
	
	List<BustinaEntity> findByBrand(Brand brand);
	List<BustinaEntity> findByCategoria(Categoria categoria);
	List<BustinaEntity> findByTipo(Tipo tipo);
	List<BustinaEntity> findByDisponibilita(boolean disponibilita);
	List<BustinaEntity> findByRimanenzaGreaterThan(int rimanenza);
    List<BustinaEntity> findByPrezzoScontatoGreaterThan(double prezzoScontato);
	List<BustinaEntity> findByNomeContainingIgnoreCase(String nome);
	List<BustinaEntity> findByPrezzoBetween(double minPrezzo, double maxPrezzo);
	List<BustinaEntity> findByBrandAndCategoria(Brand brand, Categoria categoria);
	Page<BustinaEntity> findByAdmin(AdminEntity admin, Pageable pageable);
	Page<BustinaEntity> findByAdmin_Email(String email, Pageable pageable);
	
//	 @Query("""
//		        SELECT b, COUNT(d.id) as vendite 
//		        FROM BustinaEntity b 
//		        JOIN b.dettagliOrdine d 
//		        GROUP BY b.id 
//		        ORDER BY vendite DESC 
//		        LIMIT 5
//		        """)
//		    List<BustinaEntity> findTop5SellingBustine();

}
