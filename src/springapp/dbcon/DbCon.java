package springapp.dbcon;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DbCon{
	
	@Autowired
	private DataSource dataSource;

	/**
	 * Run a query for which results are expected.
	 * @param query
	 * @return
	 */
	public ResultSet makeConnectionAndRunQuery(String query){
		
		ResultSet rs = null;
		
		try {
			
			Connection c = dataSource.getConnection();

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
	public void makeConnectionAndExecuteQuery(String query){
		
		try {

			Connection c = dataSource.getConnection();
						
			Statement stmt = c.createStatement();
			
			System.out.println("Executing query: " + query);
			
			stmt.execute(query);	
			
			c.close();

		} catch (Exception e) {

			e.printStackTrace();

		}
	}
}
