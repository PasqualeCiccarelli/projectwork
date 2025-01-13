package com.example.projectwork.restCtrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projectwork.dto.AccessoriDto;
import com.example.projectwork.dto.BoxDto;
import com.example.projectwork.dto.BustinaDto;
import com.example.projectwork.dto.CardDto;
import com.example.projectwork.service.interf.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminCtrl {
	
	@Autowired
	private AdminService adminService;
	
	
	@GetMapping("/{email}/cards")
	public ResponseEntity<List<CardDto>> getCardsByAdmin(@PathVariable String email){
		
		List<CardDto> lista= adminService.getCardByAdmin(email);
		
		return ResponseEntity.ok(lista);
	}
	
	
	@GetMapping("/{email}/box")
	public ResponseEntity<List<BoxDto>> getBoxByAdmin(@PathVariable String email){
		
		List<BoxDto> lista= adminService.getBoxByAdmin(email);
		
		return ResponseEntity.ok(lista);
	}
	
	
	@GetMapping("/{email}/bustine")
	public ResponseEntity<List<BustinaDto>> getBustineByAdmin(@PathVariable String email){
		
		List<BustinaDto> lista= adminService.getBustineByAdmin(email);
		
		return ResponseEntity.ok(lista);
	}
	
	
	@GetMapping("/{email}/bustine")
	public ResponseEntity<List<AccessoriDto>> getAccessoriByAdmin(@PathVariable String email){
		
		List<AccessoriDto> lista= adminService.getAccessoriByAdmin(email);
		
		return ResponseEntity.ok(lista);
	}

}

























