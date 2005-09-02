/*
 * SignalGenerator.java
 *
 * Created on 20 August 2005, 13:38
 *
 * Copyright (C) 2005 Shuku Torabi
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

package runtime;

import java.util.Calendar ;
import java.util.GregorianCalendar ;

/**
 *
 * @author shuku
 */
public class SignalGenerator extends java.lang.Thread {
    /** The senderObject sending the signal **/
    private runtime.Object senderObject = null ;
    
    /** Signal which shall be sent to the target **/
    private runtime.DelayedSignal signal = null ;
    
    /** time which the signal has been created **/
    private Calendar created = null ;
    
    /** Creates a new instance of SignalGenerator */
    public SignalGenerator(DelayedSignal signal, runtime.Object sender) {
        this.signal = signal  ;        
        this.senderObject = sender;
        this.created = Calendar.getInstance() ;
//        System.out.println( created.getTime().getTime() ) ;
        start() ;
    }
    
    public void run() {
        long sleepMs;
        do {
            Calendar rightNow = Calendar.getInstance() ;
            sleepMs = signal.getDelay().longValue() -
                    (rightNow.getTime().getTime() - created.getTime().getTime());
            if (sleepMs > 0) {
                try {
                    sleep(sleepMs);
                } catch (InterruptedException e) {
                            /*
                             * InterruptedException is OK. We'll just go
                             * around again and recalculate time
                             */
                }
            }
        } while (sleepMs > 0);
        
        runtime.Object target = signal.getTarget();
        Integer signalId = signal.getSignalId() ; 
        Integer targetObjectId = target.getObjectId() ; 
        System.out.println("Delayed Signal " + signalId + " Was generated to object " + targetObjectId ) ; 
        if (target == senderObject) {
            target.addSignalSelf(signal);
        } else {
            target.addSignal(signal);
        }
        //System.out.println("--Delayed Signal Generated !--") ;
    }
}
