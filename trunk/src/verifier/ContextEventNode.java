/*
 * ContextEventNode.java
 *
 * Created on September 12, 2005, 4:24 PM
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

import java.math.BigDecimal;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import metamodel.Event ;
import runtime.DelayedSignal;
import runtime.LemRuntimeException;
import runtime.Object ;
import runtime.Signal;


/**
 * Event node appearing inside a ModelTreePanel based on a particular event, has methods for returning
 * the name, description and ContexMenu for the node. 
 * 
 * @author Shokouhmand Torabi
 */
 
public class ContextEventNode extends AbstractDescriptionNode {
	
	Event thisEvent ;
	LoggerFrame frame ;
	private Object parentObject = null ;
	
	/** 
	* Creates a new instance of SignalNode 
	* @param object the parent of the given event
	* @param e the event from which a node is created
	* @param frame the logger frame the node will be displayed in
	*/
	public ContextEventNode(Object object, Event e, LoggerFrame frame) {
		this.frame = frame ;
		this.thisEvent = e ;
		this.parentObject = object ;
	}
	/**
	 * Returns name property of the Object
	 * @return the Object name.
	 */
	public String toString() {
		return thisEvent.getName() + " : ";
	}
	/**
	 * Returns the description property of the Object.
	 * @return the Object description.
	 */
	public String getDescription(){
		return thisEvent.getDescription() ;
	}
	/**
	 * Returns the ContextMenu based on the Scenario.
	 * @return the Scenario ContextMenu.
	 */
	public JPopupMenu getContextMenu() {
		JPopupMenu contextMenu = new JPopupMenu();
		JMenuItem generateEvent = new JMenuItem();
		
		// ##### Generate normal signal ########
		generateEvent.setText("Generate.");
		generateEvent.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					Signal signal = new Signal(thisEvent) ;
					int signalId = signal.getSignalId().intValue() ;
					parentObject.getContext().getDebugObject().addEntity();
					parentObject.addSignal(signal) ;
					JOptionPane.showMessageDialog( frame , "Signal " + signalId + " Was generated to the object", "Success!",
							JOptionPane.INFORMATION_MESSAGE );
					
				} catch(LemRuntimeException e) {
					//todo : open a JOptionPane saying this is invalid for osme reason.
					JOptionPane.showMessageDialog( frame , "LemRuntimeException happened: " + e.getMessage() , "Failed!",
							JOptionPane.INFORMATION_MESSAGE );
					
				}
			}
		});		
		contextMenu.add(generateEvent);
		// ##### Generate self signal ########
		JMenuItem generateSelfEvent = new JMenuItem();
		generateSelfEvent.setText("Generate to Self.");
		generateSelfEvent.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					Signal signal = new Signal(thisEvent) ;
					int signalId = signal.getSignalId().intValue() ;
					parentObject.getContext().getDebugObject().addEntity();
					parentObject.addSignal(signal) ;
					JOptionPane.showMessageDialog( frame , "Self Signal " + signalId + " Was generated to the object", "Success!",
							JOptionPane.INFORMATION_MESSAGE );
					
				} catch ( LemRuntimeException e) {
					// todo : open a JOptionPane saying this is invalid
					JOptionPane.showMessageDialog( frame , "LemRuntimeException happened: " + e.getMessage() , "Failed!",
							JOptionPane.INFORMATION_MESSAGE );
					
				}
			}
		});
		contextMenu.add(generateSelfEvent);
		// ##### Generate delayed signal ########
		JMenuItem generateDelayedEvent = new JMenuItem();
		generateDelayedEvent.setText("Generate Delayed Signal.");
		generateDelayedEvent.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				
				String input = JOptionPane.showInputDialog( frame , "Delay in milisecconds", "Amount of Delay",
						JOptionPane.QUESTION_MESSAGE );
				if ( input != null ) {
					try {
						DelayedSignal delayedSignal = new DelayedSignal(thisEvent, parentObject , new BigDecimal(Integer.parseInt(input)));
						parentObject.getContext().getDebugObject().addEntity();
						parentObject.addDelayedSignal(delayedSignal) ;
						int signalId = delayedSignal.getSignalId().intValue() ;
						JOptionPane.showMessageDialog( frame , "Delayed Self Signal " + signalId + " Was generated to the object", "Success!",
								JOptionPane.INFORMATION_MESSAGE );
					} catch( Exception e) {
						JOptionPane.showMessageDialog( frame , "Exception Happened : " + e.getMessage() , "Success!",
								JOptionPane.ERROR_MESSAGE );
					}
				}
			}
		});
		contextMenu.add(generateDelayedEvent);
		// ##### Generate delayed self signal ########
		JMenuItem generateDelayedSelfEvent = new JMenuItem();
		generateDelayedSelfEvent.setText("Generate Delayed Self Signal.");
		generateDelayedSelfEvent.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				
				String input = JOptionPane.showInputDialog( frame , "Delay in milisecconds", "Amount of Delay",
						JOptionPane.QUESTION_MESSAGE );
				if ( input != null ) {
					try {
						DelayedSignal delayedSelfSignal = new DelayedSignal(thisEvent, parentObject , new BigDecimal(Integer.parseInt(input)));
						parentObject.getContext().getDebugObject().addEntity();
						parentObject.addDelayedSignalSelf(delayedSelfSignal) ;
						int signalId = delayedSelfSignal.getSignalId().intValue() ;
						JOptionPane.showMessageDialog( frame , "Delayed Self Signal " + signalId + " Was generated to the object", "Success!",
								JOptionPane.INFORMATION_MESSAGE );
					} catch( Exception e) {
						JOptionPane.showMessageDialog( frame , "Exception Happened : " + e.getMessage() , "Success!",
								JOptionPane.ERROR_MESSAGE );
					}
				}
			}
		});
		contextMenu.add(generateDelayedSelfEvent);
		
		
		return contextMenu;
	}
}
