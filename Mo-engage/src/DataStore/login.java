package DataStore;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class login extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String urn=req.getParameter("un");
		String pwd=req.getParameter("ps");
		String fqcn="com.mysql.cj.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306?user=root&password=root";
        String Qs="select * from brew.users";
		
        try {
			Class.forName(fqcn);
			Connection con=DriverManager.getConnection(url);
			Statement stmt=con.createStatement();
			PreparedStatement pstmts=con.prepareStatement(Qs);
			ResultSet rss=pstmts.executeQuery();

			for (int i = 0; i < 1; i++) {
				while (rss.next()) {// for every table change the condition
					if (rss.getString(1).equals(urn)&&rss.getString(2).equals(pwd)) {
						System.out.print(rss.getString(2)+" ");
						HttpSession ssn=req.getSession(true);
						ssn.putValue("username", urn);
						resp.sendRedirect(req.getContextPath()+"/browse.html");
//						RequestDispatcher rd=req.getRequestDispatcher("browse.html");
//						rd.forward(req, resp);
					}
				}
				RequestDispatcher rd=req.getRequestDispatcher("err.html");
				rd.include(req, resp);
				break;
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
