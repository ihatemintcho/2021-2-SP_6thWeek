package com.sqlite.todolist;

import java.util.Scanner;

import com.sqlite.todolist.dao.TodoList;
import com.sqlite.todolist.menu.Menu;
import com.sqlite.todolist.service.TodoUtil;

public class TodoMain {

	public static void start() {
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		//l.importData("todolist.txt");
		boolean isList = false;		//flag var
		boolean quit = false;		//flag var
		Menu.displaymenu();
		do {
			Menu.prompt();
			isList = false;
			String choice = sc.next();
			
			switch (choice) {
			
			case "add" :
				TodoUtil.createItem(l);
				break;
				
			case "del" :
				TodoUtil.deleteItem(l);
				break;
				
			case "edit" :
				TodoUtil.updateItem(l);
				break;
				
			case "ls" :
				TodoUtil.listAll(l);
				break;
				
			case "ls_name" :
				System.out.println("제목순으로 정렬하였습니다.");
				TodoUtil.listAll(l, "title", 1);
				break;
				
			case "ls_name_desc" :
				System.out.println("제목역순으로 정렬하였습니다.");
				TodoUtil.listAll(l, "title", 0);
				break;
				
			case "ls_date" :
				System.out.println("날짜순으로 정렬하였습니다.");
				TodoUtil.listAll(l, "due_date", 1);
				break;
				
			case "ls_date_desc" :
				System.out.println("날짜역순으로 정렬하였습니다.");
				TodoUtil.listAll(l, "due_date", 0);
				break;
				
			case "help" :
				Menu.displaymenu();
				break;
				
			case "find" :
				String keyword = sc.nextLine().trim();
				TodoUtil.findList(l, keyword);
				break;
				
			case "find_cate" :
				String cate = sc.next().trim();
				TodoUtil.findCateList(l, cate);
				break;
				
			case "ls_cate":
				TodoUtil.listCateAll(l);
				break;
				
			case "exit" :
				quit = true;
				break;
				
			default : 
				System.out.println("Please enter the correct commmand (\"help\" for list of commands)");
				break;
			}
			
			if(isList) TodoUtil.listAll(l);
		} while (!quit);
		//TodoUtil.saveList(l, "todolist.txt");
	}
}
