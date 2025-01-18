package com.example.projectwork.service.interf;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.example.projectwork.dto.AccessoriDto;
import com.example.projectwork.dto.AdminDto;
import com.example.projectwork.dto.BoxDto;
import com.example.projectwork.dto.BustinaDto;
import com.example.projectwork.dto.CardDto;
import com.example.projectwork.dto.ProdottoDto;
import com.example.projectwork.entity.AdminEntity;
import com.example.projectwork.entity.ProdottoEntity;



public interface AdminService {
	
	public List<CardDto> getCardByAdmin(String email);
	public List<BoxDto> getBoxByAdmin(String email);
	public List<BustinaDto> getBustineByAdmin(String email);
	public List<AccessoriDto> getAccessoriByAdmin(String email);
	public Page<ProdottoEntity> getProdottiByAdmin(String email, int page, int size);
	public AdminDto getAdminByEmail(String email);
	public boolean eliminaProdotto(Long id);
}
