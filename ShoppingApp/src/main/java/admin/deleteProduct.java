package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class deleteProduct
 */
@WebServlet("/deleteProduct")
public class deleteProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Connection connection;
	PreparedStatement ps;
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try
		{
			ServletContext application = getServletContext();
			connection=(Connection) application.getAttribute("globalConnection");
			ps=connection.prepareStatement("DELETE FROM products_0053 WHERE productName = ?");
		}catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	public void destroy() {
		try
		{
			if(ps!=null)
				ps.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String productName = request.getParameter("productName");
		try
		{
			ps.clearParameters();
			ps.setString(1,productName);
			ps.executeUpdate();
			out.println("<html>");
			out.println("<body>");
			out.println("<p>Product deleted</p>");
			out.println("<a href='welcome.html'>Back To Home</a>");
			out.println("</body>");
			out.println("</html>");
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
