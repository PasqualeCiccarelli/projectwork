package com.example.projectwork.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projectwork.entity.AccessoriEntity;
import com.example.projectwork.entity.BustinaEntity;
import com.example.projectwork.entity.entityenum.Brand;
import com.example.projectwork.entity.entityenum.Categoria;

public interface BustineRepository extends JpaRepository<BustinaEntity, Long>{
	
	List<BustinaEntity> findByBrand(Brand brand);
	List<BustinaEntity> findByBrandAndCategoria(Brand brand, Categoria categoria);

}
