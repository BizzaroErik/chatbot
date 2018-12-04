package edu.depaul.cdm.se450.project.views;

import edu.depaul.cdm.se450.project.util.Observer;
import edu.depaul.cdm.se450.project.util.EventCode;

/**
 *  OutputView for the application. Provides very simple functionality
 *	of displaying start-up banner and echoing user input.
 *
 */

public class OutputView extends View implements Observer {

    /**
     *  Receive notification of an event in an Observable object.
     */

    public void handleEvent( EventCode eventCode ) {

        /*
         *  Handle the two event types for an OutputView:
         *	<p>
         *	(1) Print start-up banner to console.
         *	<p>
         *	(2) Echo user input to the console.
         */

        //  Read banner from model and display.

        if ( eventCode == EventCode.INITIALIZE ) {
            System.out.println(model.getBanner());
        }

        //  Retrieve user input from model and display.

        else if ( eventCode == EventCode.DISPLAY_USER_INPUT ) {
        	//will fetch whatever the output string should be from the model
            String out = model.getOutput();
            System.out.println(out);
        }

        //	Ignore any other events.

        else { /*	Do nothing.	*/ }
    }
}