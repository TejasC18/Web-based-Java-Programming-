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
 * Servlet implementation class addProduct
 */
@WebServlet("/addProduct")
public class addProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection connection;
	PreparedStatement ps;
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try
		{
			ServletContext application = getServletContext();
			connection=(Connection) application.getAttribute("globalConnection");
			ps=connection.prepareStatement("insert into products_0053 values (?,?,?,?,?,?)");
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
		String tmp = request.getParameter("productId");
		int productId = Integer.parseInt(tmp);
		String tmp1 = request.getParameter("categoryId");
		int categoryId = Integer.parseInt(tmp1);
		String productName = request.getParameter("productName");
		String productDescription = request.getParameter("productDescription");
		String productImageUrl = request.getParameter("productImageUrl");
		String tmp2 = request.getParameter("productPrice");
		float productPrice = Float.parseFloat(tmp2);
		try
		{
			ps.clearParameters();
			ps.setInt(1,productId);
			ps.setString(2,productName);
			ps.setString(3,productDescription);
			ps.setString(4,productImageUrl);
			ps.setFloat(5, productPrice);
			ps.setInt(6,categoryId);
			ps.executeUpdate();
			out.println("<html>");
			out.println("<body>");
			out.println("<p>Product Added</p>");
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
