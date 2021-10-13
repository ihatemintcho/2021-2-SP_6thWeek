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
		boolean quit = false;		//flag var
		System.out.println("\n       ,Welcome back!");
		System.out.print("~(^-^)~");
		Menu.displaymenu();
		do {
		
			Menu.prompt();
			String choice = sc.next();
			
			switch (choice) {
			
			case "add" :
				TodoUtil.createItem(l);
				break;
				
			case "del" :
				String item_id = sc.nextLine().trim();
				TodoUtil.deleteItem(l, item_id);
				break;
				
			case "edit" :
				TodoUtil.updateItem(l);
				break;
				
			case "comp" :	///complete item command
				String item_id1 = sc.nextLine().trim();
				String[] tokens = item_id1.split(" ");
				for(String token : tokens) {
					int temp = Integer.parseInt(token);
					TodoUtil.completeItem(l, temp);
				}
				System.out.println("완료체크 햐였습니다.");
				
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
				
			case "ls_comp" :	//list complete items command
				TodoUtil.listAll(l, 1);
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
							
			case "help" :
				Menu.displaymenu();
				break;
				
			default : 
				System.out.println("올바른 명령어를 입려하시오 (\"help\" for list of commands)");
				break;
			}
		} while (!quit);
	}
}
