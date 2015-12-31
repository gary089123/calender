package project_calender;

import java.sql.*;

import javax.swing.*;

public class connect {
	private static String connect;
	private static Connection connection;
	private static Statement command;
	
	public void connect(String ip , String port ,String username,String password){
		connect="jdbc:mysql://"+"140.115.200.7"+":"+port;
		try {
			connection = DriverManager.getConnection(connect,"gary089123","GARY24836100");
			command=connection.createStatement();
			 
			try{
				command.executeUpdate("USE calender");
			}
			catch(Exception e){
				command.executeUpdate("CREATE DATABASE calender");
				command.executeUpdate("USE calender");
				command.executeUpdate("CREATE TABLE user"
						+ "(id int NOT NULL AUTO_INCREMENT,"
						+ "username varchar(20) NOT NULL,"
						+ "password varchar(20) NOT NULL,"
						+ "email varchar(30),"
						+ "PRIMARY KEY (ID))");
				command.executeUpdate("USE calender");
			}
			System.out.println("Success");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "請確認伺服器資訊與狀態", "連線錯誤", JOptionPane.ERROR_MESSAGE );
			e.printStackTrace();
		}
	}
	public void register(String username, String password, String email){
		try{
			command.executeUpdate("INSERT INTO user"
					+ "(username , password , email) "
					+ "VALUES ("+username+" ," +password+" , "+email+")");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void login(){
		
	}
}
