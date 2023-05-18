package entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class GaraDiAtletica extends Evento {
	@ManyToMany
	private Set<Persona> atleti;
	@OneToOne
	private Persona vincitore;

}
