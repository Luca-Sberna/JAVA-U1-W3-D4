package entities;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "partecipazioni")
@NoArgsConstructor
public class Partecipazione {
	@Id
	@GeneratedValue
	private UUID id;
	private String persona;

	@ManyToOne
	private Evento evento;

	@Enumerated(EnumType.STRING)
	private TipoEvento stato;

	public enum TipoEvento {
		CONFERMATA, DA_CONFERMARE
	}

	public Partecipazione(String persona, Evento evento, TipoEvento stato) {
		this.persona = persona;
		this.evento = evento;
		this.stato = stato;

	}

}
