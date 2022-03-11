package txp.learn.hibernate.jpa.myexample.model;

import javax.persistence.Embeddable;

@Embeddable
public class EmbeddableEntityA {

    private String stringField;
    private Integer intField;
    
    public EmbeddableEntityA() {
    }
    
    public EmbeddableEntityA(String stringField, Integer intField) {
        this.stringField = stringField;
        this.intField = intField;
    }
    
    public String getStringField() {
        return stringField;
    }
    public void setStringField(String stringField) {
        this.stringField = stringField;
    }
    public Integer getIntField() {
        return intField;
    }
    public void setIntField(Integer intField) {
        this.intField = intField;
    }
    
    @Override
    public int hashCode() {
        return intField;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else {
            if (obj == null) {
                return false;
            } else if (!(obj instanceof EmbeddableEntityA)) {
                return false;
            } else {
                return (this.hashCode() == obj.hashCode());
            }
        }
    }
    
    @Override
    public String toString() {
        return "(" + stringField + ", " + intField + ")";
    }
    
}
