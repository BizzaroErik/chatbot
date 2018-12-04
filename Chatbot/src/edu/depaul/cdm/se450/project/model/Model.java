package edu.depaul.cdm.se450.project.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;

import edu.depaul.cdm.se450.project.util.Observer;
import edu.depaul.cdm.se450.project.util.Response;
import edu.depaul.cdm.se450.project.util.Observable;
import edu.depaul.cdm.se450.project.util.EventCode;


public class Model implements Observable {
	private SudoChatbotDB database;
	private FileHandler fh;
	private Response exResp;
	private Response unexResp;
	
    private StringBuilder banner;        
    private StringBuilder input;    
    private String output;
    private ArrayList<Observer> observers = new ArrayList<Observer>();
	
	public void setSudoChatbotDB(SudoChatbotDB database) {
		this.database = database;
	}
	public SudoChatbotDB getSudoChatbotDB() {
		return database;
	}

    public void setExpectedResponse(Response ex) {

    	exResp = ex;
    }
    public void setUnexpectedResponse(Response unex) {

    	unexResp = unex;
    }
  
    public String getOutput() {
    	return output;
    }  
    public void setBanner( String b ) {
        banner = new StringBuilder( b );
    }
    public String getBanner() {
        return banner.toString();
    }
    public String getUserInput() {
        return input.toString();
    }
 
    public void setFileHandler(FileHandler fh) {
    	this.fh = fh;
    	fh.setUpFileConfig();
    }

    
    
    public void run() {
        notifyObservers( EventCode.INITIALIZE );		//  Initialization
        notifyObservers(EventCode.FILE_QUERY);
    }

    public void displayFileContents() {
    	output = fh.getFileContents();
    	notifyObservers(EventCode.DISPLAY_USER_INPUT);
    }
    
    public void startConversation() {
    	fh.sudoXMLParser();
    	unexResp.setDB(this.database);
    	exResp.setDB(database);
    	ArrayList<String> opener = database.getStatement();
    	ArrayList<String> initialQuestion = database.getQuery();
    	output = opener.get(0) + "\n" + opener.get(1) + "\n" + initialQuestion.get(0);
    	notifyObservers( EventCode.DISPLAY_USER_INPUT);
    	notifyObservers(EventCode.READ_USER_INPUT);
    }
    
    public void startExpected(String out, boolean isEnd) {
    	output = out;
    	ArrayList<String> question = database.getQuery();
    	if(isEnd) {
    		notifyObservers( EventCode.DISPLAY_USER_INPUT);
    	}
    	else {
    		notifyObservers( EventCode.DISPLAY_USER_INPUT);
    		if(database.isMovieQuery()) {
    			output = question.get(1);
    		}
    		else if(database.isActorQuery()) {
    			output = question.get(2);
    		}
    		else if(database.isActressQuery()) {
    			output = question.get(3);
    		}
    		else if(database.isDirectorQuery()) {
    			output = question.get(4);
    		}
    		else {
    		}
    		notifyObservers( EventCode.DISPLAY_USER_INPUT);
    		notifyObservers(EventCode.READ_USER_INPUT);
    	}
    }
    
    public void startUnexpected(String out, boolean wait) {
    	output = out;
    	ArrayList<String> question = database.getQuery();
    	if(!wait) {
    		notifyObservers( EventCode.DISPLAY_USER_INPUT);
    		if(database.isMovieQuery()) {
    			output = question.get(1);
    		}
    		else if(database.isActorQuery()) {
    			output = question.get(2);
    		}
    		else if(database.isActressQuery()) {
    			output = question.get(3);
    		}
    		else if(database.isDirectorQuery()) {
    			output = question.get(4);
    		}
    		else if(database.isFilmQuery()) {
    			output = "Could you name a film that director has made?";
    		}
    		else {
    		}
    		notifyObservers( EventCode.DISPLAY_USER_INPUT);
    		notifyObservers(EventCode.READ_USER_INPUT);
    	}
    	else {
    		notifyObservers( EventCode.DISPLAY_USER_INPUT);
    		notifyObservers(EventCode.READ_USER_INPUT);
    	}
    }
    
    public void handleUserInput( String in ) {
    	boolean isExpected = false;
    	boolean movieBool = false;
    	ArrayList<String> question = database.getQuery();
    	ArrayList<String> statement = database.getStatement();
    	ArrayList<String> response = database.getResponse();
    	ArrayList<String> movies = database.getMovies();
    	HashMap<String, String> actors = database.getActors();
    	HashMap<String, String> actresses = database.getActresses();
    	HashMap<String, String> directors = database.getDirectors();
    	
    	for(int i = 0; i < question.size(); i++) {
    		if(in.equals(question.get(i))) {
    			isExpected = true;
    		}
    	}
    	for(int i = 0; i < statement.size(); i++) {
    		if(in.equals(statement.get(i))) {
    			isExpected = true;
    		}
    	}
    	for(int i = 0; i < response.size(); i++ ) {
    		if(in.equals(response.get(i))) {
    			isExpected = true;
    		}
    	}
    	for(int i = 0; i< movies.size(); i++) {
    		if(in.equals(movies.get(i))) {
    			movieBool = true;
    		}
    	}
    	if(actors.containsKey(in) || actresses.containsKey(in) || directors.containsKey(in)) {
    		movieBool = true;
    	}
    	if(isExpected || in.equals(database.getUser().getTerminator()) || movieBool) {
    		exResp.handleInput(in);
    	}
    	else {
    		unexResp.handleInput(in);
    	}

    }

    
    
    /**
     *  Add an observer to the list of observers for this object. An observer
     *  is entered into the list only once.
     *
     *  @param o observer to be added to list of observers for this object
     */

    public void addObserver( Observer o ) {
        if ( !observers.contains( o ) )
            observers.add( o );
    }
    /**
     *  Remove an observer from the list of observers for this object.
     *
     *  @param o observer to be removed from list of observers for this object
     */
    public void removeObserver( Observer o ) {
        if ( !observers.remove( o ) )
            System.out.println( "Error in Model.removeObserver: " +
                                "Specified Observer not in list." );
    }
    /**
     *  Notify observers of this object that an event in this object has
     *  occurred.
     *
     *	@param eventCode value indicating which event occurred
     */
    public void notifyObservers( EventCode eventCode ) {
        ListIterator<Observer> listIterator = observers.listIterator();
        while ( listIterator.hasNext() ) {
            ( listIterator.next() ).handleEvent( eventCode );
        }
    }
}