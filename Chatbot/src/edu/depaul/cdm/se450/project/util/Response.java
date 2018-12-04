package edu.depaul.cdm.se450.project.util;

import edu.depaul.cdm.se450.project.model.Model;
import edu.depaul.cdm.se450.project.model.SudoChatbotDB;

public interface Response {
	public void setDB(SudoChatbotDB db);
    public void setModel( Model m );
    
	public void handleInput(String in);
}
