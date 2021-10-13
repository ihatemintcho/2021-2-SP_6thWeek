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
		
		System.out.print("[�׸� �߰�]\n" + "���� > ");
		title = sc.next();
		
		if(l.isDuplicate(title)>0) {
			System.out.println("������ �ߺ��˴ϴ�!");
			return;
		}

		System.out.print("ī�װ� > ");
		category = sc.next();
		sc.nextLine();
		System.out.print("���� > ");
		desc = sc.nextLine().trim();
		System.out.print("�������� > ");
		due_date = sc.nextLine().trim();
		System.out.print("�ʿ��� ����� > ");
		people = sc.nextLine().trim();
		System.out.print("�غ񹰵� > ");
		apparatus = sc.nextLine().trim();
		
		int completed = 0;
		
		TodoItem t = new TodoItem(category, title, desc, due_date, completed, people, apparatus);
		if(l.addItem(t)>0)
			System.out.println("�߰��Ǿ����ϴ�");
	}
	
	
	public static void deleteItem(TodoList l, String item_id) {
		
		int temp;
		String[] tokens = item_id.split(" ");
		System.out.print("[�׸� ����]\n");
		
		for (String token : tokens) {
			temp = Integer.parseInt(token);
			if(l.deleteItem(temp)<=0) {
				System.out.println("index ����!");
				return;
			}
		}
		System.out.println("�����Ͻ� �׸���� �����Ǿ����ϴ�.");
	}
	
	
	public static void updateItem(TodoList l) {
		
		String new_title, new_desc, new_category, new_due_date, new_people, new_apparatus;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[�׸� ����]\n" + "������ �׸��� ��ȣ�� �Է��Ͻÿ� > ");
		int index = sc.nextInt();
		
		System.out.print("�� ���� > ");
		new_title = sc.next().trim();
		System.out.print("�� ī�װ� > ");
		new_category = sc.next();
		sc.nextLine();
		System.out.print("�� ���� > ");
		new_desc = sc.nextLine().trim();
		System.out.print("�� �������� > ");
		new_due_date = sc.nextLine().trim();
		System.out.print("���� �ʿ��� ����� > ");
		new_people = sc.nextLine().trim();
		System.out.print("���� �ʿ��� �غ񹰵� > ");
		new_apparatus = sc.nextLine().trim();
		
		int completed = 0;
		
		TodoItem t = new TodoItem(new_category, new_title, new_desc, new_due_date, completed, new_people, new_apparatus);
		t.setId(index);
		if(l.updateItem(t) > 0)
			System.out.println("�����Ǿ����ϴ�.");
	}
	
	
	public static void listAll(TodoList l) {
		System.out.printf("[��ü ���, �� %d��]\n", l.getCount());
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
		System.out.printf("\n�� %d���� ī�װ��� ��ϵǾ� �ֽ��ϴ�.\n", count);
	}
	
	
	public static void findList(TodoList l, String keyword) {
		int count = 0;
		for (TodoItem item : l.getList(keyword)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("�� %d���� �׸��� ã�ҽ��ϴ�.\n", count);
	}
	
	
	public static void findCateList(TodoList l, String cate) {
		int count = 0;
		for (TodoItem item : l.getListCategory(cate)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("�� %d���� �׸��� ã�ҽ��ϴ�.\n", count);
	}
	
	
	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.printf("[��ü ���, �� %d��]\n", l.getCount());
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
		System.out.printf("�� %d���� �׸��� ã�ҽ��ϴ�.\n", count);
	}

}
