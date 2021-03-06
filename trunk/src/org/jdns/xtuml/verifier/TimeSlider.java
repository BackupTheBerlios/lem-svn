/*
 * TimeSlider.java
 *
 * Created on September 25, 2005, 7:10 PM
 *
 * Copyright (C) 2005 Shokouhmand Torabi
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

import org.jdns.xtuml.runtime.Debug;
import org.jdns.xtuml.runtime.DomainContext;

/**
 * Creates the time slider and time display for an executing scenario.
 * @author  Shokouhmand Torabi
 */
public class TimeSlider extends javax.swing.JPanel {
	
	private DomainContext context = null ;
	
	/** Creates new form BeanForm */
	public TimeSlider() {
		initComponents();
		timeFactorLabel.setText("Factor:"+ getTimeFactor() + "X" ) ;
		TimeChanger tc = new TimeChanger() ;
		
		//if ( context != null)
		//	 context.getTimeObject().setTimeFactor( getTimeFactor() ) ; 
	}
	
        /**
         * Initalise the time slide and associate a domain context
         * @param d The associated domain context
         */
	public void initialise(DomainContext d) {
		this.context = d ;				
		refreshTime() ; 
		//context.getTimeObject().setTimeFactor( 1 ) ;
	}
	
        /**
         * Refresh the time as displayed in the LEM time box
         */
	public void refreshTime() {
		if ( context != null && context.getDebugObject().isRunning() ) {			
			timer.setText( " " + context.getTimeObject().getTimeMs() + " ") ;
		}
	}
	
	
	
	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        timeFactorLabel = new javax.swing.JLabel();
        timeSlider = new javax.swing.JSlider();
        sep1 = new javax.swing.JSeparator();
        lemTimeLabel = new javax.swing.JLabel();
        timer = new javax.swing.JTextField();

        timeFactorLabel.setText("Factor :      ");
        add(timeFactorLabel);

        timeSlider.setMaximum(199);
        timeSlider.setMinimum(1);
        timeSlider.setValue(100);
        timeSlider.setPreferredSize(new java.awt.Dimension(150, 16));
        timeSlider.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                timeSliderKeyPressed(evt);
            }
        });
        timeSlider.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                timeSliderMouseClicked(evt);
            }
        });

        add(timeSlider);

        sep1.setForeground(new java.awt.Color(51, 51, 51));
        sep1.setPreferredSize(new java.awt.Dimension(10, 0));
        add(sep1);

        lemTimeLabel.setText("lem time:");
        add(lemTimeLabel);

        timer.setEditable(false);
        timer.setForeground(new java.awt.Color(0, 0, 0));
        timer.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        timer.setPreferredSize(new java.awt.Dimension(80, 19));
        timer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timerActionPerformed(evt);
            }
        });

        add(timer);

    }
    // </editor-fold>//GEN-END:initComponents

    /**
     * Sets the time factor according to the display on the time slider
     * @param evt The Time slider event
     */
	private void timeSliderKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_timeSliderKeyPressed
// TODO add your handling code here:
		context.getTimeObject().setTimeFactor( getTimeFactor() ) ;
		timeFactorLabel.setText("Factor: " + getTimeFactor() + "X" ) ;
		refreshTime() ;
	}//GEN-LAST:event_timeSliderKeyPressed

        /**
         * Set the time factor according to the display on the time slider
         * @param evt The associated Action event (moving the slider)
         */
	private void timerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timerActionPerformed
// TODO add your handling code here:
		context.getTimeObject().setTimeFactor( getTimeFactor() ) ;
		timeFactorLabel.setText("Factor: " + getTimeFactor() + "X" ) ;
		refreshTime() ;
	}//GEN-LAST:event_timerActionPerformed
	
        /**
         * Change the factor according to the time slider
         * @return The time factor displayed on the time slider
         */
	public double getTimeFactor() {
		double value = (double) timeSlider.getValue() ;
		double timeFactor = 0.0 ;
		if (value == 100)
			timeFactor = 1.0 ; 
		else if ( value < 100)
			timeFactor = 1 -Math.abs(  (value-100) / 100 );  
		else 
			timeFactor = (double)(value - 100) ;
		
		return Math.round( (100*timeFactor) ) / 100.0   ; 
	}
	/**
         * Set the time factor according to the time slider
         * @param evt The mouse click event 
         */
        
	private void timeSliderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_timeSliderMouseClicked
// TODO add your handling code here:
		context.getTimeObject().setTimeFactor( getTimeFactor() ) ;
		timeFactorLabel.setText("Factor: " + getTimeFactor() + "X" ) ;
		refreshTime() ;
	}//GEN-LAST:event_timeSliderMouseClicked
	
	
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lemTimeLabel;
    private javax.swing.JSeparator sep1;
    private javax.swing.JLabel timeFactorLabel;
    private javax.swing.JSlider timeSlider;
    private javax.swing.JTextField timer;
    // End of variables declaration//GEN-END:variables
	
    /**
     * Refreshes the displayed time according to LEM time. 
     */
	class TimeChanger extends Thread {
		public TimeChanger() {
			start() ;
		}
		
		public void run() {
			while ( true ) {
					refreshTime() ;
				try {
					sleep(100) ;
				} catch(Exception e) {}
			}
		}
	}
	
}
