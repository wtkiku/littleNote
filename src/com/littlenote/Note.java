package com.littlenote;

public class Note {
	private int id;
	private String title;
	private String body;
	private String time;
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return id;
	}
	public void setTitle(String title){
		this.title = title;
	}
	public String getTitle(){
		return title;
	}
	public void setBody(String body){
		this.body = body;
	}
	public String getBody(){
		return body;
	}
	public void setTime(String time){
		this.time = time;
	}
	public String getTime(){
		return time;
	}
}
