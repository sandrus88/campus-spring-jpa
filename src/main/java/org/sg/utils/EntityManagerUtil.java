package org.sg.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {

	public static final String PERSISTENCE_UNIT_NAME = "hello-jpa-pu";
	private static EntityManager entityManager;

	private EntityManagerUtil() {
	}

	public static EntityManager getEntityManager() {
		if (entityManager == null) {
			EntityManagerFactory emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

			return emFactory.createEntityManager();
		}
		return entityManager;
	}

}
