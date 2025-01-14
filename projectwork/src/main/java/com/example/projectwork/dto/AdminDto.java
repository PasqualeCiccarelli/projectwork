package com.example.projectwork.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


import com.example.projectwork.entity.AccessorioEntity;
import com.example.projectwork.entity.AdminEntity;
import com.example.projectwork.entity.BoxEntity;
import com.example.projectwork.entity.BustinaEntity;
import com.example.projectwork.entity.CardEntity;
import com.example.projectwork.entity.entityenum.Ruolo;

public class AdminDto {

	private Long id;
	private String nome;
	private String cognome;
	private String email;
	private String password;
	private Ruolo ruolo;
	private LocalDate dataNascita;
	private List<CardDto> carte;
	private List<AccessoriDto> accessori;
	private List<BustinaDto> bustine;
	private List<BoxDto> box;

	public AdminDto() {
		
	}

		

	public AdminDto(Long id, String nome, String cognome, String email, String password, Ruolo ruolo,
			LocalDate dataNascita, List<CardDto> carte, List<AccessoriDto> accessori, List<BustinaDto> bustine,
			List<BoxDto> box) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
		this.ruolo = ruolo;
		this.dataNascita = dataNascita;
		this.carte = carte;
		this.accessori = accessori;
		this.bustine = bustine;
		this.box = box;
	}

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getCognome() {
		return cognome;
	}



	public void setCognome(String cognome) {
		this.cognome = cognome;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public Ruolo getRuolo() {
		return ruolo;
	}



	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}



	public LocalDate getDataNascita() {
		return dataNascita;
	}



	public void setDataNascita(LocalDate dataNascita) {
		this.dataNascita = dataNascita;
	}



	public List<CardDto> getCarte() {
		return carte;
	}



	public void setCarte(List<CardDto> carte) {
		this.carte = carte;
	}



	public List<AccessoriDto> getAccessori() {
		return accessori;
	}



	public void setAccessori(List<AccessoriDto> accessori) {
		this.accessori = accessori;
	}



	public List<BustinaDto> getBustine() {
		return bustine;
	}



	public void setBustine(List<BustinaDto> bustine) {
		this.bustine = bustine;
	}



	public List<BoxDto> getBox() {
		return box;
	}



	public void setBox(List<BoxDto> box) {
		this.box = box;
	}



	public static AdminDto fromEntity(AdminEntity entity) {
		if (entity == null)
			return null;

		AdminDto dto = new AdminDto();
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
		dto.setCognome(entity.getCognome());
		dto.setEmail(entity.getEmail());
		dto.setPassword(entity.getPassword());
		dto.setRuolo(entity.getRuolo());
		dto.setDataNascita(entity.getData_nascita());

		if (entity.getCarte() != null) {
			dto.setCarte(entity.getCarte().stream().map(CardDto::fromEntity).collect(Collectors.toList()));
		}

		if (entity.getAccessori() != null) {
			dto.setAccessori(entity.getAccessori().stream().map(AccessoriDto::fromEntity).collect(Collectors.toList()));
		}
		
		if (entity.getBustine() != null) {
			dto.setBustine(entity.getBustine().stream().map(BustinaDto::fromEntity).collect(Collectors.toList()));
		}
		
		if (entity.getBox() != null) {
			dto.setBox(entity.getBox().stream().map(BoxDto::fromEntity).collect(Collectors.toList()));
		}

		return dto;
	}

	public AdminEntity toEntity(AdminEntity existingEntity) {
		AdminEntity entity = (existingEntity != null && this.getId() != null) ? existingEntity : new AdminEntity();

		entity.setNome(this.getNome());
		entity.setCognome(this.getCognome());
		entity.setEmail(this.getEmail());
		entity.setPassword(this.getPassword());
		entity.setRuolo(this.getRuolo());
		entity.setData_nascita(this.getDataNascita());

		if (this.getCarte() != null) {
			List<CardEntity> carte = this.getCarte().stream().map(cardDto -> cardDto.toEntity(null))
					.collect(Collectors.toList());
			entity.setCarte(carte);
		}

		if (this.getAccessori() != null) {
			List<AccessorioEntity> accessori = this.getAccessori().stream()
					.map(accessoriDto -> accessoriDto.toEntity(null)).collect(Collectors.toList());
			entity.setAccessori(accessori);
		}
		
		if (this.getBustine() != null) {
			List<BustinaEntity> bustine = this.getBustine().stream()
					.map(BustinaDto -> BustinaDto.toEntity(null)).collect(Collectors.toList());
			entity.setBustine(bustine);
		}
		
		if (this.getBox() != null) {
			List<BoxEntity> box = this.getBox().stream()
					.map(BoxDto -> BoxDto.toEntity(null)).collect(Collectors.toList());
			entity.setBox(box);
		}

		return entity;
	}

}
