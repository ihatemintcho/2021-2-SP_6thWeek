package com.sqlite.todolist.service;

import java.util.*;
import com.sqlite.todolist.dao.TodoItem;
import com.sqlite.todolist.dao.TodoList;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.Reader;
import java.io.FileWriter;
import java.io.Writer;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class TodoUtil {
	
	public static void createItem(TodoList l) {
		
		String title, desc, category, due_date, people, apparatus;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[항목 추가]\n" + "제목 > ");
		title = sc.next();
		
		if(l.isDuplicate(title)>0) {
			System.out.println("제목이 중복됩니다!");
			return;
		}

		System.out.print("카테고리 > ");
		category = sc.next();
		sc.nextLine();
		System.out.print("내용 > ");
		desc = sc.nextLine().trim();
		System.out.print("마감일자 > ");
		due_date = sc.nextLine().trim();
		System.out.print("필요한 사람들 > ");
		people = sc.nextLine().trim();
		System.out.print("준비물들 > ");
		apparatus = sc.nextLine().trim();
		
		int completed = 0;
		
		TodoItem t = new TodoItem(category, title, desc, due_date, completed, people, apparatus);
		if(l.addItem(t)>0)
			System.out.println("추가되었습니다");
	}
	
	
	public static void deleteItem(TodoList l, String item_id) {
		
		int temp;
		String[] tokens = item_id.split(" ");
		System.out.print("[항목 삭제]\n");
		
		for (String token : tokens) {
			temp = Integer.parseInt(token);
			if(l.deleteItem(temp)<=0) {
				System.out.println("index 오류!");
				return;
			}
		}
		System.out.println("선택하신 항목들이 삭제되었습니다.");
	}
	
	
	public static void updateItem(TodoList l) {
		
		String new_title, new_desc, new_category, new_due_date, new_people, new_apparatus;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[항목 수정]\n" + "수정할 항목의 번호를 입력하시오 > ");
		int index = sc.nextInt();
		
		System.out.print("새 제목 > ");
		new_title = sc.next().trim();
		System.out.print("새 카테고리 > ");
		new_category = sc.next();
		sc.nextLine();
		System.out.print("새 내용 > ");
		new_desc = sc.nextLine().trim();
		System.out.print("새 마감일자 > ");
		new_due_date = sc.nextLine().trim();
		System.out.print("새로 필요한 사람들 > ");
		new_people = sc.nextLine().trim();
		System.out.print("새로 필요한 준비물들 > ");
		new_apparatus = sc.nextLine().trim();
		
		int completed = 0;
		
		TodoItem t = new TodoItem(new_category, new_title, new_desc, new_due_date, completed, new_people, new_apparatus);
		t.setId(index);
		if(l.updateItem(t) > 0)
			System.out.println("수정되었습니다.");
	}
	
	
	public static void listAll(TodoList l) {
		System.out.printf("[전체 목록, 총 %d개]\n", l.getCount());
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}
	
	
	public static void listCateAll(TodoList l) {
		int count = 0;
		for (String item : l.getCategories()) {
			System.out.print(item + " ");
			count++;
		}
		System.out.printf("\n총 %d개의 카테고리가 등록되어 있습니다.\n", count);
	}
	
	
	public static void findList(TodoList l, String keyword) {
		int count = 0;
		for (TodoItem item : l.getList(keyword)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("총 %d개의 항목을 찾았습니다.\n", count);
	}
	
	
	public static void findCateList(TodoList l, String cate) {
		int count = 0;
		for (TodoItem item : l.getListCategory(cate)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("총 %d개의 항목을 찾았습니다.\n", count);
	}
	
	
	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.printf("[전체 목록, 총 %d개]\n", l.getCount());
		for (TodoItem item : l.getOrderedList(orderby, ordering)) {
			System.out.println(item.toString());
		}
	}

	
	public static void completeItem(TodoList l, int item_id) {
		for (TodoItem item : l.getList()) {
			if (item.getId() == item_id) {
				String same_title = item.getTitle();
				String same_desc = item.getDesc();
				String same_category = item.getCategory();
				String same_due_date = item.getDue_date();
				String same_people = item.getPeople();
				String same_apparatus = item.getApparatus();
				TodoItem t = new TodoItem(same_title, same_desc, same_category, same_due_date, 1, same_people,
						same_apparatus);
				t.setId(item_id);
				if (l.completeItem(t) > 0)
					return;
			}
		}	
	}


	public static void listAll(TodoList l, int i) {
		int count=0;
		for (TodoItem item : l.getList()) {
			if(item.getIs_completed() == 1 ) {
				System.out.println(item.toString());
				count++;
			}
		}
		System.out.printf("총 %d개의 항목을 찾았습니다.\n", count);
	}

}
