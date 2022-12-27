package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class showProducts
 */
@WebServlet("/showProducts")
public class showProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	Connection connection;
	PreparedStatement ps;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<a href='/ShoppingApp/welcome.html'>Back To Home Page</a><br/><br/>");
		out.println("<table border='1'>");
		out.println("<tr>");
		out.println("<th>Product Name</th>");
		out.println("<th>Product Description</th>");
		out.println("<th>Image</th>");
		out.println("<th>Price</th>");
		out.println("<tr>");
		
				try
				{
					
					try (ResultSet result = ps.executeQuery())
					{
						while(result.next())
						{
							out.println("<tr>");
							out.println("<td>"+result.getString("productName") + "</td>");
							out.println("<td>"+result.getString("productDescription")+"</td>");
							out.println("<td><img src='Images/"+result.getString("productImageUrl")+"' height='120px' width='120px'/></td>");
							out.println("<td>"+result.getString("productPrice")+"</td>");
							out.println("</tr>");
						}
						out.println("</table>");
						out.println("</body>");
						out.println("</html>");
					} catch (SQLException e)
					{
						e.printStackTrace();
					}
				} catch (Exception e)
				{
					e.printStackTrace();
				}
	}
	@Override
	public void destroy()
	{
		try
		{
			if(ps!=null)
				ps.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	@Override
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		try
		{
			ServletContext application = getServletContext();
			connection=(Connection) application.getAttribute("globalConnection");
			ps=connection.prepareStatement("select * from products_0053");
		} catch (SQLException e)
		{
			e.printStackTrace();
		}

	}

}
