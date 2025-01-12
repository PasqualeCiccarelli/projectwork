package com.example.projectwork.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projectwork.entity.AdminEntity;

public interface AdminRepository extends JpaRepository<AdminEntity, Long>{
	
	Optional<AdminEntity> findByEmail(String Email);
}
