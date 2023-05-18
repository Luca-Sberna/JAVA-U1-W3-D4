package entities;

import java.sql.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "persone")
@NoArgsConstructor
public class Persona {
	@Id
	@GeneratedValue

	private UUID id;
	private String nome;
	private String cognome;
	private String email;
	private Date dataDiNascita;
	@Enumerated(EnumType.STRING)
	private TipoEvento sesso;
	private Integer listaPartecipazioni;

	public enum TipoEvento {
		MASCHIO, FEMMINA
	}

	public Persona(String nome, String cognome, String email, Date dataDiNascita, TipoEvento sesso,
			Integer listaPartecipazioni) {
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.dataDiNascita = dataDiNascita;
		this.sesso = sesso;
		this.listaPartecipazioni = listaPartecipazioni;

	}

}
