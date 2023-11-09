package DataStore;

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class insertu extends HttpServlet {
	protected void doPost(HttpServletRequest req,HttpServletResponse resp )
			throws ServletException,IOException{
		
		String carno = req.getParameter("un");
		String cname = req.getParameter("pass");
		String sn=req.getParameter("submit");
		
		
		 String c="";
		String fcqn="com.mysql.cj.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306?user=root&password=root";
		String Q1="insert into brew.users values(?,?)";
		PrintWriter ref = resp.getWriter();
		try {
			Class.forName(fcqn);
			Connection con=DriverManager.getConnection(url);
			
			
			if (carno.equals(c)&&cname.equals(c)) {
				System.out.println("lll");
				ref.print("<h1 style=\"color: red;\"> Fill all the Spaces!!!!!!</h1>");
				RequestDispatcher rd=req.getRequestDispatcher("./createAcc.html");
				rd.include(req, resp);
			} else {
				System.out.println("hhh");
				PreparedStatement pstmt=con.prepareStatement(Q1);
				pstmt.setString(1,carno);
				pstmt.setString(2,cname);
				pstmt.executeUpdate();
				RequestDispatcher rd=req.getRequestDispatcher("login.html");
				rd.forward(req, resp);
			}
		}catch (Exception e) {
			ref.print("<h1 style=\"color: red;\"> Username Already exist!!!!!!</h1>");
			RequestDispatcher rd=req.getRequestDispatcher("./createAcc.html");
			rd.include(req, resp);
			System.out.println("Bye....");
			e.printStackTrace();
		}
	}
	
}
