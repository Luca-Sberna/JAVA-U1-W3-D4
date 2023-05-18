package dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import entities.Concerto;
import entities.Concerto.GenereConcerto;
import entities.Evento;
import entities.GaraDiAtletica;
import entities.PartitaDiCalcio;
import entities.Persona;

public class EventoDAO {
	private EntityManagerFactory entityManagerFactory;

	public EventoDAO(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	public void save(Evento evento) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(evento);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public Evento getById(UUID uuid) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Evento evento = entityManager.find(Evento.class, uuid);
		entityManager.close();
		return evento;
	}

	public void delete(Evento evento) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		evento = entityManager.merge(evento);
		entityManager.remove(evento);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public void refresh(Evento evento) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		evento = entityManager.merge(evento);
		entityManager.refresh(evento);
		entityManager.close();
	}

	public List<Concerto> getConcertiInStreaming(boolean inStreaming) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		TypedQuery<Concerto> query = entityManager
				.createQuery("SELECT c FROM Concerto c WHERE c.inStreaming = :inStreaming", Concerto.class);
		query.setParameter("inStreaming", inStreaming);
		List<Concerto> result = query.getResultList();
		entityManager.close();
		return result;
	}

	public List<Concerto> getConcertiPerGenere(List<GenereConcerto> generi) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		TypedQuery<Concerto> query = entityManager.createQuery("SELECT c FROM Concerto c WHERE c.genere IN :generi",
				Concerto.class);
		query.setParameter("generi", generi);
		List<Concerto> result = query.getResultList();
		entityManager.close();
		return result;
	}

	public List<PartitaDiCalcio> getPartiteVinteInCasa() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		TypedQuery<PartitaDiCalcio> query = entityManager.createQuery(
				"SELECT p FROM PartitaDiCalcio p WHERE p.squadraVincente = p.squadraDiCasa", PartitaDiCalcio.class);
		List<PartitaDiCalcio> result = query.getResultList();
		entityManager.close();
		return result;
	}

	public List<PartitaDiCalcio> getPartiteVinteInTrasferta() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		TypedQuery<PartitaDiCalcio> query = entityManager.createQuery(
				"SELECT p FROM PartitaDiCalcio p WHERE p.squadraVincente = p.squadraOspite", PartitaDiCalcio.class);
		List<PartitaDiCalcio> result = query.getResultList();
		entityManager.close();
		return result;
	}

	public List<PartitaDiCalcio> getPartitePareggiate() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		TypedQuery<PartitaDiCalcio> query = entityManager
				.createQuery("SELECT p FROM PartitaDiCalcio p WHERE p.squadraVincente IS NULL", PartitaDiCalcio.class);
		List<PartitaDiCalcio> result = query.getResultList();
		entityManager.close();
		return result;
	}

	public List<GaraDiAtletica> getGareDiAtleticaPerVincitore(Persona vincitore) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		TypedQuery<GaraDiAtletica> query = entityManager
				.createQuery("SELECT g FROM GaraDiAtletica g WHERE g.vincitore = :vincitore", GaraDiAtletica.class);
		query.setParameter("vincitore", vincitore);
		List<GaraDiAtletica> result = query.getResultList();
		entityManager.close();
		return result;
	}

	public List<Evento> getEventiSoldOut() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		TypedQuery<Evento> query = entityManager.createQuery(
				"SELECT e FROM Evento e WHERE SIZE(e.partecipazioni) = e.numeroMassimoPartecipanti", Evento.class);
		List<Evento> result = query.getResultList();
		entityManager.close();
		return result;
	}

}
