package queryapp;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class SQLQueries
{
	public void Insert(String uName, String password1, String name, String email) 
	{
		try 
		{
			Properties props = new Properties();
			props.load(new FileInputStream("database.properties"));
			String driverClassName =props.getProperty("db.driverName");
			String url = props.getProperty("db.url");
			String userName = props.getProperty("db.userName");
			String password = props.getProperty("db.password");		
			Class.forName(driverClassName);

		try(Connection connection = DriverManager.getConnection(url,userName,password);
				Statement stSelect = connection.createStatement();
				PreparedStatement ps=connection.prepareStatement("insert into users_0053 values(?,?,?,?)"))
		{
			ps.clearParameters();
			ps.setString(1, uName);
			ps.setString(2, password1);
			ps.setString(3, name);
			ps.setString(4, email);
			ps.executeUpdate();
		}
		catch(SQLException sql)
		{
			sql.printStackTrace();
		}
		}
		catch(ClassNotFoundException | IOException e)
		{
			e.printStackTrace();
		}
	}
	public void delete(String uName) {
		try 
		{
			Properties props = new Properties();
			props.load(new FileInputStream("database.properties"));
			String driverClassName =props.getProperty("db.driverName");
			String url = props.getProperty("db.url");
			String userName = props.getProperty("db.userName");
			String password = props.getProperty("db.password");		
			Class.forName(driverClassName);

		try(Connection connection = DriverManager.getConnection(url,userName,password);
				Statement stSelect = connection.createStatement();
				PreparedStatement ps=connection.prepareStatement("delete from users_0053 where username=?");
						)
		{	
			ps.clearParameters();
			ps.setString(1, uName);
			ps.executeUpdate();
		}
		catch(SQLException sql)
		{
			sql.printStackTrace();
		}
		}
		catch(ClassNotFoundException | IOException e)
		{
			e.printStackTrace();
		}
	}
	public void changePassword(String uNamePassword, String password2) {
		try 
		{
			Properties props = new Properties();
			props.load(new FileInputStream("database.properties"));
			String driverClassName =props.getProperty("db.driverName");
			String url = props.getProperty("db.url");
			String userName = props.getProperty("db.userName");
			String password = props.getProperty("db.password");		
			Class.forName(driverClassName);

		try(Connection connection = DriverManager.getConnection(url,userName,password);
				Statement stSelect = connection.createStatement();
				PreparedStatement ps=connection.prepareStatement("update users_0053 set password=? where username=?");
						)
		{
			ps.clearParameters();
			ps.setString(1, password2);
			ps.setString(2, uNamePassword);
			ps.executeUpdate();
		}
		catch(SQLException sql)
		{
			sql.printStackTrace();
		}
		}
		catch(ClassNotFoundException | IOException e)
		{
			e.printStackTrace();
		}
	}
	public void showDetails(String uNameShow) {
		try {
			Properties props = new Properties();
			props.load(new FileInputStream("database.properties"));
			String driverClassName =props.getProperty("db.driverName");
			String url = props.getProperty("db.url");
			String userName = props.getProperty("db.userName");
			String password = props.getProperty("db.password");		
			Class.forName(driverClassName);
		try(Connection connection = DriverManager.getConnection(url,userName,password);
				Statement stSelect = connection.createStatement();
				ResultSet result = stSelect.executeQuery("select * from users_0053 where userName='"+uNameShow+"'"))
		{
			while(result.next())
			{
				System.out.println(result.getString("username"));
				System.out.println(result.getString("password"));
				System.out.println(result.getString(3));
				System.out.println(result.getString(4));
			}
		}
		catch(SQLException sql)
		{
			sql.printStackTrace();
		}
		}
		catch(ClassNotFoundException | IOException e)
		{
			e.printStackTrace();
		}
		
	}
	public void updateDetails(String oldUName, String uNameNew, String passwordNew, String nameNew, String emailNew) {
		try 
		{
			Properties props = new Properties();
			props.load(new FileInputStream("database.properties"));
			String driverClassName =props.getProperty("db.driverName");
			String url = props.getProperty("db.url");
			String userName = props.getProperty("db.userName");
			String password = props.getProperty("db.password");		
			Class.forName(driverClassName);

		try(Connection connection = DriverManager.getConnection(url,userName,password);
				Statement stSelect = connection.createStatement();
				PreparedStatement ps=connection.prepareStatement("update users_0053 set username=?,password=?,name=?,email=? where username=?"))
		{
			
			
			ps.clearParameters();
			ps.setString(1, uNameNew);
			ps.setString(2, passwordNew);
			ps.setString(3, nameNew);
			ps.setString(4, emailNew);
			ps.setString(5, oldUName);
			ps.executeUpdate();
		}
		catch(SQLException sql)
		{
			sql.printStackTrace();
		}
		}
		catch(ClassNotFoundException | IOException e)
		{
			e.printStackTrace();
		}
	}
}
