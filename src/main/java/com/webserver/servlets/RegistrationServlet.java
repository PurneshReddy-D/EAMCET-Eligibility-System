package com.webserver.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class RegistrationServlet extends HttpServlet
{
	Connection con;
	PreparedStatement pstmt;
	ResultSet res;
	@Override
	public void init() throws ServletException
	{
		
		String url="jdbc:mysql://localhost:3306/school";
		String un="root";
		String pwd="Purihere@531";
		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url,un,pwd);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException
	{
		//studentRegistration
		
		
		String roll_no=req.getParameter("Roll_No");
		String un=req.getParameter("UserName");
		//String ln=req.getParameter("LastName");
		String em=req.getParameter("email");
		String pl=req.getParameter("Place");
String pass3=req.getParameter("Password");

		String percentage=req.getParameter("10th Percentage");
		int per1=Integer.parseInt(percentage);
		String percentage2=req.getParameter("12th Percentage");
		float per2=Float.parseFloat(percentage2);
		String rank=req.getParameter("EAPCET rank");
		int rank1=Integer.parseInt(rank);
		String cas=req.getParameter("caste");
		
		resp.setContentType("text/html");
		PrintWriter wt=resp.getWriter();
		try {
		String query1="insert into studentdetails(`roll_no`,`username`,`email`,`place`,`10th Percentage`,`12th_percentage`,`password`,`eamcet_rank`,`caste`) values(?,?,?,?,?,?,?,?,?)";
		
		pstmt=con.prepareStatement(query1);
		pstmt.setString(1, roll_no);
		pstmt.setString(2,un);
		pstmt.setString(3,em);
		pstmt.setString(4,pl);
		pstmt.setInt(5,per1);
		pstmt.setFloat(6, per2);
		pstmt.setString(7,pass3);
		pstmt.setInt(8,rank1);
		pstmt.setString(9,cas);
		pstmt.execute();
		wt.println("<h3>Registration completed!!!</h3>");
		

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	


}
