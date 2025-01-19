package com.example.projectwork.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.projectwork.dto.AccessoriDto;
import com.example.projectwork.dto.AdminDto;
import com.example.projectwork.dto.BoxDto;
import com.example.projectwork.dto.BustinaDto;
import com.example.projectwork.dto.BustinaRequest;
import com.example.projectwork.dto.CardDto;
import com.example.projectwork.dto.ProdottoDto;
import com.example.projectwork.entity.AccessorioEntity;
import com.example.projectwork.entity.AdminEntity;
import com.example.projectwork.entity.BoxEntity;
import com.example.projectwork.entity.BustinaEntity;
import com.example.projectwork.entity.CardEntity;
import com.example.projectwork.entity.ProdottoEntity;
import com.example.projectwork.entity.UtenteEntity;
import com.example.projectwork.repository.AccessoriRepository;
import com.example.projectwork.repository.AdminRepository;
import com.example.projectwork.repository.BoxRepository;
import com.example.projectwork.repository.BustineRepository;
import com.example.projectwork.repository.CardRepository;
import com.example.projectwork.repository.ProdottoRepository;
import com.example.projectwork.service.interf.AdminService;


@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private CardRepository cardRepository;
	
	@Autowired
	private BustineRepository bustinaRepository;
	
	@Autowired
	private BoxRepository boxRepository;
	
	@Autowired
	private AccessoriRepository accessoriRepository;
	
	@Autowired
	private ProdottoRepository prodottoRepository;
	
	
	
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
	
	public AdminDto getAdminByEmail(String email) {
        AdminEntity admin = adminRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Admin non trovato"));

        return AdminDto.fromEntity(admin);
    }


	@Override
	public List<AccessoriDto> getAccessoriByAdmin(String email) {

		AdminEntity admin= adminRepository.findByEmail(email)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin non trovato!"));
		
		return admin.getAccessori().stream().map(AccessoriDto::fromEntity).collect(Collectors.toList());
	}
	
	 public Page<ProdottoEntity> getProdottiByAdmin(String email, int page, int size) {
	        Pageable pageable = PageRequest.of(page, size);
	        
	        List<ProdottoEntity> allProdotti = new ArrayList<>();
	        
	        // Aggiungiamo tutti i prodotti in una lista
	        allProdotti.addAll(cardRepository.findByAdmin_Email(email, Pageable.unpaged()).getContent());
	        allProdotti.addAll(boxRepository.findByAdmin_Email(email, Pageable.unpaged()).getContent());
	        allProdotti.addAll(bustinaRepository.findByAdmin_Email(email, Pageable.unpaged()).getContent());
	        allProdotti.addAll(accessoriRepository.findByAdmin_Email(email, Pageable.unpaged()).getContent());
	        
	        // Implementiamo la paginazione manualmente
	        int start = (int) pageable.getOffset();
	        int end = Math.min((start + pageable.getPageSize()), allProdotti.size());
	        
	        return new PageImpl<>(
	            allProdotti.subList(start, end),
	            pageable,
	            allProdotti.size()
	        );
	    }
	 
	 public boolean eliminaProdotto(Long id) {
	        
	        Optional<ProdottoEntity> prodotto = prodottoRepository.findById(id);
	        if (!prodotto.isPresent()) {
	            return false;
	        }

	        prodottoRepository.deleteById(id);
	        return true;
	    }
	 
}
