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
 * Servlet implementation class addCategory
 */
@WebServlet("/addCategory")
public class addCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection connection;
	PreparedStatement ps;
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try
		{
			ServletContext application = getServletContext();
			connection=(Connection) application.getAttribute("globalConnection");
			ps=connection.prepareStatement("insert into category_0053 values (?,?,?,?)");
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
		String tmp = request.getParameter("categoryId");
		int categoryId = Integer.parseInt(tmp);
		String categoryName = request.getParameter("categoryName");
		String categoryDescription = request.getParameter("categoryDescription");
		String categoryImageUrl = request.getParameter("categoryImageUrl");
		try
		{
			ps.clearParameters();
			ps.setInt(1,categoryId);
			ps.setString(2,categoryName);
			ps.setString(3,categoryDescription);
			ps.setString(4,categoryImageUrl);
			ps.executeUpdate();
			out.println("<html>");
			out.println("<body>");
			out.println("<p>Category Added</p>");
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
