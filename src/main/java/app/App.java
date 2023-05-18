package app;

import java.sql.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dao.EventoDAO;
import dao.LocationDAO;
import dao.PartecipazioneDAO;
import dao.PersonaDAO;
import entities.Evento;
import entities.Location;
import entities.Partecipazione;
import entities.Persona;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JavaUnit1Week3Day2");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		EventoDAO eventoDAO = new EventoDAO(entityManagerFactory);
		PersonaDAO personaDAO = new PersonaDAO(entityManagerFactory);
		LocationDAO locationDAO = new LocationDAO(entityManagerFactory);
		PartecipazioneDAO partecipazioneDAO = new PartecipazioneDAO(entityManagerFactory);

		// Creo una persona
		Date dataDiNascita = Date.valueOf("1996-04-30");
		Persona persona = new Persona("Luca", "Salazar", "Salazar.Luca@email.it", dataDiNascita,
				Persona.TipoEvento.MASCHIO, 0);
		personaDAO.save(persona);

		// Creo una location
		Location location = new Location("Piazza del Duomo", "Milano");
		locationDAO.save(location);

		// Creo un evento
		Evento evento = new Evento("Era un Concerto", new java.util.Date(), "Concerto di musica morta",
				Evento.TipoEvento.PUBBLICO, 100000, location);
		eventoDAO.save(evento);

		// Creo una partecipazione
		Partecipazione partecipazione = new Partecipazione(persona.getId().toString(), evento,
				Partecipazione.TipoEvento.CONFERMATA);
		partecipazioneDAO.save(partecipazione);
		// Aumento il numero delle partecipazioni della persona
		persona.setListaPartecipazioni(persona.getListaPartecipazioni() + 1);
		personaDAO.update(persona);

		log.info("-------------------------");
		log.info(location.toString());
		log.info("-------------------------");
		log.info(evento.toString());
		log.info("-------------------------");
		log.info(partecipazione.toString());
		log.info("-------------------------");
		log.info(persona.toString());
		log.info("-------------------------");

		entityManager.close();
		entityManagerFactory.close();
	}
}
