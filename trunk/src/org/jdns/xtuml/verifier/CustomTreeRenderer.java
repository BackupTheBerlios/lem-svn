/*
 * CustomTreeRenderer.java
 *
 * Created on 2 May 2005, 04:13
 *
 * Copyright (C) 2005 David Gavin
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

package org.jdns.xtuml.verifier;

import java.awt.Component;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 * Component allowing custom icons to be used in the ModelTreePanel.
 * @author David Gavin
 */
public class CustomTreeRenderer extends DefaultTreeCellRenderer{
    /**Image icon for state objects in model tree*/
    private ImageIcon stateIcon = new ImageIcon();
    /**Image icon for transition objects in model tree*/
    private ImageIcon transitionIcon = new ImageIcon();
    /**Image icon for relationship objects in model tree*/
    private ImageIcon relationshipIcon = new ImageIcon();
    /**Image icon for attribute objects in model tree*/
    private ImageIcon attributeIcon = new ImageIcon();
    /**Image icon for statemachine objects in model tree*/
    private ImageIcon stateMachineIcon = new ImageIcon();
    /**Image icon for open folders in model tree*/
    private ImageIcon openedIcon = new ImageIcon();
    /**Image icon for closed folders in model tree*/
    private ImageIcon closeIcon = new ImageIcon();
    /**Image icon for event objects in model tree*/
    private ImageIcon eventIcon = new ImageIcon();
    /**Image icon for class objects in model tree*/
    private ImageIcon classIcon = new ImageIcon();
    /**Image icon for scenario objects in model tree*/
    private ImageIcon scenarioIcon = new ImageIcon();
    
    /** 
     * Creates a new instance of CustomTreeRenderer. Contains locations for all
     * images to be used for icons in the ModelTreePanel.
     */
    public CustomTreeRenderer() {
        URL imageURL = getClass().getClassLoader().getResource("verifier/state.jpg");
        stateIcon.setImage(Toolkit.getDefaultToolkit().getImage(imageURL));
        imageURL = getClass().getClassLoader().getResource("verifier/transition.jpg");
        transitionIcon.setImage(Toolkit.getDefaultToolkit().getImage(imageURL));
        imageURL = getClass().getClassLoader().getResource("verifier/relationship.jpg");
        relationshipIcon.setImage(Toolkit.getDefaultToolkit().getImage(imageURL));
        imageURL = getClass().getClassLoader().getResource("verifier/attribute.jpg");
        attributeIcon.setImage(Toolkit.getDefaultToolkit().getImage(imageURL));
        imageURL = getClass().getClassLoader().getResource("verifier/statemachine.jpg");
        stateMachineIcon.setImage(Toolkit.getDefaultToolkit().getImage(imageURL));
        imageURL = getClass().getClassLoader().getResource("verifier/open.jpg");
        openedIcon.setImage(Toolkit.getDefaultToolkit().getImage(imageURL));
        imageURL = getClass().getClassLoader().getResource("verifier/closed.jpg");
        closeIcon.setImage(Toolkit.getDefaultToolkit().getImage(imageURL));
        imageURL = getClass().getClassLoader().getResource("verifier/event.jpg");
        eventIcon.setImage(Toolkit.getDefaultToolkit().getImage(imageURL));
        imageURL = getClass().getClassLoader().getResource("verifier/class.jpg");
        classIcon.setImage(Toolkit.getDefaultToolkit().getImage(imageURL));
        imageURL = getClass().getClassLoader().getResource("verifier/scenario.jpg");
        scenarioIcon.setImage(Toolkit.getDefaultToolkit().getImage(imageURL));

	setClosedIcon( closeIcon );
	setOpenIcon( openedIcon );
    }
    /**
     * Method for setting the icon of the current JTree component. Recursively 
     * checks the type of the current tree component and assigns appropriate icon,
     * @param tree the tree containing the component to be inspected.
     * @param value the component to be inspected.
     * @param sel Not used
     * @param expanded Not used
     * @param leaf boolean indicator of whether the component has children or not.
     * @param row Not used
     * @param hasFocus Not used
     * @return the TreeCellRederer
     */
    public Component getTreeCellRendererComponent(
            JTree tree,
            Object value,
            boolean sel,
            boolean expanded,
            boolean leaf,
            int row,
            boolean hasFocus) {
        
        super.getTreeCellRendererComponent(
                tree, value, sel,
                expanded, leaf, row,
                hasFocus);
        setOpenIcon(openIcon);
        setClosedIcon(closedIcon);
        
        if (value instanceof ClassNode) {
            setIcon(classIcon);
        } else if (value instanceof RelationshipNode){
            setIcon(relationshipIcon);
        } else if (value instanceof AttributeNode){
            setIcon(attributeIcon);
        } else if (value instanceof StateMachineNode){
            setIcon(stateMachineIcon);
        } else if (value instanceof StateNode){
            setIcon(stateIcon);
        } else if (value instanceof TransitionNode){
            setIcon(transitionIcon);
        } else if (value instanceof EventNode){
            setIcon(eventIcon);
        }else if (value instanceof ScenarioNode ){
            setIcon(scenarioIcon);
        }
		/** below this we have instances of objects used in the ContextTreePanel **/
		else if (value instanceof ScenarioContextNode){
            setIcon(scenarioIcon);
        }else if (value instanceof ObjectNode) {
			setIcon(classIcon) ; 
		}
        
        return this;
    }   
}
