package entities;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "eventi")
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Evento {
	@Id
	@GeneratedValue

	private UUID id;
	private String titolo;
	private Date dataEvento;
	private String descrizione;
	@Enumerated(EnumType.STRING)
	private TipoEvento tipoEvento;
	private Integer numeroMassimoPartecipanti;

	@OneToMany(mappedBy = "evento", cascade = CascadeType.REMOVE)
	private Set<Partecipazione> partecipazioni;

	@ManyToOne
	private Location location;

	public enum TipoEvento {
		PUBBLICO, PRIVATO
	}

	public Evento(String titolo, Date dataEvento, String descrizione, TipoEvento tipoEvento,
			Integer numeroMassimoPartecipanti, Location location) {
		this.titolo = titolo;
		this.dataEvento = dataEvento;
		this.descrizione = descrizione;
		this.tipoEvento = tipoEvento;
		this.numeroMassimoPartecipanti = numeroMassimoPartecipanti;
		this.location = location;

	}

	@Override
	public String toString() {
		return "Evento [id = " + id + ", title = " + titolo + ", dataEvento = " + dataEvento + ", descrizione = "
				+ descrizione + ", tipoEvento = " + tipoEvento + ", numMaxPartecipanti = " + numeroMassimoPartecipanti
				+ ", location = " + location + ", partecipazioniEvento = " + partecipazioni;
	}

}
