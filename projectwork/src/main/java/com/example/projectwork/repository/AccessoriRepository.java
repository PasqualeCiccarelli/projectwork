package com.example.projectwork.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.projectwork.entity.AccessoriEntity;
import com.example.projectwork.entity.CardEntity;
import com.example.projectwork.entity.entityenum.Brand;
import com.example.projectwork.entity.entityenum.Categoria;
import com.example.projectwork.entity.entityenum.Tipo;

public interface AccessoriRepository extends JpaRepository<AccessoriEntity, Long>{
	
	List<AccessoriEntity> findByBrand(Brand brand);
	List<AccessoriEntity> findByBrandAndCategoria(Brand brand, Categoria categoria);
	
	List<AccessoriEntity> findByCategoria(Categoria categoria);
	List<AccessoriEntity> findByTipo(Tipo tipo);
	List<AccessoriEntity> findByDisponibilita(boolean disponibilita);
	List<AccessoriEntity> findByRimanenzeGreaterThan(int rimanenze);
	@Query("SELECT c FROM CardEntity c WHERE c.prezzo_scontato > 0")
    List<AccessoriEntity> findCarteInSconto();
	List<AccessoriEntity> findByNomeContainingIgnoreCase(String nome);
	List<AccessoriEntity> findByPrezzoBetween(double minPrezzo, double maxPrezzo);

}
