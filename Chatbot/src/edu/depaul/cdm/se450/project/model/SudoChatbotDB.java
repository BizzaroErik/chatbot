package edu.depaul.cdm.se450.project.model;

import java.util.ArrayList;
import java.util.HashMap;

public class SudoChatbotDB {
    private Entity user;
    private Entity chatbot;
    
	private boolean movieQuery = false;
	private boolean actorQuery = false;
	private boolean actressQuery = false;
	private boolean directorQuery = false;
	private boolean filmQuery = false;
    
    private ArrayList<String> query;
    private ArrayList<String> response;
    private ArrayList<String> statement;
    private ArrayList<String> movies;
    private HashMap<String, String> actors;
    private HashMap<String, String> actresses;
    private HashMap<String, String> directors;
	public Entity getUser() {
		return user;
	}
	public void setUser(Entity user) {
		this.user = user;
	}
	public Entity getChatbot() {
		return chatbot;
	}
	public void setChatbot(Entity chatbot) {
		this.chatbot = chatbot;
	}
	public boolean isMovieQuery() {
		return movieQuery;
	}
	public void setMovieQuery(boolean movieQuery) {
		this.movieQuery = movieQuery;
	}
	public boolean isActorQuery() {
		return actorQuery;
	}
	public void setActorQuery(boolean actorQuery) {
		this.actorQuery = actorQuery;
	}
	public boolean isActressQuery() {
		return actressQuery;
	}
	public void setActressQuery(boolean actressQuery) {
		this.actressQuery = actressQuery;
	}
	public boolean isDirectorQuery() {
		return directorQuery;
	}
	public void setDirectorQuery(boolean directorQuery) {
		this.directorQuery = directorQuery;
	}
	public boolean isFilmQuery() {
		return filmQuery;
	}
	public void setFilmQuery(boolean filmQuery) {
		this.filmQuery = filmQuery;
	}
	public ArrayList<String> getQuery() {
		return query;
	}
	public void setQuery(ArrayList<String> query) {

		this.query = query;
	}
	public ArrayList<String> getResponse() {
		return response;
	}
	public void setResponse(ArrayList<String> response) {
		this.response = response;
	}
	public ArrayList<String> getStatement() {
		return statement;
	}
	public void setStatement(ArrayList<String> statement) {
		this.statement = statement;
	}
	public ArrayList<String> getMovies() {
		return movies;
	}
	public void setMovies(ArrayList<String> movies) {
		this.movies = movies;
	}
	public HashMap<String, String> getActors() {
		return actors;
	}
	public void setActors(HashMap<String, String> actors) {
		this.actors = actors;
	}
	public HashMap<String, String> getActresses() {
		return actresses;
	}
	public void setActresses(HashMap<String, String> actresses) {
		this.actresses = actresses;
	}
	public HashMap<String, String> getDirectors() {
		return directors;
	}
	public void setDirectors(HashMap<String, String> directors) {
		this.directors = directors;
	}
    

 
}
