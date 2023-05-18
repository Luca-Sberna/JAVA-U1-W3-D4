package dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import entities.Evento;
import entities.Partecipazione;

public class PartecipazioneDAO {
	private EntityManagerFactory entityManagerFactory;

	public PartecipazioneDAO(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	public void save(Partecipazione partecipazione) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(partecipazione);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public Partecipazione getById(UUID uuid) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Partecipazione persona = entityManager.find(Partecipazione.class, uuid);
		entityManager.close();
		return persona;
	}

	public void delete(Partecipazione partecipazione) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		partecipazione = entityManager.merge(partecipazione);
		entityManager.remove(partecipazione);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public void refresh(Partecipazione partecipazione) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		partecipazione = entityManager.merge(partecipazione);
		entityManager.refresh(partecipazione);
		entityManager.close();
	}

	public List<Partecipazione> getPartecipazioniDaConfermarePerEvento(Evento evento) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		TypedQuery<Partecipazione> query = entityManager.createQuery(
				"SELECT p FROM Partecipazione p WHERE p.evento = :evento AND p.confermata = false",
				Partecipazione.class);
		query.setParameter("evento", evento);
		List<Partecipazione> result = query.getResultList();
		entityManager.close();
		return result;
	}
}
