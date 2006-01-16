/*
 * Copyright (C) 2005 James Ring
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 */

package org.jdns.xtuml.metamodel;

/**
 * A scenario represents an xtUML test case or scenario. A scenario is used 
 * to initialise the executable model and then stimulate the model with events.
 * The eLEMinator verifier is then used to examine the behaviour of the model
 * in a given scenario.
 *
 * @author sjr
 */
public class Scenario implements DescribedEntity {
  
    /** A description of this scenario */
    private String description;
    
    /** The ActionBlock used to create this Scenario */
    private ActionBlock actionBlock;
    
    /** The name of this scenario */
    private String name;
    
    /** Creates a new instance of Scenario */
    public Scenario() {
    }

    /**
     * Returns the description of this Scenario.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of this Scenario.
     *
     * @param description the new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the ActionBlock used to create this scenario.
     *
     * @return the ActionBlock
     */
    public ActionBlock getActionBlock() {
        return actionBlock;
    }

    /** 
     * Sets the ActionBlock used to create this scenario.
     *
     * @param actionBlock the new ActionBlock
     */
    public void setActionBlock(ActionBlock actionBlock) {
        this.actionBlock = actionBlock;
    }

    /** 
     * Return the name of this Scenario.
     *
     * @return the name 
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of this Scenario.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }
}
