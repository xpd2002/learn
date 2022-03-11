package txp.learn.hibernate.jpa.myexample;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import learn.GenericTest;
import txp.learn.hibernate.jpa.myexample.model.EmbeddableEntityA;
import txp.learn.hibernate.jpa.myexample.model.EntityA;
import txp.learn.hibernate.jpa.myexample.model.EntityB;
import txp.learn.hibernate.jpa.myexample.model.EntityC;
import txp.learn.hibernate.jpa.myexample.model.EntityD;
import txp.learn.hibernate.jpa.myexample.model.EntityE;
import txp.learn.hibernate.jpa.myexample.model.OneToManyBDMapCase21EntityA;
import txp.learn.hibernate.jpa.myexample.model.OneToManyBDMapCase21EntityB;
import txp.util.ObjectUtil;

public class DaoTest extends GenericTest {
    
    private EntityManager em;
    private ObjectUtil ou = new ObjectUtil();
    
    private void printInstnace(Object obj) throws Exception {
        System.out.println(ou.print(ou.export(obj, new ArrayList<>()), 0));
    }
    
    
    private void printEntityA(EntityA entity) {
        if (entity == null) {
            System.out.println("EntityA: empty object");
        } else {
            System.out.println("name: " + entity.getName());
            System.out.println("creation date = " + entity.getCreationTime());
            if (entity.getEmbeddableEntity() == null) {
                System.out.println("embeddable entity: empty");
            } else {
                System.out.println("embeddable entity: "+entity.getEmbeddableEntity());
            }
            if (entity.getEntityB() == null) {
                System.out.println("entity B: empty");
            } else {
                System.out.println(entity.getEntityB());
            }
            System.out.println("entity C:");
            if (entity.getEntityC() == null) {
                System.out.println("empty");
            } else {
                
                System.out.println(entity.getEntityC());
            }
            
            if (entity.getStringList() != null && entity.getStringList().size() > 0) {
                System.out.println("string list:");
                for (String string : entity.getStringList()) {
                    System.out.println("    " + string);
                }
            } else {
                System.out.println("empty string list");
            }
            
            if (entity.getEmbeddableEntityList() != null && entity.getEmbeddableEntityList().size() > 0) {
                System.out.println("embeddable entity list:");
                for (EmbeddableEntityA embeddableEntity : entity.getEmbeddableEntityList()) {
                    System.out.println("    " + embeddableEntity.getStringField()+", "+embeddableEntity.getIntField());
                }
            } else {
                System.out.println("empty embeddable entity list");
            }
            
            System.out.println("Entity D List:");
            if (entity.getEntityDList() != null && entity.getEntityDList().size() > 0) {
                for (int i=0;i<entity.getEntityDList().size();i++) {
                    EntityD entityD = entity.getEntityDList().get(i);
                    System.out.println("Entity D#"+i+":");
                    System.out.println(entityD);
                }
            } else {
                System.out.println("empty");
            }
            
            System.out.println("Entity E List:");
            if (entity.getEntityEList() != null && entity.getEntityEList().size() > 0) {
                for (int i=0;i<entity.getEntityEList().size();i++) {
                    EntityE entityE = entity.getEntityEList().get(i);
                    System.out.println("Entity E#"+i+":");
                    System.out.println(entityE);
                }
            } else {
                System.out.println("empty");
            }
            
            if (entity.getStringMap() != null && entity.getStringMap().size() > 0) {
                System.out.println("string map:");
                for (String key : entity.getStringMap().keySet()) {
                    System.out.println("    " + key + " = " + entity.getStringMap().get(key));
                }
            } else {
                System.out.println("empty string map");
            }
            
            if (entity.getEmbeddableEntityMap() != null && entity.getEmbeddableEntityMap().size() > 0) {
                System.out.println("embeddable entity map:");
                for (EmbeddableEntityA key : entity.getEmbeddableEntityMap().keySet()) {
                    System.out.println("    " + key + " = " + entity.getEmbeddableEntityMap().get(key));
                }
            } else {
                System.out.println("empty string map");
            }
        }
        System.out.println("");
    }
    
    @Before
    public void before() {
        em = getEntityManagerFactory().createEntityManager();
    }
    
    @After
    public void after() {
        em.close();
    }
    
    private EntityA constructEntityA() {
        EntityA entity = new EntityA();
        entity.setName("test1");
        entity.setCreationTime(new Date());
        
        EmbeddableEntityA embeddableEntity = new EmbeddableEntityA("entity A's embedded entity", 120);
        entity.setEmbeddableEntity(embeddableEntity);
        
        List<String> stringList = new ArrayList<>();
        stringList.add("Toyota");
        stringList.add("Honda");
        stringList.add("Nissan");
        entity.setStringList(stringList);
        
        List<EmbeddableEntityA> embeddableEntityList = new ArrayList<>();
        embeddableEntityList.add(new EmbeddableEntityA("test1", 100));
        embeddableEntityList.add(new EmbeddableEntityA("test2", 80));
        embeddableEntityList.add(new EmbeddableEntityA("test3", 60));
        entity.setEmbeddableEntityList(embeddableEntityList);
        
        List<EntityD> entityDList = new ArrayList<>();
        entityDList.add(new EntityD("test1", entity));
        entityDList.add(new EntityD("test2", entity));
        entityDList.add(new EntityD("test3", entity));
        entity.setEntityDList(entityDList);
        
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("key1", "value1");
        stringMap.put("key2", "value2");
        stringMap.put("key3", "value3");
        entity.setStringMap(stringMap);
        
        Map<EmbeddableEntityA, EmbeddableEntityA> embeddableEntityMap = new HashMap<>();
        embeddableEntityMap.put(new EmbeddableEntityA("key1", 100), new EmbeddableEntityA("value1", 10100));
        embeddableEntityMap.put(new EmbeddableEntityA("key2", 200), new EmbeddableEntityA("value2", 10200));
        embeddableEntityMap.put(new EmbeddableEntityA("key3", 300), new EmbeddableEntityA("value3", 10300));
        entity.setEmbeddableEntityMap(embeddableEntityMap);
        
        return entity;
    }
    
    private EntityB constructEntityB() {
        EntityB result = new EntityB();
        result.setName("test1");
        return result;
    }
    
    private EntityC constructEntityC() {
        EntityC result = new EntityC();
        result.setName("test1");
        return result;
    }
    
    private EntityE constructEntityE(String name) {
        EntityE result = new EntityE();
        result.setName(name);
        return result;
    }
    
    @Test
    public void create() {
        em.getTransaction().begin();
        
        EntityA entityAToCreate = constructEntityA();
        EntityA entityAToCreate1 = constructEntityA();
        EntityB entityBToCreate = constructEntityB();
        EntityC entityCToCreate = constructEntityC();
        
        EntityE entityEToCreate1 = constructEntityE("test 1");
        EntityE entityEToCreate2 = constructEntityE("test 2");
        EntityE entityEToCreate3 = constructEntityE("test 3");
        
        entityAToCreate.setEntityB(entityBToCreate);
        entityBToCreate.setEntityA(entityAToCreate);
        
        em.persist(entityAToCreate);
        em.persist(entityAToCreate1);
        em.persist(entityCToCreate);
        em.persist(entityEToCreate1);
        em.persist(entityEToCreate2);
        em.persist(entityEToCreate3);
        
        System.out.println("AFTER SAVING: A = " + entityAToCreate.getId() + ", B = " + entityBToCreate.getId() + ", C = " + entityCToCreate.getId());
        System.out.println("");
        
        entityAToCreate.setEntityC(entityCToCreate);
        em.persist(entityAToCreate);
        
        // update ManyToMany relationship and save
        entityAToCreate.getEntityEList().add(entityEToCreate1);
        entityAToCreate.getEntityEList().add(entityEToCreate2);
        entityAToCreate.getEntityEList().add(entityEToCreate3);
        em.persist(entityAToCreate);
        
        entityAToCreate1.getEntityEList().add(entityEToCreate2);
        entityAToCreate1.getEntityEList().add(entityEToCreate3);
        em.persist(entityAToCreate1);
        
        EntityA existingA = em.find(EntityA.class, entityAToCreate.getId());
        printEntityA(existingA);
        
        EntityB existingB = em.find(EntityB.class, entityBToCreate.getId());
        System.out.println(existingB);
        System.out.println("");
        
        EntityC existingC = em.find(EntityC.class, entityCToCreate.getId());
        System.out.println("Entity C: ");
        System.out.println(existingC);
        System.out.println("");
        
        EntityE existingE1 = em.find(EntityE.class, entityEToCreate1.getId());
        System.out.println("Entity E#1: ");
        System.out.println(existingE1);
        System.out.println("");
        
        EntityE existingE2 = em.find(EntityE.class, entityEToCreate2.getId());
        System.out.println("Entity E#2: ");
        System.out.println(existingE2);
        System.out.println("");
        
        EntityE existingE3 = em.find(EntityE.class, entityEToCreate3.getId());
        System.out.println("Entity E#3: ");
        System.out.println(existingE3);
        System.out.println("");
        
        /*
        em.remove(existingA);
        em.remove(existingC);
        
        System.out.println("After deleting");
        System.out.println("");
        
        existingA = em.find(EntityA.class, entityAToCreate.getId());
        printEntityA(existingA);
        System.out.println("");
        
        existingB = em.find(EntityB.class, entityBToCreate.getId());
        System.out.println(existingB);
        System.out.println("");
        
        existingC = em.find(EntityC.class, entityCToCreate.getId());
        System.out.println(existingC);
        System.out.println("");
        */
        
        em.getTransaction().commit();
    }
    
    @Test
    public void oneToManyBDMapCase21() throws Exception {
        OneToManyBDMapCase21EntityA entityA = new OneToManyBDMapCase21EntityA("entity A");
        
        OneToManyBDMapCase21EntityB entityB1 = new OneToManyBDMapCase21EntityB(entityA, "key1", "entity B1");
        OneToManyBDMapCase21EntityB entityB2 = new OneToManyBDMapCase21EntityB(entityA, "key2", "entity B2");
        OneToManyBDMapCase21EntityB entityB3 = new OneToManyBDMapCase21EntityB(entityA, "key3", "entity B3");
        
        entityA.getEntityBMap().put(entityB1.getKeyField(), entityB1);
        entityA.getEntityBMap().put(entityB2.getKeyField(), entityB2);
        entityA.getEntityBMap().put(entityB3.getKeyField(), entityB3);
        
        em.getTransaction().begin();
        em.persist(entityA);
        em.getTransaction().commit();
        
        printInstnace(entityA);
    }
    
    @Test
    public void read() throws Exception {
        em.getTransaction().begin();
        OneToManyBDMapCase21EntityA entityA = em.find(OneToManyBDMapCase21EntityA.class, 1L);
        em.getTransaction().commit();
        
        printInstnace(entityA);
    }
    
    @Test
    public void delete() throws Exception {
        em.getTransaction().begin();
        OneToManyBDMapCase21EntityA entityA = em.find(OneToManyBDMapCase21EntityA.class, 1L);
        em.remove(entityA);
        em.getTransaction().commit();
    }

}
