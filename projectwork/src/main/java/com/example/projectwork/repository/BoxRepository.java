package com.example.projectwork.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projectwork.entity.BoxEntity;
import com.example.projectwork.entity.entityenum.Brand;

public interface BoxRepository extends JpaRepository<BoxEntity, Long>{
	
	List<BoxEntity> findByBrand(Brand brand);

}
