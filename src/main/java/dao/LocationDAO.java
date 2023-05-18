package dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import entities.Location;

public class LocationDAO {
	private EntityManagerFactory entityManagerFactory;

	public LocationDAO(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	public void save(Location location) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(location);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public Location getById(UUID uuid) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Location persona = entityManager.find(Location.class, uuid);
		entityManager.close();
		return persona;
	}

	public void delete(Location location) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		location = entityManager.merge(location);
		entityManager.remove(location);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public void refresh(Location location) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		location = entityManager.merge(location);
		entityManager.refresh(location);
		entityManager.close();
	}
}
