import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	int row;
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html");
		PrintWriter p = resp.getWriter();
		
		String eid=req.getParameter("id");
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/lba","root","root");
			
             String empid=req.getParameter("empid");
             String fname=req.getParameter("fname");
             String lname=req.getParameter("lname");
             
             pst=con.prepareStatement("update employee set fname=? ,lname=? where id=?");
             pst.setString(1, fname);
             pst.setString(2, lname);
             pst.setString(3, empid);
             
             int row=pst.executeUpdate();
             p.println("<font color='green'> Record updated </font>");
             
		} 
		catch (Exception e) {
			//p.println("<font color='red'> Record updation fail</font>");
			e.printStackTrace();
		}
}
}