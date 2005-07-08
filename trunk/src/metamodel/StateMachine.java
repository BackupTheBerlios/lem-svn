/* 
 * StateMachine.java
 *
 * Copyright (C) 2005 Steven Michael Ring
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

/*
 * StateMachine.java
 *
 * Created on September 24, 2004, 4:34 PM
 */

package metamodel;

import java.util.*;

/**
 * Represents a instance based state machine for a class.
 *
 * @author  smr
 */
public class StateMachine implements DescribedEntity {
    
    /** the class whose behaviour this StateMachine describes (R501) */
    private Class domainClass = null;
    
    /** a description of the state machine */
    private String description = "";
    
    /** 
     * The states which are defined for this StateMachine 
     * This supports metamodel R504
     *
     */
    private ArrayList states = new ArrayList();
    
    /** allow access to states by name */
    private HashMap stateNames = new HashMap();
    
    /**
     * the transitions which are defined for this StateMachine.
     * This supports metamodel R514 but also includes StateTransition types
     */
    private ArrayList transitions = new ArrayList();
    
    /**
     * The events to which this state machine responds.
     * This includes non-public (self directed) events delared in the LEM "behaviour" block.
     * This attribute supports metamodel relationship R513.
     */
    private HashMap events = new HashMap();
    
    /** 
     * Creates a new instance of StateMachine
     *
     * @param aClass whose behaviour this StateMachine describes (R501)
     */
    public StateMachine( Class aClass ) {
        
        domainClass = aClass;
    }
    /** 
     * Creates a new instance of StateMachine
     */
    public StateMachine() {
    }
    
    /** Getter for property description.
     * @return Value of property description.
     **/
    public java.lang.String getDescription() {
        return description;
    }
    
    /** Setter for property description.
     * @param description New value of property description.
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }

    /**
     * Add a new state to the StateMachine 
     *
     * @param aState to be added to this state machine
     */
    public void add( State aState ) {
        states.add( aState );
        stateNames.put( aState.getName(), aState);
        aState.setStateMachine( this );
    }
    
    /**
     * Add a new event to the StateMachine 
     *
     * @param anEvent to be added to this state machine
     */
    public void add( Event anEvent ) {
        events.put( anEvent.getName(), anEvent );
        anEvent.setStateMachine( this );
    }
    
    /**
     * Find and return an a event given the event name. All known events (including those
     * declared is superclasses are searched)
     * 
     * @param eventName the name of the event to return
     * @return the named event or null if the event is not know to the class
     * @todo search the superclasses
     */
    public Event getEvent( String eventName ) {
        
        return (Event) events.get( eventName );
        
    }
    
    /**
     * Add a new transition to the StateMachine 
     *
     * @param aTransition to be added to this state machine
     */
    public void add( Transition aTransition ) {
        transitions.add( aTransition );
        aTransition.setStateMachine( this );
    }
    
    /**
     * Return an array of the states in this state machine
     * 
     * @return an array of the states in this state machine
     */
    public State [] getStateArray() {
        
        State [] result = new State[ states.size() ];
        
        for ( int i=0; i < states.size(); i++ )
            result[ i ] = (State) states.get( i );
            
        return result;
    }
    
    /**
     * Return a state given its name
     *
     * @param name of the state
     * @return the named state or null if it does not exist
     */
    public State getState( String name ) {
        return (State) stateNames.get( name );
    }
    
    /**
     * Return an array of the events define in this state machine. 
     *
     * This method will return all events, including privately scoped
     * self-directed events.
     * 
     * @return an array of the events in this state machine
     */
    public Event [] getEvents() {
        
        Event [] result = new Event[ events.size() ];
        
        int i = 0;
        for ( Iterator it = events.values().iterator(); it.hasNext()  ; i++ )
            result[ i ] = (Event) it.next();
            
        return result;
    }
    
    
    /**
     * Return an array of the deletion states in this state machine
     * 
     * @return an array of the deletion states in this state machine
     */
    public State [] getDeletionStates() {
        
        DeletionState [] result = new DeletionState[ getDeletionStateCount() ];
        
        // now load the array 
        
        int j = 0;
        for ( int i=0; i < states.size(); i++ ) {
            if ( states.get(i) instanceof DeletionState ) {
                result[ i ] = (DeletionState) states.get( i );
                j++;
            }
        }
            
        return result;
    }
    
    /**
     * Return a count of the number of deletion states (across [R525])
     *
     * @return a count of the number of deletion states
     */
    public int getDeletionStateCount() {
        
        int j = 0;
        for ( int i=0; i < states.size(); i++ ) {
            if ( states.get(i) instanceof DeletionState ) {
                j++;
            }
        }
        return j;
    }
    
    /**
     * Return a count of the number of non deletion states
     *
     * @return a count of the number of non deletion states
     */
    public int getNonDeletionStateCount() {
        
        int j = 0;
        for ( int i=0; i < states.size(); i++ ) {
            if ( states.get(i) instanceof NonDeletionState ) {
                j++;
            }
        }
        return j;
    }
    
    /**
     * Return the list of states in this statemachine.
     *
     * @return the list of states in this statemachine
     */
    public ArrayList getStateList() {
        return states;
    }
    
    /**
     * Return the list of tansitions in this statemachine.
     *
     * @return the list of transitions in this statemachine
     */
    public ArrayList getTransitionList() {
        return transitions;
    }
    
    /**
     * Return a count of the number of initialising transitions (across [R514])
     *
     * @return a count of the number of initialising transitions
     */
    public int getInitialisingTransitionCount() {
        
        int j = 0;
        for ( int i=0; i < transitions.size(); i++ ) {
            if ( transitions.get(i) instanceof InitialisingTransition ) {
                j++;
            }
        }
        return j;
    }
    
    /**
     * Return a count of the number of state transitions (non initilising)
     *
     * @return a count of the number of state transitions (non initilising)
     */
    public int getStateTransitionCount() {
        
        int j = 0;
        for ( int i=0; i < transitions.size(); i++ ) {
            if ( transitions.get(i) instanceof StateTransition ) {
                j++;
            }
        }
        return j;
    }
    
    /**
     * Return a count of the number of transitions (all types)
     *
     * @return a count of the number of transitions (all types)
     */
    public int getTransitionCount() {
        
        return transitions.size();
    }
    
    /**
     * Return an array of the transitions in this state machine
     * 
     * @return an array of the transitions in this state machine
     */
    public Transition [] getTransitions() {
        
        Transition [] result = new Transition[ transitions.size() ];
        
        for ( int i=0; i < transitions.size(); i++ )
            result[ i ] = (Transition) transitions.get( i );
            
        return result;
    }
    
    /**
     * Return the relative position of a given state in the state list. The
     * position index starts at 0.
     * 
     * @param aState whose relative position is to be returned
     * @return the relative position of the supplied state. Return -1 if
     * the supplied state is not defined in this state machine
     */
    public int getStateIndex( State aState ) {
        
        for ( int i = 0; i < states.size() ; i++ ) {
            if ( aState == states.get(i) )
                return i;
        }
        
        // not found 
        
        return -1;
    }
    
    /**
     * Return the class for for which this statemachine defines a lifecycle.
     *
     * @return the class for for which this statemachine defines a lifecycle and behaviour.
     */
    public Class getDomainClass(){
        return domainClass;
        
    }
    
    /**
     * Relate this StateMachine to the supplied domain class. This state machine will now
     * define the lifecycle and behaviour of the specified domain class.
     *
     * @param aClass for which this StateMachine will specify behaviour.
     */
    public void setDomainClass( Class aClass ) {
        domainClass = aClass;
    }
    
    public void dumpDot() {
        Iterator i = transitions.iterator();
        
        System.out.println( "digraph G {" );
        while( i.hasNext() ) {
            Transition t = (Transition)i.next();
            
            if( t.getFromState() == null ) {
                System.out.println( "creation -> " + t.getToState().getName() + ";" );
            } else {
                System.out.println( t.getFromState().getName() + " -> " + t.getToState().getName() + ";" );
            }
        }
        
        System.out.println( "}" );
    }
}
