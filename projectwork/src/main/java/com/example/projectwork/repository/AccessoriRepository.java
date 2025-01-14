package com.example.projectwork.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.projectwork.entity.AccessorioEntity;
import com.example.projectwork.entity.entityenum.Brand;
import com.example.projectwork.entity.entityenum.Categoria;
import com.example.projectwork.entity.entityenum.Tipo;

public interface AccessoriRepository extends JpaRepository<AccessorioEntity, Long> {
    
    List<AccessorioEntity> findByBrand(Brand brand);
    List<AccessorioEntity> findByBrandAndCategoria(Brand brand, Categoria categoria);
    List<AccessorioEntity> findByCategoria(Categoria categoria);
    List<AccessorioEntity> findByTipo(Tipo tipo);
    List<AccessorioEntity> findByDisponibilita(boolean disponibilita);
    List<AccessorioEntity> findByRimanenzaGreaterThan(int rimanenza);
    List<AccessorioEntity> findByPrezzoScontatoGreaterThan(double prezzoScontato);
    List<AccessorioEntity> findByNomeContainingIgnoreCase(String nome);
    List<AccessorioEntity> findByPrezzoBetween(double minPrezzo, double maxPrezzo);
    List<AccessorioEntity> findByBrandAndTipo(Brand brand, Tipo tipo);
}