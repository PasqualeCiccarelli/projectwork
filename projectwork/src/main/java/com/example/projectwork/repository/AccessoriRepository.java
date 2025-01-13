package com.example.projectwork.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projectwork.entity.AccessoriEntity;
import com.example.projectwork.entity.entityenum.Brand;

public interface AccessoriRepository extends JpaRepository<AccessoriEntity, Long>{
	
	List<AccessoriEntity> findByBrand(Brand brand);

}
