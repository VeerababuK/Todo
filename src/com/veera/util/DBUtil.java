package com.veera.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.veera.bean.Todo;
import com.veera.bean.User;

public class DBUtil {

	public static final Connection getDatabaseConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/todo", "root", "root");
	}

	public static final boolean isValidUser(String userName, String password) {
		try {
			Connection connection = getDatabaseConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM LOGIN WHERE USER_NAME = ? AND PASSWORD = ?");
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static final User getUser(String userName, String password) {
		try {
			Connection connection = getDatabaseConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM LOGIN WHERE USER_NAME = ? AND PASSWORD = ?");
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				User user = new User();
				user.setOid(resultSet.getLong("OID"));
				user.setUserName(resultSet.getString("USER_NAME"));
				user.setPassword(resultSet.getString("PASSWORD"));

				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static final boolean insertTodo(Todo todo) {
		try {
			Connection connection = getDatabaseConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO TODO(TODO_DESC, DAYS, COMPLETED, ACTIVE) VALUES (?,?,?,?,?)");
			preparedStatement.setString(1, todo.getDescription());
			preparedStatement.setInt(2, todo.getNumberOfDays());
			preparedStatement.setBoolean(3, false);
			preparedStatement.setBoolean(5, true);

			return preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public static final boolean updateTodo(Todo todo) {
		try {
			Connection connection = getDatabaseConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("UPDATE TODO SET COMPLETED = ?, ACTIVE = ? WHERE OID = ?");
			preparedStatement.setBoolean(1, todo.isCompleted());
			preparedStatement.setBoolean(2, todo.isActive());
			preparedStatement.setLong(3, todo.getOid());

			return preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public static final boolean deleteTodo(Todo todo) {
		try {
			Connection connection = getDatabaseConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM TODO WHERE OID = ?");
			preparedStatement.setLong(3, todo.getOid());

			return preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public static final Todo getTodo(long todoOid, long loginOid) {
		try {
			Connection connection = getDatabaseConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM TODO WHERE OID = ? LOGIN_OID = ?");
			preparedStatement.setLong(1, todoOid);
			preparedStatement.setLong(2, loginOid);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				Todo todo = new Todo();
				todo.setOid(resultSet.getLong("OID"));
				todo.setDescription(resultSet.getString("TODO_DESC"));
				todo.setNumberOfDays(resultSet.getInt("DAYS"));
				todo.setCompleted(resultSet.getBoolean("COMPLETED"));
				todo.setActive(resultSet.getBoolean("ACTIVE"));
				return todo;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static final List<Todo> getTodoList(long loginOid) {
		List<Todo> todoList = new ArrayList<Todo>();
		try {
			Connection connection = getDatabaseConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM TODO WHERE LOGIN_OID = ?");
			preparedStatement.setLong(1, loginOid);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Todo todo = new Todo();
				todo.setOid(resultSet.getLong("OID"));
				todo.setDescription(resultSet.getString("TODO_DESC"));
				todo.setNumberOfDays(resultSet.getInt("DAYS"));
				todo.setCompleted(resultSet.getBoolean("COMPLETED"));
				todo.setActive(resultSet.getBoolean("ACTIVE"));
				todoList.add(todo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return todoList;
	}

}
