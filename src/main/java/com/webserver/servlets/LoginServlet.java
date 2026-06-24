package com.webserver.servlets;

package com.webserver.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class LoginServlet extends HttpServlet
{
	Connection con;
	ResultSet res;
	PreparedStatement pstmt;
	Statement stmt;
	String url="jdbc:mysql://localhost:3306/school";
	String un="root";
	String pwd="Purihere@531";

	@Override
	public void init() throws ServletException {
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
	@Override
	public void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException
	{
		String name=req.getParameter("Username");
		String pass=req.getParameter("Password");
		
		resp.setContentType("text/html");
		PrintWriter wt	=resp.getWriter();

		try {
			String query="select * from studentdetails"+ " where username=? and password=?";
			 pstmt=con.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2,pass);
		 res=pstmt.executeQuery();
			if(res.next()==true)
			{
               wt.println("<h3>Welcome!!"+res.getString(2)+"</h3>");
               //wt.println("<h3>Your Tenth percentage is: "+res.getInt(5)+"</h3>");
              // wt.println("<h3>Your Inter percentage is : "+res.getInt(6)+"</h3>");
               req.getRequestDispatcher("/eligibility").include(req,resp);
			}
			else if(res.next()==false)
			{
				wt.println("Invalid Login.please try again!!");
				req.getRequestDispatcher("invalidlogin.html").include(req, resp);
			}
			else
			{
			   String query1="select * from studentdetails";
			  stmt= con.createStatement();
			 ResultSet res1=stmt.executeQuery(query1);
		
			}
			
			
			
		}
			catch (SQLException e) {

			e.printStackTrace();
		}
	}
	@Override
	public void destroy()
	{
		try
		{
			con.close();
			res.close();
			pstmt.close();
		}
		catch(Exception f)
		{
			f.printStackTrace();
		}

	}
}




