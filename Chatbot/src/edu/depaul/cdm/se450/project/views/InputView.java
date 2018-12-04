package edu.depaul.cdm.se450.project.views;

import java.io.*;

import edu.depaul.cdm.se450.project.util.Observer;
import edu.depaul.cdm.se450.project.util.EventCode;

/**
 *  Input view for the application. Provides very simple functionality
 *	for accepting input string from user
 *
 */

public class InputView extends View implements Observer {
	
    private StringBuilder inputString;      //  User input string

    /**
     *  Return the last user input to this InputView.
     *
     *  @return user input
     */

    public String getUserInput() {
        return inputString.toString();
    }

    /**
     *  Receive notification of an event in an Observable object.
     */

    public void handleEvent( EventCode eventCode ) {

        /*
         *  Handle the single event type for an InputView:
         *	<p>
         *	(1) Print prompt to console.
         *	<p>
         *	(2) Read a line from the console.
         *	<p>
         *	(3) Notify Obervers (in this case, the Controller)
         *		that the InputView has input.
         */

        //  Read input from user and notify observers.

        if ( eventCode == EventCode.READ_USER_INPUT ) {
            BufferedReader console = new BufferedReader( new InputStreamReader( System.in ) );
            String inString = null;
            try {
                inString = console.readLine();
            }
            catch ( IOException e ) {
                inString = "<" + e + ">";
            }
            inputString = new StringBuilder( inString );
            notifyObservers( EventCode.SET_MODEL_VALUE );
        }
        
        else if( eventCode == EventCode.FILE_QUERY) {
        	BufferedReader console = new BufferedReader( new InputStreamReader( System.in ) );
            String inString = null;
            try {
                do {
                	inString = console.readLine();
                	if(inString.contentEquals("y") || inString.contentEquals("n")) break;
                }while(true);
            }
            catch ( IOException e ) {
                inString = "<" + e + ">";
            }
            inputString = new StringBuilder( inString );
            notifyObservers( EventCode.YN_EVAL);
        }

        //	Ignore any other events.

        else { /*	Do nothing.	*/ }
    }
}