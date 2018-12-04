package edu.depaul.cdm.se450.project.controllers;

import edu.depaul.cdm.se450.project.model.Model;
import edu.depaul.cdm.se450.project.views.InputView;
import edu.depaul.cdm.se450.project.util.Observer;
import edu.depaul.cdm.se450.project.util.EventCode;


/**
 *  Controller for the application.
 *
 */

public class Controller implements Observer {

    private InputView view;                 //  View observed by
    										//	this Controller

    private Model model;                    //  Model controlled by
    										//	this Controller


    /**
     *  Set the Model to be controlled by this Controller.
     *
     *  @param m    model to be controlled
     */

    public void setModel( Model m ) {
        model = m;
    }

    /**
     *  Set the View to be observed by this Controller.
     *
     *  @param v    view to be observed
     */

    public void setView( InputView v ) {
        view = v;
        v.addObserver( this );
    }

    /**
     *  Receive notification of an event in the View.
     *  <p>
     *  This triggers the Controller to retrieve information from
     *  the View and update the Model.
     */

    public void handleEvent( EventCode eventCode ) {

        /*
         *  Note that a more complex program would require more
         *	sophisticated event handling similar to that in OutputView.
         *	See handleEvent() in OuputView for more details.
         */

        if ( eventCode == EventCode.SET_MODEL_VALUE ) {
        	//takes what was entered in the Input View and passes it to the model which will handle whatever was entered by user
        	String userInput = view.getUserInput();
        	model.handleUserInput( userInput );
		}
        
        else if(eventCode == EventCode.YN_EVAL) {
        	String userInput = view.getUserInput();
        	if(userInput.equals("y")) {
        		model.displayFileContents();
        	}
        	else {
        		model.startConversation();
        	}
        }
		else { /*	Do nothing.	*/ }
    }
}