package MysqlUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.xdevapi.PreparableStatement;

public class CreateStatement { 
	public static void main(String[] args) {
		CreateStatement createStatement=new CreateStatement();
		//cs.createTable();
		createStatement.show();
		createStatement.update();
		createStatement.delete();
		createStatement.insert("hello","world");
		createStatement.show();
	}
	public void createTable() {
		System.out.println("creating table");
		String sql="create table actorsample(\n"
				+ "actor_id SMALLINT(5) UNSIGNED not null AUTO_INCREMENT,\n"
				+ "first_name varchar(30) not null,\n"
				+ "last_name varchar(30) null,\n"
				+ "last_update TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP on UPDATE CURRENT_TIMESTAMP,\n"
				+ "PRIMARY KEY(actor_id)\n"
				+ ");";
		try {		
			Connection conn= JDBC_Utils.getConnect();
			Statement st = conn.createStatement();		
			st.execute(sql);
			System.out.println("created");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void insert(String first,String last) {
		String sql="insert into actorsample(first_name,last_name) values(?,?) ";
		try {
			PreparedStatement statement=JDBC_Utils.getConnect().prepareStatement(sql);
			statement.setString(1, first);
			statement.setString(2, last);
			int rowInsert=statement.executeUpdate();
			if(rowInsert>0) {
				System.out.println("inserted "+first+" "+last);
			}else {
				System.out.println("can not insert");
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}
	
	public void show() {
		try {
			Connection conn=JDBC_Utils.getConnect();
			Statement st=conn.createStatement();
			ResultSet rs= st.executeQuery("select * from actorsample");
			while(rs.next()) {
				System.out.print(rs.getString(1));
				System.out.print(rs.getString(2));
				System.out.print(rs.getString(3));
				System.out.print(rs.getString(4));
				System.out.println();
				
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}
	public void update() {
		String sql="UPDATE actorsample SET first_name=?, last_name=?  WHERE actor_id=?";
		try {
			Connection con=JDBC_Utils.getConnect();
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setString(1, "john");
			statement.setString(2, "le");
			statement.setString(3, "1");
			int rowUpdate= statement.executeUpdate();
			if(rowUpdate >0) {
				System.out.println("updated");
			}else {
				System.out.println("wrong update info");
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}
	public void  delete() {
		try {
			String sql="delete from actorsample where actor_id=?";
			PreparedStatement statement= JDBC_Utils.getConnect().prepareStatement(sql);
			statement.setString(1, "3");
			int rowDelete=statement.executeUpdate();
			if(rowDelete > 0) {
				System.out.println("deleted");
			}else {
				System.out.println("wrong delete info");
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
		
		
	}
}
