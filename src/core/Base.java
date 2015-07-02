package core;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Base {
	
	static String user = "root";
	static String pass = "";
	static String host = "jdbc:mysql://localhost:3306/";
	static String dbName = "grupo_6_db";
	
	public static Boolean ExecuteScript(String script)
	{
		Connection conn=null;
		Statement st;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(host + dbName,
					user, pass);
			st = (Statement) conn.createStatement();

			if(st.executeUpdate(script) >0)
			{
				return true;
			}
			else{
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
//		finally
//		{
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		
	}
	
	public static ResultSet ExecuteQuery(String script)
	{
		Connection conn=null;
		Statement st;
		ResultSet rs;
		try {			
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(host + dbName,
					user, pass);
			st = (Statement) conn.createStatement();
			rs = st.executeQuery(script);
			
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
//		finally
//		{
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
	}
}
