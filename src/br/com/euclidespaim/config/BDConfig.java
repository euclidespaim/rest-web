package br.com.euclidespaim.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDConfig {

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://datascience.c1hfylqrxabo.us-east-1.rds.amazonaws.com:3306/pacs_db", "euclidespaim", "capaiotadelta");
		}
}
