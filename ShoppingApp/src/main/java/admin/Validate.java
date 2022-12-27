package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
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

@WebServlet(loadOnStartup = 100,urlPatterns={"/Validate"})
public class Validate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection connection;
	PreparedStatement ps;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		try
		{
			ps.clearParameters();
			ps.setString(1,userName);
			ps.setString(2,password);
			try (ResultSet result = ps.executeQuery())
			{
				if(result.next())
				{
					response.sendRedirect("welcome.html");
				}
				else {
					out.println("Invalid username/password");
					response.sendRedirect("login.html");
				}
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		} catch (IOException e)
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
			String driverClass = application.getInitParameter("driverClass");
			String url = application.getInitParameter("url");
			String userName = application.getInitParameter("userName");
			String password = application.getInitParameter("password");
			
			Class.forName(driverClass);
			connection=DriverManager.getConnection(url,userName,password);
			application.setAttribute("globalConnection",connection);
			ps=connection.prepareStatement("select * from users_0053 where userName=? and password=?");
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}

	}

}
