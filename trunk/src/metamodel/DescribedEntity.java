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
  
    public String getDescription();
    public void setDescription( String theDescription );
}
