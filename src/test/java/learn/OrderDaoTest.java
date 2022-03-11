package learn;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import txp.learn.hibernate.jpa.oracleexample.dao.OrderDao;
import txp.learn.hibernate.jpa.oracleexample.model.Order;
import txp.learn.hibernate.jpa.service.EntityManagerFactoryProvider;

public class OrderDaoTest {
	
	private OrderDao getDao() {
		EntityManagerFactoryProvider provider = new EntityManagerFactoryProvider();
		provider.setDriver("com.mysql.jdbc.Driver");
		provider.setUrl("jdbc:mysql://localhost:3306/test");
		provider.setUser("root");
		provider.setPassword("xpd_422725");
		OrderDao dao = new OrderDao(provider.getEntityManagerFactory());
		return dao;
	}

	@Test
	public void save() {
		OrderDao dao = getDao();
		
		Order order = new Order();
		order.setLastUpdate(new Date());
		order.setShipmentInfo("N/A");
		order.setStatus('P');
		
		dao.save(order);
	}

}
