import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class viewemplyee extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	int row;
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html");
		PrintWriter p = resp.getWriter();
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/lba","root","root");
			String sql="select  * from employee";
			Statement stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			
			p.println("<table cellsspacing='0' width='360px' border='1'>");
			p.println("<tr>");
			p.println("<td> Emp id</td>");
			p.println("<td> firstname</td>");
			p.println("<td> lastname</td>");
			p.println("<td> Edit</td>");
			p.println("<td> Delete</td>");
			p.println("</tr>");
			
			
			while(rs.next())
			{
				
				p.println("<tr>");
				p.println("<td>"+ rs.getString("id")+"</td>");
				p.println("<td>"+ rs.getString("fname")+"</td>");
				p.println("<td>"+ rs.getString("lname")+"</td>");
				
	p.println("<td>"+ "<a href='Editruturn?id=" +rs.getString("id") +"'>Edit </a>" +"</td>");
	p.println("<td>"+ "<a href='Delete?id=" +rs.getString("id") +"'>Delete </a>" +"</td>");
		
				
				p.println("</tr>");
				
			}
			p.println("</table>");
			
			
		} 
		catch (Exception e) {
			p.println("<font color='red'> Record fail</font>");
		}

	}

}
