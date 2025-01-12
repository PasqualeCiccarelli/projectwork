package com.example.projectwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projectwork.entity.CardEntity;

public interface CardRepository extends JpaRepository<CardEntity, Long>{

}
