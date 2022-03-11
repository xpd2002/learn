package txp.learn.hibernate.jpa.myexample.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class EntityD extends AbstractEntity {
    
    @ManyToOne
    @JoinColumn(name = "entityA_id", unique=true, nullable=false)
    private EntityA entityA;
    
    public EntityD() {
    }
    
    public EntityD(String name, EntityA entityA) {
        this.setName(name);
        this.entityA = entityA;
    }

    public EntityA getEntityA() {
        return entityA;
    }

    public void setEntityA(EntityA entityA) {
        this.entityA = entityA;
    }

}