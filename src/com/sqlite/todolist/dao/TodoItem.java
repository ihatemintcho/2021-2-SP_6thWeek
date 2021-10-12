package com.sqlite.todolist.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TodoItem {
	private int id;
	private String category;	
	private String title;
	private String desc;
	private String due_date;
	private String current_date;
	private int is_completed;
	private String people;
	private String apparatus;
	
	public TodoItem(String category, String title, String desc, String due_date, int is_completed, String people, String apparatus) {
		this.category = category;
		this.title = title;
		this.desc = desc;
		this.due_date = due_date;
		SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
		this.current_date = f.format(new Date());
		this.is_completed = is_completed;
		this.people = people;
		this.apparatus = apparatus;
	}
	
	
	public String toSaveString() {
		return category + "##" + title + "##" + desc + "##" + due_date + "##" + current_date + "\n";
	}	

	
	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDesc() {
		return desc;
	}


	public void setDesc(String desc) {
		this.desc = desc;
	}


	public String getDue_date() {
		return due_date;
	}


	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}


	public String getCurrent_date() {
		return current_date;
	}


	public void setCurrent_date(String current_date) {
		this.current_date = current_date;
	}
	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getIs_completed() {
		return is_completed;
	}


	public void setIs_completed(int is_completed) {
		this.is_completed = is_completed;
	}


	public String getPeople() {
		return people;
	}


	public void setPeople(String people) {
		this.people = people;
	}


	public String getApparatus() {
		return apparatus;
	}


	public void setApparatus(String apparatus) {
		this.apparatus = apparatus;
	}


	@Override
	public String toString() {
		if(this.getIs_completed() == 1)
			return id + " [" + desc + "] " + category + " [V] - " + title + " - "
				+ due_date + " - " + current_date + " // " + people + " // " + apparatus;
		else
			return id + " [" + desc + "] " + category + " - " + title + " - "
				+ due_date + " - " + current_date + " // " + people + " // " + apparatus;
	}
}
