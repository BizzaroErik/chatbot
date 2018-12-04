package edu.depaul.cdm.se450.project.model;


import java.util.Random;

import edu.depaul.cdm.se450.project.util.Response;

public class UnexpectedResponse implements Response{
	private Model model;
	private SudoChatbotDB database;
	
	public void setDB(SudoChatbotDB database) {
		this.database = database;
	}
    public void setModel( Model m ) {
        model = m;
    }
    
	public void handleInput(String in) {
		if(in.contains("I am")) {
			String userInput = in.substring(in.indexOf("am")+2, in.length());
			String predicateResponse = "I understand you are"+ userInput;
			database.setMovieQuery(true);
			model.startUnexpected(predicateResponse, false);
		}
		else if(database.isMovieQuery()) {
			database.setMovieQuery(false);
			database.setActorQuery(true);
			model.startUnexpected("I haven't seen that movie", false);
			
		}
		else if(database.isActorQuery()) {
			database.setActorQuery(false);
			database.setActressQuery(true);
			model.startUnexpected("I don't know that actor", false);
			
		}
		else if(database.isActressQuery()) {
			database.setActressQuery(false);
			database.setDirectorQuery(true);
			model.startUnexpected("I don't know that actress", false);
		}
		else if(database.isDirectorQuery()) {
			database.setDirectorQuery(false);
			database.setFilmQuery(true);
			model.startUnexpected("I don't know that director", false);
		}
		else if(database.isFilmQuery()) {
			database.setFilmQuery(false);
			model.startUnexpected("Sorry, I've never seen " + in + "." , true);
		}
		else {
			Random randomInt = new Random();
			int replyIndex = randomInt.nextInt(database.getResponse().size()-1) + 1;
			System.out.println("we crashed before here");
			model.startUnexpected(database.getResponse().get(replyIndex), true);
		}
	}

}
