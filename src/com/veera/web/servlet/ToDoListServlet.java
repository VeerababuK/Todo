package com.veera.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.veera.bean.Todo;
import com.veera.bean.User;
import com.veera.util.DBUtil;
import com.veera.util.Message;

public class ToDoListServlet extends HttpServlet {

	private static final long serialVersionUID = 1300941880392413959L;

	private static final Logger logger = LogManager.getLogger(LoginServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String methodValue = req.getParameter("method");

		boolean isInsertTodo = "INSERT_TODO".equals(methodValue);
		boolean isUpdateCompleteTodo = "UPDATE_COMPLETE_TODO".equals(methodValue);
		boolean isUpdateActiveTodo = "UPDATE_ACTIVE_TODO".equals(methodValue);
		boolean isDeleteTodo = "DELETE_TODO".equals(methodValue);

		User user = (User) req.getSession().getAttribute("LOGIN_USER");

		if (isInsertTodo) {
			String description = req.getParameter("description");
			String noOfDaysStr = req.getParameter("noOfDays");
			int numberOfDays = 0;
			if (noOfDaysStr != null) {
				numberOfDays = Integer.parseInt(noOfDaysStr);
			}

			Todo todo = new Todo();
			todo.setDescription(description);
			todo.setNumberOfDays(numberOfDays);

			if (DBUtil.insertTodo(todo, user)) {
				req.setAttribute("MESSAGE", Message.SUCCESSFUL_INSERTION);
				logger.debug(Message.SUCCESSFUL_INSERTION);
			} else {
				req.setAttribute("ERROR_MESSAGE", Message.UNSUCCESSFUL_INSERTION);
				logger.debug(Message.UNSUCCESSFUL_INSERTION);
			}
		} else if (isUpdateCompleteTodo || isUpdateActiveTodo) {
			String todoOidStr = req.getParameter("oid");

			long todoOid = 0;
			if (todoOidStr != null) {
				todoOid = Integer.parseInt(todoOidStr);
			}

			Todo todo = DBUtil.getTodo(todoOid, user.getOid());
			if (todo != null) {
				if (isUpdateCompleteTodo) {
					todo.setCompleted(!todo.isCompleted());
				} else if (isUpdateActiveTodo) {
					todo.setActive(!todo.isActive());
				}

				if (DBUtil.updateTodo(todo)) {
					req.setAttribute("MESSAGE", Message.SUCCESSFUL_UPDATION);
					logger.debug(Message.SUCCESSFUL_UPDATION);
				} else {
					req.setAttribute("ERROR_MESSAGE", Message.UNSUCCESSFUL_UPDATION);
					logger.debug(Message.UNSUCCESSFUL_UPDATION);
				}
			}
		} else if (isDeleteTodo) {
			String todoOidStr = req.getParameter("oid");

			long todoOid = 0;
			if (todoOidStr != null) {
				todoOid = Integer.parseInt(todoOidStr);
			}

			Todo todo = DBUtil.getTodo(todoOid, user.getOid());
			if (todo != null && DBUtil.deleteTodo(todo)) {
				req.setAttribute("MESSAGE", Message.SUCCESSFUL_DELETION);
				logger.debug(Message.SUCCESSFUL_DELETION);
			} else {
				req.setAttribute("ERROR_MESSAGE", Message.UNSUCCESSFUL_DELETION);
				logger.debug(Message.UNSUCCESSFUL_DELETION);
			}
		}

		List<Todo> todoList = DBUtil.getTodoList(user.getOid());		
		req.setAttribute("TODOLIST", todoList);
		logger.debug("TODOLIST data size : " + todoList.size());
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/todoList.jsp");
		requestDispatcher.forward(req, resp);
	}

}
