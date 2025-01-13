package com.example.projectwork.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.example.projectwork.entity.OrdineEntity;
import com.example.projectwork.entity.UtenteEntity;
import com.example.projectwork.entity.entityenum.Ruolo;

public class UtenteDto {

	private Long id;
	private String nome;
	private String cognome;
	private String email;
	private String password;
	private Ruolo ruolo;
	private LocalDate dataNascita;
	private List<OrdineDto> ordini;

	public UtenteDto() {
		
	}

	public UtenteDto(Long id, String nome, String cognome, String email, String password, Ruolo ruolo,
			LocalDate dataNascita, List<OrdineDto> ordini) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
		this.ruolo = ruolo;
		this.dataNascita = dataNascita;
		this.ordini = ordini;
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

	public List<OrdineDto> getOrdini() {
		return ordini;
	}

	public void setOrdini(List<OrdineDto> ordini) {
		this.ordini = ordini;
	}

	public static UtenteDto fromEntity(UtenteEntity entity) {
		if (entity == null)
			return null;

		UtenteDto dto = new UtenteDto();
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
		dto.setCognome(entity.getCognome());
		dto.setEmail(entity.getEmail());
		dto.setPassword(entity.getPassword());
		dto.setRuolo(entity.getRuolo());
		dto.setDataNascita(entity.getData_nascita());

		if (entity.getOrdini() != null) {
			dto.setOrdini(entity.getOrdini().stream().map(OrdineDto::fromEntity).collect(Collectors.toList()));
		}

		return dto;
	}

	public UtenteEntity toEntity(UtenteEntity existingEntity) {
		UtenteEntity entity = (existingEntity != null && this.getId() != null) ? existingEntity : new UtenteEntity();

		entity.setNome(this.getNome());
		entity.setCognome(this.getCognome());
		entity.setEmail(this.getEmail());
		entity.setPassword(this.getPassword());
		entity.setRuolo(this.getRuolo());
		entity.setData_nascita(this.getDataNascita());

		if (this.getOrdini() != null) {
			List<OrdineEntity> ordineEntities = this.getOrdini().stream().map(ordineDto -> ordineDto.toEntity(null))
					.collect(Collectors.toList());
			entity.setOrdini(ordineEntities);
		}

		return entity;
	}

}
