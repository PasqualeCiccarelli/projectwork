package com.example.projectwork.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


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

}