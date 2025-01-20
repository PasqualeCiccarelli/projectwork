package com.example.projectwork.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.projectwork.entity.AdminEntity;
import com.example.projectwork.entity.CardEntity;
import com.example.projectwork.entity.entityenum.Brand;
import com.example.projectwork.entity.entityenum.Categoria;
import com.example.projectwork.entity.entityenum.Tipo;

public interface CardRepository extends JpaRepository<CardEntity, Long>{
	
	List<CardEntity> findByBrand(Brand brand);
	List<CardEntity> findByBrandAndCategoria(Brand brand, Categoria categoria);
	List<CardEntity> findByCategoria(Categoria categoria);
	List<CardEntity> findByTipo(Tipo tipo);
	List<CardEntity> findByDisponibilita(boolean disponibilita);
	List<CardEntity> findByRimanenzaGreaterThan(int rimanenze);
    List<CardEntity> findByPrezzoScontatoGreaterThan(double prezzoScontato);
	List<CardEntity> findByNomeContainingIgnoreCase(String nome);
	List<CardEntity> findByPrezzoBetween(double minPrezzo, double maxPrezzo);
	Page<CardEntity> findByAdmin(AdminEntity admin, Pageable pageable);
	Page<CardEntity> findByAdmin_Email(String email, Pageable pageable);
	
//	 @Query("""
//		        SELECT c, COUNT(d.id) as vendite 
//		        FROM CardEntity c 
//		        JOIN c.dettagliOrdine d 
//		        GROUP BY c.id 
//		        ORDER BY vendite DESC 
//		        LIMIT 5
//		        """)
//		    List<CardEntity> findTop5SellingCards();

}
