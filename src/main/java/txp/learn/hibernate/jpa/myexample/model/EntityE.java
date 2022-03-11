package txp.learn.hibernate.jpa.myexample.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class EntityE extends AbstractEntity {

    @ManyToMany(mappedBy="entityEList")
    private List<EntityA> entityAList;

    public List<EntityA> getEntityAList() {
        return entityAList;
    }

    public void setEntityAList(List<EntityA> entityAList) {
        this.entityAList = entityAList;
    }
    
}