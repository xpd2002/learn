package txp.learn.hibernate.jpa.myexample.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class EntityB {

    @Id
    @GenericGenerator(name="increment", strategy = "increment")
    @GeneratedValue(generator="increment")
    private Long id;
    
    private String name;
    
    @OneToOne(mappedBy = "entityB")
    private EntityA entityA;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public EntityA getEntityA() {
        return entityA;
    }
    public void setEntityA(EntityA entityA) {
        this.entityA = entityA;
    }
    
    @Override
    public String toString() {
        return "EntityB: " + id + ", " + name + ", " + (entityA == null? "null" : entityA.getId());
    }
    
}