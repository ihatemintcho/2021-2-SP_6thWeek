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
		
		String title, desc, category, due_date;
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
		
		TodoItem t = new TodoItem(title, desc, category, due_date);
		if(l.addItem(t)>0)
			System.out.println("추가되었습니다");
	}
	
	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[항목 삭제]\n" + "삭제할 항목의 번호를 입력하시오 > ");
		int index = sc.nextInt();
		if (l.deleteItem(index)>0)
			System.out.println("삭제되었습니다.");
	}
	
	public static void updateItem(TodoList l) {
		
		String new_title, new_desc, new_category, new_due_date;
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
		
		TodoItem t = new TodoItem(new_title, new_desc, new_category, new_due_date);
		t.setId(index);
		if(l.updateItem(t) > 0)
			System.out.println("수정되었습니다.");
	}
	
	public static void listAll(TodoList l) {
		System.out.printf("[전체 목록, 총 %d개]\n", l.getCount());
		for (TodoItem item : l.getList()) {
			System.out.printf("%d ", item.getId());
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
			System.out.printf("%d ", item.getId());
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
			System.out.printf("%d ", item.getId());
			System.out.println(item.toString());
		}
	}
	
	/*
	public static void saveList(TodoList list, String filename) {	// use FileWriter 
		try {
			Writer w = new FileWriter(filename);
			for(TodoItem item : list.getList()) {
				w.write(item.toSaveString());
			}
			w.close();
			System.out.println("저장되었습니다!");
		} catch(FileNotFoundException e) {
		} catch (IOException e) {
		}
	}
	*/
}
