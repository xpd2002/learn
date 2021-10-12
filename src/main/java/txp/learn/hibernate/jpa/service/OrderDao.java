package txp.learn.hibernate.jpa.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import txp.learn.hibernate.jpa.model.Order;

public class OrderDao {
	
	private EntityManagerFactory entityManagerFactory;

	public OrderDao(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}
	
	public void save(Order order) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(order);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

}
