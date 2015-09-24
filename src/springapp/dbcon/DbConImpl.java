package springapp.dbcon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbConImpl implements DbCon {

	/**
	 * Run a query for which results are expected.
	 * @param query
	 * @return
	 */
	public static ResultSet makeConnectionAndRunQuery(String query){
		
		ResultSet rs = null;
		
		try {

			Class.forName(DRIVER_CLASS_NAME);

			Connection c = DriverManager.getConnection(URL, USERNAME, PASSWORD);
						
			Statement stmt = c.createStatement();
			
			System.out.println("Running query: " + query);
			
			rs = stmt.executeQuery(query);	
			
			c.close();

		} catch (Exception e) {

			e.printStackTrace();

		}
		
		return rs;
		
	}
	
	/**
	 * Execute query which does not return a result.
	 * @param query
	 */
	public static void makeConnectionAndExecuteQuery(String query){
		
		try {

			Class.forName(DRIVER_CLASS_NAME);

			Connection c = DriverManager.getConnection(URL, USERNAME, PASSWORD);
						
			Statement stmt = c.createStatement();
			
			System.out.println("Executing query: " + query);
			
			stmt.execute(query);	
			
			c.close();

		} catch (Exception e) {

			e.printStackTrace();

		}
	}
}
