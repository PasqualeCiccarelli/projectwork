package com.example.projectwork.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
	List<CardEntity> findByRimanenzeGreaterThan(int rimanenze);
	@Query("SELECT c FROM CardEntity c WHERE c.prezzo_scontato > 0")
    List<CardEntity> findCarteInSconto();
	List<CardEntity> findByNomeContainingIgnoreCase(String nome);
	List<CardEntity> findByPrezzoBetween(double minPrezzo, double maxPrezzo);

}
