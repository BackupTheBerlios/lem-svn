/*
 * DescribedEntitiy.java
 *
 * Created on February 1, 2004, 10:05 AM
 */

package metamodel;
/**
 * An entity that has an associated description
 *
 * @author  smr
 */
public interface DescribedEntity {
  
    /**
     * Returns the textual description associated with this DescribedEntity
     * @return the textual description associated with this entity
     */
    public String getDescription();
    /**
     * Set the textual description of this DescribedEntity.
     * @param theDescription the description to be associated with this entity
     */
    public void setDescription( String theDescription );
}
