package entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Concerto extends Evento {
	public enum GenereConcerto {
		CLASSICO, ROCK, POP
	}

	@Enumerated(EnumType.STRING)
	private GenereConcerto genere;
	private boolean inStreaming;

}
