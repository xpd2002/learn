package txp.learn.hibernate.jpa.service;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryProvider {

	private String driver;
	private String url;
	private String user;
	private String password;
	private EntityManagerFactory entityManagerFactory;
	
	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public EntityManagerFactory getEntityManagerFactory() {
		if (this.entityManagerFactory == null) {
			Map properties = new HashMap();
			properties.put("javax.persistence.jdbc.driver", this.driver);
			properties.put("javax.persistence.jdbc.url", this.url);
			properties.put("javax.persistence.jdbc.user", this.user);
			properties.put("javax.persistence.jdbc.password", this.password);
			this.entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa", properties);
		}
		return this.entityManagerFactory;
	}
	
}
