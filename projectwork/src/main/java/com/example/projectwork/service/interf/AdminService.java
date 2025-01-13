package com.example.projectwork.service.interf;

import java.util.List;

import com.example.projectwork.dto.AccessoriDto;
import com.example.projectwork.dto.BoxDto;
import com.example.projectwork.dto.BustinaDto;
import com.example.projectwork.dto.CardDto;

public interface AdminService {
	
	public List<CardDto> getCardByAdmin(String email);
	public List<BoxDto> getBoxByAdmin(String email);
	public List<BustinaDto> getBustineByAdmin(String email);
	public List<AccessoriDto> getAccessoriByAdmin(String email);
}
