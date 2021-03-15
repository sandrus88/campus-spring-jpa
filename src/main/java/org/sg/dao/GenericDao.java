package org.sg.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GenericDao {

	public static final String PERSISTENCE_UNIT_NAME = "campus-pu";
	public EntityManagerFactory emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	public EntityManager entityManager = emFactory.createEntityManager();
}
