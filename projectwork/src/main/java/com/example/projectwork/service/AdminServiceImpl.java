package com.example.projectwork.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.projectwork.dto.AccessoriDto;
import com.example.projectwork.dto.BoxDto;
import com.example.projectwork.dto.BustinaDto;
import com.example.projectwork.dto.CardDto;
import com.example.projectwork.entity.AdminEntity;
import com.example.projectwork.repository.AdminRepository;
import com.example.projectwork.service.interf.AdminService;


@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminRepository adminRepository;
	
	
	public List<CardDto> getCardByAdmin(String email){
		
		AdminEntity admin= adminRepository.findByEmail(email)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin non trovato!"));
		
		return admin.getCarte().stream().map(CardDto::fromEntity).collect(Collectors.toList());
	}


	@Override
	public List<BoxDto> getBoxByAdmin(String email) {

		AdminEntity admin= adminRepository.findByEmail(email)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin non trovato!"));
		
		return admin.getBox().stream().map(BoxDto::fromEntity).collect(Collectors.toList());
	}


	@Override
	public List<BustinaDto> getBustineByAdmin(String email) {

		AdminEntity admin= adminRepository.findByEmail(email)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin non trovato!"));
		
		return admin.getBustine().stream().map(BustinaDto::fromEntity).collect(Collectors.toList());
	}


	@Override
	public List<AccessoriDto> getAccessoriByAdmin(String email) {

		AdminEntity admin= adminRepository.findByEmail(email)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin non trovato!"));
		
		return admin.getAccessori().stream().map(AccessoriDto::fromEntity).collect(Collectors.toList());
	}	
}























