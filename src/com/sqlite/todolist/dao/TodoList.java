package com.sqlite.todolist.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.sqlite.todolist.dao.TodoItem;
import com.sqlite.todolist.service.DbConnect;

public class TodoList {
	Connection conn;
	
	public TodoList() {
		this.conn = DbConnect.getConnection();
	}
	
	public void importData(String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line;
			String sql = "insert into todolist (title, memo, category, current_date, due_date)"
						+ " values (?,?,?,?,?);";
			int records = 0;
			while((line = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "##");
				String category = st.nextToken();
				String title = st.nextToken();
				String desc = st.nextToken();
				String due_date = st.nextToken();
				String current_date = st.nextToken();
				
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, title);
				pstmt.setString(2, desc);
				pstmt.setString(3, category);
				pstmt.setString(4, current_date);
				pstmt.setString(5, due_date);
				int count = pstmt.executeUpdate();
				if(count > 0) records++;
				pstmt.close();
			}
			System.out.println(records + " records read!!");
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int addItem(TodoItem t) {
		String sql = "insert into todolist (title, memo, category, current_date, due_date)"
					+ " values (?,?,?,?,?);";
		PreparedStatement pstmt;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getTitle());
			pstmt.setString(2, t.getDesc());
			pstmt.setString(3, t.getCategory());
			pstmt.setString(4, t.getCurrent_date());
			pstmt.setString(5, t.getDue_date());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public int deleteItem(int index) {
		
		String sql = "delete from todolist where id=?;";
		PreparedStatement pstmt;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, index);;
			count = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public int updateItem(TodoItem t) {
		String sql = "update todolist set title=?, memo=?, category=?, current_date=?, due_date=?"
					+ " where id = ?;";
		PreparedStatement pstmt;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getTitle());
			pstmt.setString(2, t.getDesc());
			pstmt.setString(3, t.getCategory());
			pstmt.setString(4, t.getCurrent_date());
			pstmt.setString(5, t.getDue_date());
			pstmt.setInt(6,  t.getId());
			count = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public ArrayList<TodoItem> getList() {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT * FROM todolist";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int id = 				rs.getInt("id");
				String category = 		rs.getString("category");
				String title = 			rs.getString("title");
				String desc = 			rs.getString("memo");
				String due_date = 		rs.getString("due_date");
				String current_date = 	rs.getString("current_date");
				TodoItem t = new TodoItem(title, desc, category, due_date);
				t.setId(id);
				t.setCurrent_date(current_date);
				list.add(t);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<TodoItem>getList(String keyword) {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		keyword = "%"+keyword+"%";
		try {
			String sql = "SELECT * FROM todolist WHERE title like ? OR memo like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,keyword);
			pstmt.setString(2,keyword);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int id = 				rs.getInt("id");
				String category = 		rs.getString("category");
				String title = 			rs.getString("title");
				String desc = 			rs.getString("memo");
				String due_date = 		rs.getString("due_date");
				String current_date = 	rs.getString("current_date");
				TodoItem t = new TodoItem(title, desc, category, due_date);
				t.setId(id);
				t.setCurrent_date(current_date);
				list.add(t);
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int getCount() {
		Statement stmt;
		int count = 0;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT count(id) FROM todolist;";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			count = rs.getInt("count(id)");
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public ArrayList<String> getCategories() {
		ArrayList<String> list = new ArrayList<String>();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT DISTINCT category FROM todolist";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				list.add(rs.getString("category"));
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<TodoItem> getListCategory(String keyword) {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		try {
			String sql = "SELECT * FROM todolist WHERE category = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int id = 				rs.getInt("id");
				String category = 		rs.getString("category");
				String title = 			rs.getString("title");
				String desc = 			rs.getString("memo");
				String due_date = 		rs.getString("due_date");
				String current_date = 	rs.getString("current_date");
				TodoItem t = new TodoItem(title, desc, category, due_date);
				t.setId(id);
				t.setCurrent_date(current_date);
				list.add(t);
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<TodoItem> getOrderedList(String orderby, int ordering) {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT * FROM todolist ORDER BY " + orderby;
			if (ordering == 0)
				sql += " desc";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int id = 				rs.getInt("id");
				String category = 		rs.getString("category");
				String title = 			rs.getString("title");
				String desc = 			rs.getString("memo");
				String due_date = 		rs.getString("due_date");
				String current_date = 	rs.getString("current_date");
				TodoItem t = new TodoItem(title, desc, category, due_date);
				t.setId(id);
				t.setCurrent_date(current_date);
				list.add(t);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int isDuplicate(String title) {
		int count=0;
		PreparedStatement pstmt;
		try {
			String sql = "SELECT count(id) FROM todolist WHERE title = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			ResultSet rs = pstmt.executeQuery();
			count = rs.getInt("count(id)");
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
}
