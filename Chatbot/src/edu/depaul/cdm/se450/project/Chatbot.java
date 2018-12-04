package edu.depaul.cdm.se450.project;

import edu.depaul.cdm.se450.project.controllers.Controller;
import edu.depaul.cdm.se450.project.model.ExpectedResponse;
import edu.depaul.cdm.se450.project.model.FileHandler;
import edu.depaul.cdm.se450.project.model.Model;
import edu.depaul.cdm.se450.project.model.SudoChatbotDB;
import edu.depaul.cdm.se450.project.model.UnexpectedResponse;
import edu.depaul.cdm.se450.project.util.Response;
import edu.depaul.cdm.se450.project.views.InputView;
import edu.depaul.cdm.se450.project.views.OutputView;

/**
 *  This is the driver (start-up) class for a sample implementation of
 *  the application. It demonstrates the basic principles of the MVC architecture,
 *  <p>
 *
 */

public class Chatbot {

    /*
     *  Note: This application requires only one View and one Controller.
     *        More complex interactive applications might need several
     *        Views and Controllers.
     */

    public static void main( String[] args ) {
    	
    	//checks that a config file was included with this program
    	String configFile;
		if(args.length > 0) {
			configFile = args[0];
		}
		//default file if no file is included
		else configFile = "Chatbot_XML.xml";

        Model eModel;               //  Model architectural component
        InputView inputView;        //  Input view architectural component
        OutputView outputView;		//	Output view architectural component
        Controller eController;     //  Controller architectural component

        
        Response exResp;
        Response unexResp;
        FileHandler fh;
        //SudoChatbotDB database;
        
        //New Bruce Banner for program with initial opening line
        String eBanner = "Welcome to Chatbot v0.9 \nDo"
        		+ " you wish to display the configuration file (y or n)?";
        
      
				/*
		         *  Create instances of each of the architectural components
		         *  of the application: Model, View, and Controller.
		         */

		 eModel = new Model();
		 
		 //passes the name of the configuration file to the model
		 //also reads and dissects the sudo XML file into conversation parts
		
		 //database = new SudoChatbotDB();
		 exResp = new ExpectedResponse();
		 unexResp = new UnexpectedResponse();
		 fh = new FileHandler();
		 fh.setConfigFile(configFile);
		 fh.setModel(eModel);
		 exResp.setModel(eModel);
		 unexResp.setModel(eModel);
		 eModel.setExpectedResponse(exResp);
		 eModel.setUnexpectedResponse(unexResp);
		// eModel.setSudoChatbotDB(database);
		 
		 
		 eModel.setFileHandler(fh);
		 eController = new Controller();

		        /*
		         *  Provide each of the architectural elements with the
		         *  appropriate references needed for them to set up
		         *  the Observer pattern.
		         */

		  inputView = new InputView();
		  inputView.setModel( eModel );
		  eController.setView( inputView );
		  outputView = new OutputView();
		  outputView.setModel( eModel );
		  

		        /*
		         *  Provide the Controller with a reference to the Model so
		         *  that it can update the Model.
		         */

		  eController.setModel( eModel );

		        /*
		         * Initialize Model instance with program banner.
		         */

		  eModel.setBanner( eBanner );

		        /*
		         *  Start execution of the Model.
		         */
		  
		  eModel.run();

		        /*
		         *  Let user know program terminated properly.
		         */

		        System.out.println( "Chatbox has terminated" );
			
        
    }
}