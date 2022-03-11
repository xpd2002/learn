package txp.learn.hibernate.jpa.myexample.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="OneToMany_BD_Map_Case21_EntityB", schema="test")
public class OneToManyBDMapCase21EntityB extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "entityA_id", unique=true, nullable=false)
    private OneToManyBDMapCase21EntityA entityA;
    
    private String keyField;
    
    public OneToManyBDMapCase21EntityB() {
    }
    
    public OneToManyBDMapCase21EntityB(OneToManyBDMapCase21EntityA entityA, String keyField, String name) {
        this.entityA = entityA;
        this.keyField = keyField;
        setName(name);
    }

    public OneToManyBDMapCase21EntityA getEntityA() {
        return entityA;
    }

    public void setEntityA(OneToManyBDMapCase21EntityA entityA) {
        this.entityA = entityA;
    }

    public String getKeyField() {
        return keyField;
    }

    public void setKeyField(String keyField) {
        this.keyField = keyField;
    }
    
}
