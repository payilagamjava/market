package com.control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class DBAction {
	Statement s = null;

	DBAction() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/record_db", "root", "");
		s = c.createStatement();
	}

	HashMap getLogin(String email, String pwd) throws Exception {
		HashMap <String,String>hm = new HashMap<String,String>();
		ResultSet rs = s.executeQuery("SELECT * FROM sys_user where email='" + email + "' and password ='" + pwd + "'");
		if (rs.next()) {
			hm.put("name", rs.getString("name"));
			hm.put("email", rs.getString("email"));
			hm.put("mobile", rs.getString("mobile"));
			hm.put("role", rs.getString("role"));
		}
	    return hm;
	}
	
	boolean register(String name,String email,String pwd) throws Exception {
		return s.execute("insert into sys_user (name,email,password) values ('"+name+"','"+email+"','"+pwd+"')");	
	}
	


}
