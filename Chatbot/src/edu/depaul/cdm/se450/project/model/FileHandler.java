package edu.depaul.cdm.se450.project.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class FileHandler {
	private StringBuilder fileContents;
    private String configFile;
    private Model model;
    
    
    public void setModel(Model model) {
    	this.model = model;
    }
  //setter for what the file name is
  	public void setConfigFile(String configFile) {
  		// TODO Auto-generated method stub
  		this.configFile = configFile;
  	}
  	public String getFile() {
  		return configFile;
  	}
  	public String getFileContents() {
		return fileContents.toString();
	}
  	
    public void setUpFileConfig() {
    	try {
    		BufferedReader fr = new BufferedReader(new FileReader(this.configFile));
    		StringBuilder sb = new StringBuilder();
			String line = fr.readLine();
			
			while(line !=null) {
				sb.append(line);
				sb.append("\n");
				line = fr.readLine();
			}
			
			this.fileContents = sb;
			fr.close();
    	} catch(IOException e) { e.printStackTrace();}
    	
    }
    
    
    public void sudoXMLParser() {
    	
    	SudoChatbotDB database = new SudoChatbotDB();
    	Entity user = new Entity();
    	Entity chatbot = new Entity();
    	ArrayList<String> query = new ArrayList<>();
    	ArrayList<String> response = new ArrayList<>();
    	ArrayList<String> statement = new ArrayList<>();
    	
    	ArrayList<String> movies = new ArrayList<>();
    	HashMap<String, String> actors = new HashMap<>();
    	HashMap<String, String> actresses =  new HashMap<>();
    	HashMap<String, String> directors = new HashMap<>();
    	
    	File inputFile = new File(this.getFile());
    	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
		try {
			dBuilder = dbf.newDocumentBuilder();
			Document docBuild = dBuilder.parse(inputFile);
	        docBuild.getDocumentElement().normalize();
	        NodeList queries = docBuild.getElementsByTagName("query");
	        
	        NodeList statements = docBuild.getElementsByTagName("statement");
	        NodeList responses = docBuild.getElementsByTagName("response");
	        
	        NodeList userDec = docBuild.getElementsByTagName("user");
	        NodeList chatDec = docBuild.getElementsByTagName("chatbot");
	        
	        NodeList movieList = docBuild.getElementsByTagName("movie");
	        NodeList actorList = docBuild.getElementsByTagName("actor");
	        NodeList actressList = docBuild.getElementsByTagName("actress");
	        NodeList directorList = docBuild.getElementsByTagName("director");
	        
	        
	        for(int i = 0; i < movieList.getLength(); i++) {
	        	Node nNode = movieList.item(i);
	        	Element element = (Element) nNode;
	        	movies.add(i, element.getTextContent());
	        	
	        }
	        
	        for(int i = 0; i < actorList.getLength(); i++) {
	        	Node nNode = actorList.item(i);
	        	if(nNode.getNodeType() == Node.ELEMENT_NODE) {
	        		Element element = (Element) nNode;
	        		String name = element.getElementsByTagName("name").item(0).getTextContent();
	        		String eval = element.getElementsByTagName("eval").item(0).getTextContent();
	        		actors.put(name, eval);
	        	}
	        }
	        
	        for(int i = 0; i < actressList.getLength(); i++) {
	        	Node nNode = actressList.item(i);
	        	if(nNode.getNodeType() == Node.ELEMENT_NODE) {
	        		Element element = (Element) nNode;
	        		String name = element.getElementsByTagName("name").item(0).getTextContent();
	        		String eval = element.getElementsByTagName("eval").item(0).getTextContent();
	        		actresses.put(name, eval);
	        	}
	        }
	        
	        for(int i = 0; i < directorList.getLength(); i++) {
	        	Node nNode = directorList.item(i);
	        	if(nNode.getNodeType() == Node.ELEMENT_NODE) {
	        		Element element = (Element) nNode;
	        		String name = element.getElementsByTagName("name").item(0).getTextContent();
	        		String eval = element.getElementsByTagName("eval").item(0).getTextContent();
	        		directors.put(name, eval);
	        	}
	        }
	        
	        Node xUser = userDec.item(0);
	        Element eUser = (Element) xUser;
	        user.setName(eUser.getElementsByTagName("first_name").item(0).getTextContent());
	        user.setTerminator(eUser.getElementsByTagName("terminator").item(0).getTextContent());
	        
	        Node yChat = chatDec.item(0);
	        Element eChat = (Element) yChat;
	        chatbot.setName(eChat.getElementsByTagName("first_name").item(0).getTextContent());
	        chatbot.setTerminator(eChat.getElementsByTagName("terminator").item(0).getTextContent());
	      
	        
	        for(int i = 0; i<queries.getLength(); i++ ) {
	        	Node n = queries.item(i);
	        	Element e = (Element) n;
	        	query.add(i, e.getTextContent());
	        }
	        for(int j = 0; j<statements.getLength(); j++) {
	        	Node n = statements.item(j);
	        	Element e = (Element) n;
	        	String insertNames = e.getTextContent();
	        	if(insertNames.contains("$") && insertNames.contains("Hello")) {
	        		String newInsertName = insertNames.replace("${first_name}", user.getName());
	        		statement.add(newInsertName);
	        	}
	        	else if(insertNames.contains("$") && insertNames.contains("My name")) {
	        		String newInsertName = insertNames.replace("${first_name}", chatbot.getName());
	        		statement.add(newInsertName);
	        	}
	        	else {
	        		statement.add(e.getTextContent());
	        	}
	        }
	        for(int j = 0; j<responses.getLength(); j++) {
	        	Node n = responses.item(j);
	        	Element e = (Element) n;
	        	response.add(e.getTextContent());
	        }
	        
	       // System.out.println(user.getTerminator());
	        database.setUser(user);
	        database.setChatbot(chatbot);
	        database.setQuery(query);
	        database.setResponse(response);
	        database.setStatement(statement);
	        database.setMovies(movies);
	        database.setActors(actors);
	        database.setActresses(actresses);
	        database.setDirectors(directors);
	        
	        model.setSudoChatbotDB(database);
	        
	   
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    

}
