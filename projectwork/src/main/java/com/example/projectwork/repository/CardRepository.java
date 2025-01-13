package com.example.projectwork.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projectwork.entity.CardEntity;
import com.example.projectwork.entity.entityenum.Brand;

public interface CardRepository extends JpaRepository<CardEntity, Long>{
	
	List<CardEntity> findByBrand(Brand brand);

}
