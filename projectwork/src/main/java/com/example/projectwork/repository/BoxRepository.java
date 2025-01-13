package com.example.projectwork.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.projectwork.entity.BoxEntity;
import com.example.projectwork.entity.entityenum.Brand;
import com.example.projectwork.entity.entityenum.Categoria;
import com.example.projectwork.entity.entityenum.Tipo;

public interface BoxRepository extends JpaRepository<BoxEntity, Long>{
	
	List<BoxEntity> findByBrand(Brand brand);
	
	List<BoxEntity> findByCategoria(Categoria categoria);
	List<BoxEntity> findByTipo(Tipo tipo);
	List<BoxEntity> findByDisponibilita(boolean disponibilita);
	List<BoxEntity> findByRimanenzaGreaterThan(int rimanenza);
	@Query("SELECT c FROM CardEntity c WHERE c.prezzo_scontato > 0")
    List<BoxEntity> findCarteInSconto();
	List<BoxEntity> findByNomeContainingIgnoreCase(String nome);
	List<BoxEntity> findByPrezzoBetween(double minPrezzo, double maxPrezzo);
	List<BoxEntity> findByBrandAndCategoria(Brand brand, Categoria categoria);

}
