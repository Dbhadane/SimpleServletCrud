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

public class Delete extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	int row;
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html");
		PrintWriter p = resp.getWriter();
		
		
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/lba","root","root");
			
             String empid=req.getParameter("id");
            
             pst=con.prepareStatement("delete from employee where id=?");
            
             pst.setString(1, empid);
             
             int row=pst.executeUpdate();
             p.println("<font color='green'> Record deleted </font>");
             
		} 
		catch (Exception e) {
			p.println("<font color='red'> Record not deleted fail</font>");
		}
}
}
