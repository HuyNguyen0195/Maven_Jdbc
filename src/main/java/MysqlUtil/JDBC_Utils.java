package MysqlUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC_Utils {
	static String url="jdbc:mysql://localhost:3306/jdbc_demos";
	static String username="root";
	static String password="Khang!23";
	public static Connection getConnect() {
		Connection conn=null;
		try {
			conn=DriverManager.getConnection(url,username,password);
			System.out.println("connected");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
		
	}
}
