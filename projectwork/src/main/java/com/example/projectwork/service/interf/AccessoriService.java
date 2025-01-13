package com.example.projectwork.service.interf;

import java.util.List;

import com.example.projectwork.dto.AccessoriDto;
import com.example.projectwork.dto.AccessoriRequest;
import com.example.projectwork.entity.AccessoriEntity;

public interface AccessoriService {
	
	 public AccessoriEntity creaAccessorio(AccessoriRequest accessoriRequest);
	 public List<AccessoriDto> getAccessoriByBrandPokemon();
	 public List<AccessoriDto> getAccessoriByBrandMagic();
	 public List<AccessoriDto> getAccessoriByBrandYugiho();
	 
	 

}
