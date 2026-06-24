package com.webserver.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class CollegeEligibilityServlet extends HttpServlet {
	Connection  Stucon=null;
	Connection colcon=null;
	ResultSet res=null;
	PreparedStatement pstmt=null;
	Statement smt=null;
	String url="jdbc:mysql://localhost:3306/school";
	String un="root";
	String pwd="Purihere@531";
	int a;
	@Override
	public void init(ServletConfig sc)throws ServletException
	{
		try
		{
			//studentdetailsDB
			Class.forName("com.mysql.cj.jdbc.Driver");
			Stucon=DriverManager.getConnection(url,un,pwd);
//collegeDB
			colcon=DriverManager.getConnection("jdbc:mysql://localhost:3306/college","root", "Purihere@531");
			System.out.println("Both DB connection established");

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}
	@Override
	
	public void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException
	{

        resp.setContentType("text/html");
        PrintWriter wt = resp.getWriter();
        String username = req.getParameter("Username");
        int rank2 = 0;
		try 
		{

			String query = "SELECT eamcet_rank FROM studentdetails WHERE username = ?";
            PreparedStatement pstmt1 = Stucon.prepareStatement(query);
            pstmt1.setString(1, username);
            ResultSet rs1 = pstmt1.executeQuery();
            if (rs1.next()) {
                rank2 = rs1.getInt("eamcet_rank"); 
            } else {
                wt.println("<h3 style='color:red'>❌ Student not found in DB.</h3>");
                return;
            }

		}
		catch(Exception f)
		{
			f.printStackTrace();
		}
		  wt.println("<html><body>");
	        wt.println("<h2>🎓 College Eligibility for: " + username + "</h2>");
	        wt.println("<p>Your EAPCET rank: <b>" + rank2 + "</b></p><hr>");

	        try 
	        {
	            
	            String query2 = "SELECT college_name, eligibilty_percentage,college_location,eapcet_rank FROM collegeeligibility";
	            Statement smt = colcon.createStatement();
	            ResultSet rs2 = smt.executeQuery(query2);

	            boolean anyEligible = false;
	            while (rs2.next())
	            {
	                String collegeName = rs2.getString("college_name");
	                int    minrank      = rs2.getInt("eapcet_rank");
	                String location    = rs2.getString("college_location");

	                if (rank2 <= minrank) {
	                    // ✅ Eligible
	                	//wt.println("<h3>"+username+" Your are eligible for :</h3");
	                    wt.println("<div style='background:#d4edda; padding:10px; margin:5px; border-radius:5px;'>");
	                    wt.println("✅ <b>" + collegeName + "</b> — " + location);
	                    wt.println("<small> (Min: " + minrank + ")</small></div>");
	                    anyEligible = true;
	                } else {
	                    // ❌ Not eligible
	                    wt.println("<div style='background:#f8d7da; padding:10px; margin:5px; border-radius:5px;'>");
	                    wt.println("❌ <b>" + collegeName + "</b> — " + location);
	                    wt.println("<small> (Min: " + minrank + " — your rank  is less than eligibility rank)</small></div>");
	                }
	            }

	}
	        catch(Exception e)
	        {
	        	e.printStackTrace();
	        }
	}
}

