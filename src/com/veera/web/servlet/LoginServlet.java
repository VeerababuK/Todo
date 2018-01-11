package com.veera.web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.veera.bean.User;
import com.veera.util.DBUtil;
import com.veera.util.Message;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 3571027017506324967L;

	private static final Logger logger = LogManager.getLogger(LoginServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String logout = req.getParameter("logout");
		if ("LOGOUT".equals(logout)) {
			req.getSession().invalidate();
			req.setAttribute("Message", Message.USER_SUCCESSFULLY_LOGGED_OUT);
		}
		logger.debug("Login servlet get method");
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/index.jsp");
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
				logger.debug("Login servlet post method success");
				RequestDispatcher requestDispatcher = req.getRequestDispatcher("/todoList");
				requestDispatcher.forward(req, resp);
			} else {
				logger.debug("Login servlet post method default");
				RequestDispatcher requestDispatcher = req.getRequestDispatcher("/");
				req.setAttribute("ERROR_MESSAGE", Message.LOGIN_ERROR_MESSAGE);
				requestDispatcher.forward(req, resp);
			}
		}
	}
}
