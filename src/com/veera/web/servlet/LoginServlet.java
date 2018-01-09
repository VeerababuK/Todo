package com.veera.web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.veera.bean.User;
import com.veera.util.DBUtil;
import com.veera.util.Message;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 3571027017506324967L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String methodValue = req.getParameter("method");
		boolean isLoginMethod = "LOGIN".equals(methodValue);

		if (isLoginMethod) {
			String userName = req.getParameter("user_name");
			String password = req.getParameter("password");
			if (DBUtil.isValidUser(userName, password)) {
				User user = DBUtil.getUser(userName, password);
				HttpSession session = req.getSession();
				session.setAttribute("LOGIN_USER", user);
				RequestDispatcher requestDispatcher = req.getRequestDispatcher("todoList");
				requestDispatcher.forward(req, resp);
			} else {
				RequestDispatcher requestDispatcher = req.getRequestDispatcher("/");
				req.setAttribute("ERROR_MESSAGE", Message.LOGIN_ERROR_MESSAGE);
				requestDispatcher.forward(req, resp);
			}

		}

	}

}
