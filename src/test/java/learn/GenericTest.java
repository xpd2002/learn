package learn;

import javax.persistence.EntityManagerFactory;

import txp.learn.hibernate.jpa.service.EntityManagerFactoryProvider;

public class GenericTest {
    
    private EntityManagerFactory entityManagerFactory;
    
    public GenericTest() {
        EntityManagerFactoryProvider entityManagerFactoryProvider = new EntityManagerFactoryProvider();
        entityManagerFactoryProvider.setDriver("com.mysql.jdbc.Driver");
        entityManagerFactoryProvider.setUrl("jdbc:mysql://localhost:3306/test");
        entityManagerFactoryProvider.setUser("root");
        entityManagerFactoryProvider.setPassword("xpd_422725");
        
        entityManagerFactory = entityManagerFactoryProvider.getEntityManagerFactory();
    }
    
    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

}
