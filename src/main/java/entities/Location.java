package entities;

import java.util.UUID;

import javax.persistence.Entity;
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
@Table(name = "locations")
@NoArgsConstructor
public class Location {
	@Id
	@GeneratedValue

	private UUID id;
	private String nome;
	private String città;

	public Location(String nome, String città) {
		this.nome = nome;
		this.città = città;

	}

}
