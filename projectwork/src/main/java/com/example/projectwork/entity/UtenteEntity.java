package com.example.projectwork.entity;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.projectwork.entity.entityenum.Ruolo;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "utenti")
public class UtenteEntity implements UserDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(length = 100)
	private String nome;

	@Column(length = 100)
	private String cognome;

	@Column(length = 100, nullable = false, unique = true)
	private String email;

	@Column(length = 20, nullable = false)
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, columnDefinition = "ENUM('UTENTE')")
	private Ruolo ruolo;

	private LocalDate data_nascita;
	
	@Column(nullable = false)
    private boolean accettaPolitiche;

	@OneToMany(mappedBy = "utente", cascade = CascadeType.ALL)
	private List<OrdineEntity> ordini;

	public UtenteEntity() {
		// TODO Auto-generated constructor stub
	}

	public UtenteEntity(long id, String nome, String cognome, String email, String password, Ruolo ruolo,
			LocalDate data_nascita, boolean accettaPolitiche, List<OrdineEntity> ordini) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
		this.ruolo = ruolo;
		this.data_nascita = data_nascita;
		this.accettaPolitiche = accettaPolitiche;
		this.ordini = ordini;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public void setPassword(String password) {
		this.password = password;
	}

	public Ruolo getRuolo() {
		return ruolo;
	}

	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}

	public LocalDate getData_nascita() {
		return data_nascita;
	}

	public void setData_nascita(LocalDate data_nascita) {
		this.data_nascita = data_nascita;
	}

	public boolean isAccettaPolitiche() {
		return accettaPolitiche;
	}

	public void setAccettaPolitiche(boolean accettaPolitiche) {
		this.accettaPolitiche = accettaPolitiche;
	}

	public List<OrdineEntity> getOrdini() {
		return ordini;
	}

	public void setOrdini(List<OrdineEntity> ordini) {
		this.ordini = ordini;
	}

	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + this.ruolo.name()));
    }

	@Override
    public String getUsername() {
        return email;
    }
	
	@Override
    public String getPassword() {
        return password;
    }

	@Override
    public boolean isAccountNonExpired() {
        return true;
    }
	
	@Override
    public boolean isAccountNonLocked() {
        return true;
    }
	
	@Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
