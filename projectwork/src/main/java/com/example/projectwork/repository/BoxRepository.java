package com.example.projectwork.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projectwork.entity.AccessoriEntity;
import com.example.projectwork.entity.BoxEntity;
import com.example.projectwork.entity.entityenum.Brand;
import com.example.projectwork.entity.entityenum.Categoria;

public interface BoxRepository extends JpaRepository<BoxEntity, Long>{
	
	List<BoxEntity> findByBrand(Brand brand);
	List<BoxEntity> findByBrandAndCategoria(Brand brand, Categoria categoria);

}
