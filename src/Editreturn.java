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

public class Editreturn extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	int row;
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html");
		PrintWriter p = resp.getWriter();
		
		String eid=req.getParameter("id");
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/lba","root","root");
			pst=con.prepareStatement("select * from employee where id=?");
			pst.setString(1, eid);
            rs=pst.executeQuery();
            
            while(rs.next())
            {
            	p.println("<form action='EditServle' method='Post'");
            	
            	
            	p.println("<table>");
            	p.println("<tr>");
                        p.println("<td>Empid </td> <td><input type='text' name='empid' id='empid' value='"+ rs.getString("id")+"'</td>");
            	p.println("</tr>");
            	
            	p.println("<tr>");
            	   p.println("<td>Firstname </td> <td><input type='text' name='fname' id='fname' value='"+ rs.getString("fname")+"'</td>");
            	p.println("</tr>");
            	
            	p.println("<tr>");
         	        p.println("<td>Lastname </td> <td><input type='text' name='lname' id='lname' value='"+ rs.getString("lname")+"'</td>");
         	    p.println("</tr>");
         	    
         	    p.println("<tr><td colspan='2'><input type='submit' value='Edit'></td></tr>");
            	
         	    p.println("</table>");
            	
            	p.println("</form>");
            }
		} 
		catch (Exception e) {
			p.println("<font color='red'> Record fail</font>");
		}

	}

}
