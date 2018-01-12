package com.veera.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.veera.bean.Todo;
import com.veera.bean.User;
import com.veera.web.servlet.LoginServlet;

public class DBUtil {

	public static final Logger logger = LogManager.getLogger(LoginServlet.class);

	// LOGIN table fields
	public static final String LOGIN_OID = "OID";
	public static final String LOGIN_USER_NAME = "USER_NAME";
	public static final String LOGIN_PASSWORD = "PASSWORD";
	// TODO table fields
	public static final String TODO_OID = "OID";
	public static final String TODO_DESCRIPTION = "TODO_DESC";
	public static final String TODO_DAYS = "DAYS";
	public static final String TODO_ACTIVE = "ACTIVE";
	public static final String TODO_COMPLETED = "COMPLETED";
	public static final String TODO_FK_LOGIN_OID = "LOGIN_OID";

	public static final Connection getDatabaseConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/todo", "root", "root");
	}

	public static final boolean isValidUser(String userName, String password) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = getDatabaseConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM LOGIN WHERE USER_NAME = ? AND PASSWORD = ?");
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, password);
			logger.debug("SQL Statement : " + preparedStatement.toString());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
			}
		}
		return false;
	}

	public static final User getUser(String userName, String password) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = getDatabaseConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM LOGIN WHERE USER_NAME = ? AND PASSWORD = ?");
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				User user = new User();
				user.setOid(resultSet.getLong(LOGIN_OID));
				user.setUserName(resultSet.getString(LOGIN_USER_NAME));
				user.setPassword(resultSet.getString(LOGIN_PASSWORD));

				logger.debug("SQL Statement : " + preparedStatement.toString());

				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
			}
		}
		return null;
	}

	public static final boolean insertTodo(Todo todo, User user) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = getDatabaseConnection();
			preparedStatement = connection.prepareStatement(
					"INSERT INTO TODO(TODO_DESC, DAYS, COMPLETED, ACTIVE, LOGIN_OID) VALUES (?,?,?,?,?)");
			preparedStatement.setString(1, todo.getDescription());
			preparedStatement.setInt(2, todo.getNumberOfDays());
			preparedStatement.setBoolean(3, false);
			preparedStatement.setBoolean(4, true);
			preparedStatement.setLong(5, user.getOid());
			logger.debug("SQL Statement : " + preparedStatement.toString());
			return preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
			}
		}

		return false;
	}

	public static final boolean updateTodo(Todo todo) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = getDatabaseConnection();
			preparedStatement = connection.prepareStatement("UPDATE TODO SET COMPLETED = ?, ACTIVE = ? WHERE OID = ?");
			preparedStatement.setBoolean(1, todo.isCompleted());
			preparedStatement.setBoolean(2, todo.isActive());
			preparedStatement.setLong(3, todo.getOid());
			logger.debug("SQL Statement : " + preparedStatement.toString());
			return preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
			}
		}

		return false;
	}

	public static final boolean deleteTodo(Todo todo) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = getDatabaseConnection();
			preparedStatement = connection.prepareStatement("DELETE FROM TODO WHERE OID = ?");
			preparedStatement.setLong(1, todo.getOid());
			logger.debug("SQL Statement : " + preparedStatement.toString());
			return preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
			}
		}

		return false;

	}

	public static final Todo getTodo(long todoOid, long loginOid) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = getDatabaseConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM TODO WHERE OID = ? AND LOGIN_OID = ?");
			preparedStatement.setLong(1, todoOid);
			preparedStatement.setLong(2, loginOid);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				Todo todo = new Todo();
				todo.setOid(resultSet.getLong(TODO_OID));
				todo.setDescription(resultSet.getString(TODO_DESCRIPTION));
				todo.setNumberOfDays(resultSet.getInt(TODO_DAYS));
				todo.setCompleted(resultSet.getBoolean(TODO_COMPLETED));
				todo.setActive(resultSet.getBoolean(TODO_ACTIVE));
				logger.debug("SQL Statement : " + preparedStatement.toString());
				return todo;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
			}
		}

		return null;
	}

	public static final List<Todo> getTodoList(long loginOid) {
		List<Todo> todoList = new ArrayList<Todo>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = getDatabaseConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM TODO WHERE LOGIN_OID = ?");
			preparedStatement.setLong(1, loginOid);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Todo todo = new Todo();
				todo.setOid(resultSet.getLong(TODO_OID));
				todo.setDescription(resultSet.getString(TODO_DESCRIPTION));
				todo.setNumberOfDays(resultSet.getInt(TODO_DAYS));
				todo.setCompleted(resultSet.getBoolean(TODO_COMPLETED));
				todo.setActive(resultSet.getBoolean(TODO_ACTIVE));
				todoList.add(todo);
			}

			logger.debug("SQL Statement : " + preparedStatement.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
			}
		}

		return todoList;
	}

}
