package dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import entities.Persona;

public class PersonaDAO {
	private EntityManagerFactory entityManagerFactory;

	public PersonaDAO(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	public void save(Persona persona) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(persona);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public Persona getById(UUID uuid) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Persona persona = entityManager.find(Persona.class, uuid);
		entityManager.close();
		return persona;
	}

	public void delete(Persona persona) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		persona = entityManager.merge(persona);
		entityManager.remove(persona);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public void update(Persona persona) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(persona);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

}
