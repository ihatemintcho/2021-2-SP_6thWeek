package com.sqlite.todolist.menu;

public class Menu {
	public static void displaymenu() {
		System.out.println();
		System.out.println("<ToDoList Management Command Inputs>");
		System.out.println("add - add new item");
		System.out.println("del <id> - delete item(s)");
		System.out.println("edit - update item");
		System.out.println("comp <id> - mark item(s) as completed");
		System.out.println("ls - display all items");
		System.out.println("ls_name - display all items by name");
		System.out.println("ls_name_desc - display all items by name, reversed");
		System.out.println("ls_date - display all items by date");
		System.out.println("ls_date_desc - display all items by date, reversed");
		System.out.println("ls_comp - display all items that are completed");
		System.out.println("find <keyword> - find and display item that which contains a keyword");
		System.out.println("find_cate <keyword> - find and display all items in searched catgory");
		System.out.println("ls_cate - list all categories");
		System.out.println("ls_only <field> - list only from a certain field");
		System.out.println("exit - exit program");
	}
	
	public static void prompt() {
		System.out.print("\nCommand > ");
	}
}
