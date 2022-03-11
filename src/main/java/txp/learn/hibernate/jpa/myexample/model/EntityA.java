package txp.learn.hibernate.jpa.myexample.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class EntityA {

    @Id
    @GenericGenerator(name="increment", strategy = "increment")
    @GeneratedValue(generator="increment")
    private Long id;
    
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;
    
    @Embedded
    private EmbeddableEntityA embeddableEntity;
    
    // OneToOne bidirectional and cascade all
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="entityB_id", unique=true, nullable=true)
    private EntityB entityB;
    
    // OneToOne bidirectional and no cascade
    @OneToOne
    @JoinColumn(name="entityC_id", unique=true, nullable=true)
    private EntityC entityC;
    
    // ----------------- Collection Fields
    
    @ElementCollection(fetch = FetchType.LAZY)
    @Column(name="stringValue")
    private List<String> stringList;
    
    @ElementCollection(fetch = FetchType.LAZY)
    private List<EmbeddableEntityA> embeddableEntityList;
    
    @OneToMany(mappedBy = "entityA", cascade=CascadeType.ALL)
    private List<EntityD> entityDList;
    
    @ManyToMany
    @JoinTable(
        name="EntityA_EntityE_JT",
        joinColumns=@JoinColumn(name="EntityA_id", referencedColumnName="id"),
        inverseJoinColumns=@JoinColumn(name="EntityE_id", referencedColumnName="id")
    )
    private List<EntityE> entityEList = new ArrayList<>();
    
    // ----------------- Map Fields
    
    @ElementCollection(fetch = FetchType.LAZY)
    @MapKeyColumn(name="keyField")
    @Column(name="stringValue")
    private Map<String, String> stringMap;
    
    @ElementCollection(fetch = FetchType.LAZY)
    @AttributeOverrides({
        @AttributeOverride(name="key.stringField", column=@Column(name="key_stringField")),
        @AttributeOverride(name="key.intField", column=@Column(name="key_intField")),
        @AttributeOverride(name="value.stringField", column=@Column(name="value_stringField")),
        @AttributeOverride(name="value.intField", column=@Column(name="value_intField"))
    })
    private Map<EmbeddableEntityA, EmbeddableEntityA> embeddableEntityMap;

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

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }
    
    public EmbeddableEntityA getEmbeddableEntity() {
        return embeddableEntity;
    }
    
    public void setEmbeddableEntity(EmbeddableEntityA embeddableEntity) {
        this.embeddableEntity = embeddableEntity;
    }
    
    public EntityB getEntityB() {
        return entityB;
    }

    public void setEntityB(EntityB entityB) {
        this.entityB = entityB;
    }
    
    public EntityC getEntityC() {
        return entityC;
    }

    public void setEntityC(EntityC entityC) {
        this.entityC = entityC;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    public List<EmbeddableEntityA> getEmbeddableEntityList() {
        return embeddableEntityList;
    }

    public void setEmbeddableEntityList(List<EmbeddableEntityA> embeddableEntityList) {
        this.embeddableEntityList = embeddableEntityList;
    }
    
    public List<EntityD> getEntityDList() {
        return entityDList;
    }

    public void setEntityDList(List<EntityD> entityDList) {
        this.entityDList = entityDList;
    }
    
    public List<EntityE> getEntityEList() {
        return entityEList;
    }

    public void setEntityEList(List<EntityE> entityEList) {
        this.entityEList = entityEList;
    }

    public Map<String, String> getStringMap() {
        return stringMap;
    }

    public void setStringMap(Map<String, String> stringMap) {
        this.stringMap = stringMap;
    }

    public Map<EmbeddableEntityA, EmbeddableEntityA> getEmbeddableEntityMap() {
        return embeddableEntityMap;
    }

    public void setEmbeddableEntityMap(Map<EmbeddableEntityA, EmbeddableEntityA> embeddableEntityMap) {
        this.embeddableEntityMap = embeddableEntityMap;
    }
    
}
