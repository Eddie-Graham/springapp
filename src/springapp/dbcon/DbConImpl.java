package springapp.dbcon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbConImpl implements DbCon {

	public static ResultSet makeConnectionAndRunQuery(String query){
		
		ResultSet rs = null;
		
		try {

			Class.forName(DRIVER_CLASS_NAME);

			Connection c = DriverManager.getConnection(URL, USERNAME, PASSWORD);
						
			Statement stmt = c.createStatement();
			
			System.out.println("Executing query: " + query);
			
			rs = stmt.executeQuery(query);	
			
			c.close();

		} catch (Exception e) {

			e.printStackTrace();

		}
		
		return rs;
		
	}
}
