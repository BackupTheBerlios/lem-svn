/*
 * ObjectNode.java
 *
 * Created on September 9, 2005, 2:44 PM
 *
 * Copyright (C) 2005 Shokouhmand Torabi
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

package verifier;
import java.awt.Color;
import java.util.Iterator;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import metamodel.Event;
import runtime.Instance;
import runtime.Signal;

/**
 *
 * @author u3958283
 */
public class ObjectNode extends AbstractDescriptionNode {
	
	private LoggerFrame frame ;
	private runtime.Object thisObject ;
	
	/** Creates a new instance of ObjectNode */
	public ObjectNode(runtime.Object o, LoggerFrame frame) {
		this.frame = frame ;
		this.thisObject = o ;
		Iterator i = thisObject.getInstances().iterator() ;
		// add instances to the tree.
		DefaultMutableTreeNode instancesLevel = new DefaultMutableTreeNode( "Instances" ) ;
		DefaultMutableTreeNode eventsLevel = new DefaultMutableTreeNode("Accepted Signals") ;
		DefaultMutableTreeNode selfQueueLevel = new DefaultMutableTreeNode("Queue to self") ;
		DefaultMutableTreeNode normalQueueLevel = new DefaultMutableTreeNode("Regular Queue") ;
		DefaultMutableTreeNode delayedSelfQueueLevel = new DefaultMutableTreeNode("Delayed to self") ;
		DefaultMutableTreeNode delayedQueueLevel = new DefaultMutableTreeNode("Delayed Queue") ;
		
		add(instancesLevel ) ;
		while( i.hasNext() ) {
			Instance instance = (Instance) i.next() ;
			InstanceNode instanceNode = new InstanceNode( instance, frame ) ;
			instancesLevel.add( instanceNode ) ;
			metamodel.Class thisClass = instance.getInstanceClass() ;
			// add signals to the object tree.
			Iterator j = thisClass.getEvents().iterator();
			if ( j.hasNext() ) {
				add(eventsLevel ) ;
				while ( j.hasNext() ) {
					Event event = (Event)j.next() ;
					eventsLevel.add( new ContextEventNode(thisObject,event,frame) ) ;
				}
			}
		}
		Iterator selfQueue = thisObject.getSelfSignalQueue().iterator();
		if(selfQueue.hasNext()) {
			add(selfQueueLevel);
			while(selfQueue.hasNext()) {
				Signal signal = (Signal)selfQueue.next();
				ContextSignalNode ConSigNode= new ContextSignalNode(signal,frame);
				selfQueueLevel.add(ConSigNode);
			}
		}
		Iterator queue = thisObject.getSignalQueue().iterator();
		
		if(queue.hasNext()) {
			add(normalQueueLevel);
			while(queue.hasNext()) {
				Signal signal = (Signal)queue.next();
				ContextSignalNode ConSigNode= new ContextSignalNode(signal,frame);
				normalQueueLevel.add(ConSigNode);
			}
		}
		
		Iterator delayedSelf = thisObject.getDelayedSelfQueue().iterator();
		
		if(delayedSelf.hasNext()) {
			add(delayedSelfQueueLevel);
			while(delayedSelf.hasNext()) {
				Signal signal = (Signal)delayedSelf.next();
				ContextSignalNode ConSigNode= new ContextSignalNode(signal,frame);
				delayedSelfQueueLevel.add(ConSigNode);
			}
		}
		
		Iterator delayed = thisObject.getDelayedQueue().iterator();
		
		if(delayed.hasNext()) {
			add(delayedQueueLevel);
			while(delayed.hasNext()) {
				Signal signal = (Signal)delayed.next();
				ContextSignalNode ConSigNode= new ContextSignalNode(signal,frame);
				delayedQueueLevel.add(ConSigNode);
			}
		}
	}
	
	/**
	 * Returns name property of the Object
	 * @return the Object name.
	 */
	public String toString() {
		return "Object " + thisObject.getObjectId();
	}
	/**
	 * Returns the description property of the Object.
	 * @return the Object description.
	 */
	public String getDescription(){
		return "" ; //trim(thisObject.getDescription());
	}
	/**
	 * Returns the ContextMenu based on the Scenario.
	 * @return the Scenario ContextMenu.
	 */
	public JPopupMenu getContextMenu() {
		JPopupMenu ContextMenu = new JPopupMenu();
		JMenuItem descriptionItem = new JMenuItem();
		descriptionItem.setText("Descriptions");
		descriptionItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JTextPane descriptionPanel = frame.getDescriptionPane() ;
				StyledDocument doc = getStyledDocument() ;
				
			}
		});
		return ContextMenu;
	}
	
	public StyledDocument getDynamicDescription() {
		StyledDocument doc = new StyledDocument() ;
		Iterator i = thisObject.getInstances().iterator() ;
		if ( i.hasNext() ) {
			SimpleAttributeSet s = new SimpleAttributeSet();
			StyleConstants.setFontFamily(s, "Times New Roman");
			StyleConstants.setFontSize(s, 14);
			StyleConstants.setBold(s, true);
			StyleConstants.setForeground(s, Color.black);
			StyledElement element = new StyledElement( "List of Instances:"+"\n" , s) ;
			doc.addStyle( element ) ;
			while (i.hasNext()) {
				doc.addStyle( StyledElement.getTab() ) ; 
				Instance instance = (Instance)i.next() ;
				InstanceNode instanceNode = new InstanceNode( instance, null ) ;
				StyledDocument styledDoc1 = instanceNode.getStyledDocument();
				StyledDocument styledDoc2 = instanceNode.getDynamicDescription();
				
				doc.append(styledDoc1) ;
				doc.addStyle( StyledElement.getTab() ) ; 
				doc.append(styledDoc2) ;
			}
		}
		return doc;
	}
}
