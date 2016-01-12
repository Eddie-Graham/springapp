package springapp.dbcon.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import springapp.dbcon.DbConInterface;

@Component
public class DbCon implements DbConInterface{

	@Autowired
	private DataSource dataSource;

	/**
	 * Run a query for which results are expected.
	 *
	 * @param query
	 * @return
	 */
	public ResultSet makeConnectionAndRunQuery(String query){

		ResultSet rs = null;

		try{

			Connection c = dataSource.getConnection();

			Statement stmt = c.createStatement();

			System.out.println("Running query: " + query);

			rs = stmt.executeQuery(query);

			c.close();

		} catch(Exception e){

			e.printStackTrace();

		}

		return rs;

	}

	/**
	 * Execute query which does not return a result.
	 *
	 * @param query
	 */
	public void makeConnectionAndExecuteQuery(String query){

		try{

			Connection c = dataSource.getConnection();

			Statement stmt = c.createStatement();

			System.out.println("Executing query: " + query);

			stmt.execute(query);

			c.close();

		} catch(Exception e){

			e.printStackTrace();

		}
	}

	/**
	 * Execute query and return auto generated id field
	 *
	 * @param query
	 */
	public String makeConnectionAndExecuteQueryGettingAutoId(String query){

		String autoId = "";

		try{

			Connection c = dataSource.getConnection();

			Statement stmt = c.createStatement();

			System.out.println("Executing query: " + query);

			stmt.execute(query, Statement.RETURN_GENERATED_KEYS);

			ResultSet rs = stmt.getGeneratedKeys();

			rs.next();

			autoId = rs.getString("id");

			c.close();

		} catch(Exception e){

			e.printStackTrace();

		}

		return autoId;
	}
}
