/*
 * RelatedToOperation.java
 *
 * Created on 2 August 2005, 19:49
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package org.jdns.xtuml.metamodel;

/**
 *
 * @author shuku
 */
public class RelatedToOperation {
    
    /** Relationship across which the object is related to a class */
    private Relationship relationship ; 
    
    /**Class to which object is related */
    private org.jdns.xtuml.metamodel.Class relatedClass ; 
    
    
    /** Creates a new instance of RelatedToOperation */
    public RelatedToOperation(org.jdns.xtuml.metamodel.Class relatedClass, Relationship r) { 
        this.setRelationship(r) ; 
        this.setRelatedClass(relatedClass) ; 
    }

    public Relationship getRelationship() {
        return relationship;
    }

    public void setRelationship(Relationship relationship) {
        this.relationship = relationship;
    }

    public org.jdns.xtuml.metamodel.Class getRelatedClass() {
        return relatedClass;
    }

    public void setRelatedClass(org.jdns.xtuml.metamodel.Class relatedClass) {
        this.relatedClass = relatedClass;
    }
    
    
    
}
