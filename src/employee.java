import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class employee extends HttpServlet {

	private static final long serialVersionUID = 1L;
	Connection con;
	PreparedStatement pst;
	int row;
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html");
		PrintWriter p = resp.getWriter();
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/lba","root","root");
			String empid=req.getParameter("empid");
			String empfname=req.getParameter("fname");
			String emplname=req.getParameter("lname");
			
			pst=con.prepareStatement("insert into employee(id,fname,lname)values(?,?,?) ");
			pst.setString(1, empid);
			pst.setString(2, empfname);
			pst.setString(3, emplname);
			row=pst.executeUpdate();
			
			RequestDispatcher rd=req.getRequestDispatcher("Viewemplyee");
			rd.include(req, resp);
		} 
		catch (Exception e) {
			p.println("<font color='red'> Record fail</font>");
		}

	}
}