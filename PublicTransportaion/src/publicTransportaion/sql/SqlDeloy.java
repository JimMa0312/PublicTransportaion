package publicTransportaion.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import publicTransportaion.model.DBConfig;
import publicTransportaion.safety.SefDES;
import publicTransportaion.safety.SefRSA;

public class SqlDeloy {
	private String DRIVERNAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private String DBURL = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=";
	private Connection connection = null;

	public SqlDeloy() throws SQLException, ClassNotFoundException {
		Class.forName(DRIVERNAME);
		String factURL = DBURL + DBConfig.getDBname();
		connection = DriverManager.getConnection(factURL, DBConfig.getUserName(), DBConfig.getPWD());
		System.out.println("Connected");
	}

	public void shotDownCon() {
		try {
			connection.close();
			System.out.println("ShotDown!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}
}
