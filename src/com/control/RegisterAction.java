package com.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegisterAction")
public class RegisterAction extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name,email,pwd;
		name = request.getParameter("name");
		email = request.getParameter("email");
		pwd = request.getParameter("password");
		response.setContentType("text/html");
		PrintWriter out =response.getWriter();	
		
		boolean isRegister = false;
		
		try {
			new DBAction().register(name,email,pwd); 
			isRegister = true;
		}catch(Exception e) {
			out.println("Something wrong!!!");
		}
		
		if(isRegister == true) {
			response.sendRedirect("login.html");
		}else {
			out.println("Unable to register");
			out.println	("Try Again? <a href=\"register.html\">Create Account</a>");//
		}
	}


}
