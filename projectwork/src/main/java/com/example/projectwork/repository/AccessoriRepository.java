package com.example.projectwork.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projectwork.entity.AccessoriEntity;
import com.example.projectwork.entity.entityenum.Brand;
import com.example.projectwork.entity.entityenum.Categoria;
import com.example.projectwork.entity.entityenum.Tipo;

public interface AccessoriRepository extends JpaRepository<AccessoriEntity, Long>{
	
	List<AccessoriEntity> findByBrand(Brand brand);
	List<AccessoriEntity> findByBrandAndCategoria(Brand brand, Categoria categoria);
	List<AccessoriEntity> findByBrandAndTipo(Brand brand, Tipo tipo);

}
