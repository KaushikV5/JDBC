package com.Basics.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountDAO {

	public static void main(String[] args) {

		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "12345678");
				Statement statement = connection.createStatement();
				ResultSet executeQuery = statement.executeQuery("Select * from account");
		) {
			System.out.println(connection);
//			int executeUpdate = statement.executeUpdate("Insert into account values(1,'Rithik','v',10000000)");
//			System.out.println(executeUpdate);
			while (executeQuery.next()) {
				System.out.println(executeQuery.getString(2));
				System.out.println(executeQuery.getString(3));
				System.out.println(executeQuery.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
