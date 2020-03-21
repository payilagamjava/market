package com.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginAction")
public class LoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = "", email, pwd;
		email = request.getParameter("email");
		pwd = request.getParameter("password");

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		DBAction db;
		HashMap hm = null;
		try {
			db = new DBAction();
			 hm= db.getLogin(email, pwd);
		} catch (Exception e) {
		}
		
		if (hm.size() != 0) {
			HttpSession session = request.getSession();
			session.setAttribute("user", hm.get("name"));
			session.setAttribute("u_mobile", hm.get("mobile"));
			session.setAttribute("u_role", hm.get("role"));
			session.setAttribute("u_email", hm.get("email"));
			response.sendRedirect("welcome.jsp");
		}else {
			out.println("Invalid Email or password");
			out.println	("Try Again? <a href=\"login.html\">Sign in</a>");//
		}
	}
}
