package com.example.projectwork.service.interf;

import java.util.List;

import com.example.projectwork.dto.AccessoriDto;
import com.example.projectwork.dto.BustinaDto;
import com.example.projectwork.dto.BustinaRequest;
import com.example.projectwork.entity.BustinaEntity;
import com.example.projectwork.entity.entityenum.Brand;
import com.example.projectwork.entity.entityenum.Categoria;

public interface BustinaService {
	
	public BustinaEntity creaBustina(BustinaRequest bustinaRequest);
	public List<BustinaDto> getBustineByBrandPokemon();
	public List<BustinaDto> getBustineByBrandMagic();
	public List<BustinaDto> getBustineByBrandYugiho();
	public List<BustinaDto> getCardByAccessori(Brand brand, Categoria categoria);

}
