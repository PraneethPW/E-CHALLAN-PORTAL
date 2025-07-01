package in.praneeth.DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChallanDB {
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnectionDB() throws SQLException {
		String url="jdbc:mysql://localhost:3306/challanportal";
		String username="root";
		String password="Pr@120502";
		return DriverManager.getConnection(url,username,password);
	}

	public void CloseResources(ResultSet resultset,Connection connection,PreparedStatement statement) throws SQLException {
		if(resultset!=null) {
			resultset.close();
		}else if(connection!=null) {
			connection.close();
			}else {
				statement.close();
			}
	}
}
