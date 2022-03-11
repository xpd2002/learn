package txp.learn.hibernate.jpa.myexample.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class EntityC extends AbstractEntity {
    
    @OneToOne(mappedBy = "entityC")
    private EntityA entityA;

    public EntityA getEntityA() {
        return entityA;
    }

    public void setEntityA(EntityA entityA) {
        this.entityA = entityA;
    }

}