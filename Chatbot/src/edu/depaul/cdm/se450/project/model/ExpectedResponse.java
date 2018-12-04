package edu.depaul.cdm.se450.project.model;

import edu.depaul.cdm.se450.project.util.Response;

public class ExpectedResponse implements Response {
	private Model model;
	private SudoChatbotDB database;
	
    public void setModel( Model m ) {
        model = m;
    }
    public void setDB(SudoChatbotDB database) {
    	this.database = database;
    }
    
	public void handleInput(String in) {
		// TODO Auto-generated method stub
		if(in.contains(database.getUser().getTerminator())) {
			model.startExpected(database.getChatbot().getTerminator(), true);
		}
		else if(database.isMovieQuery()) {
			database.setMovieQuery(false);
			database.setActorQuery(true);
			model.startExpected("I've seen that movie", false);
			
		}
		else if(database.isActorQuery()) {
			database.setActorQuery(false);
			database.setActressQuery(true);
			model.startExpected("I " + database.getActors().get(in) + " that actor", false);
		}
		else if(database.isActressQuery()) {
			database.setActressQuery(false);
			database.setDirectorQuery(true);
			model.startExpected("I " + database.getActresses().get(in) + " that actress", false);
		}
		else if(database.isDirectorQuery()) {
			database.setDirectorQuery(false);
			model.startExpected("I " + database.getDirectors().get(in)+ " that director", false);
		}
		else if(database.isFilmQuery()) {
			database.setFilmQuery(false);
			model.startExpected("I've seen that move", true);
		}
		else {
			database.setMovieQuery(true);
			model.startExpected("I'm glad to hear it", false);
		}
	}
	
	

}
