package com.veera.web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.veera.bean.User;
import com.veera.util.DBUtil;
import com.veera.util.Message;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 3571027017506324967L;
	
	Logger logger = Logger.getLogger(LoginServlet.class);
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/index.jsp");
		System.out.println("success***GET****");
		requestDispatcher.forward(req, resp);
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
				logger.debug("this is a debug log message todoList");
				System.out.println("success********");
				RequestDispatcher requestDispatcher = req.getRequestDispatcher("/todoList");
				requestDispatcher.forward(req, resp);
			} else {
				logger.debug("this is a debug log message /");
				System.out.println("success########");
				RequestDispatcher requestDispatcher = req.getRequestDispatcher("/");
				req.setAttribute("ERROR_MESSAGE", Message.LOGIN_ERROR_MESSAGE);
				requestDispatcher.forward(req, resp);
			}
		}

	}

}
