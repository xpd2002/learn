package txp.learn.hibernate.jpa.myexample.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="OneToMany_BD_Map_Case21_EntityA", schema="test")
public class OneToManyBDMapCase21EntityA extends AbstractEntity {

    @OneToMany(mappedBy = "entityA", cascade = CascadeType.ALL)
    @MapKey(name = "keyField")
    private Map<String, OneToManyBDMapCase21EntityB> entityBMap = new HashMap<>();
    
    public OneToManyBDMapCase21EntityA() {
    }
    
    public OneToManyBDMapCase21EntityA(String name) {
        setName(name);
    }

    public Map<String, OneToManyBDMapCase21EntityB> getEntityBMap() {
        return entityBMap;
    }

    public void setEntityBMap(Map<String, OneToManyBDMapCase21EntityB> entityBMap) {
        this.entityBMap = entityBMap;
    }
    
}
