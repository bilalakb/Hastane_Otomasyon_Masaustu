package Helper;
import java.sql.*;

public class DBConnection {
	Connection c = null;
	
	public DBConnection() {
	}
	
	public Connection connDb() {
		try {
			this.c = DriverManager.getConnection("jdbc:mariadb://localhost:3306/hospital?user=root&password=akbulak1940");
			return c;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
}