package org.sg.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GenericDao {

	public static final String PERSISTENCE_UNIT_NAME = "campus-pu";
	private EntityManagerFactory emFactory;
	protected EntityManager entityManager;

	protected GenericDao() {
		emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		entityManager = emFactory.createEntityManager();
		if (entityManager == null) {
			throw new IllegalStateException("EntityManager for persistence '" + PERSISTENCE_UNIT_NAME + "' is null");
		}
	}

}
