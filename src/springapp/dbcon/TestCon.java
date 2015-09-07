package springapp.dbcon;

import java.sql.Connection;
import java.sql.DriverManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class TestCon {
	
	public static void testCon() {

		try {

			Class.forName(DbCon.DRIVER_CLASS_NAME);

			Connection c = DriverManager.getConnection(DbCon.HOST + DbCon.DB_NAME,
					           						   DbCon.USERNAME,
					           						   DbCon.PASSWORD);
			
//			String query = "select * from test";			
//			Statement stmt = c.createStatement();
//			ResultSet rs = stmt.executeQuery(query);
//			
//			while (rs.next()) {
//	            String test = rs.getString("text");
//	            System.out.println(test);
//	        }			

			System.out.println("DB connected");

		} catch (Exception e) {

			e.printStackTrace();

		}

	}
	
	public static void testCon2() {

		try {

			DriverManagerDataSource dataSource = new DriverManagerDataSource();
		    dataSource.setDriverClassName(DbCon.DRIVER_CLASS_NAME);
		    dataSource.setUrl(DbCon.HOST + DbCon.DB_NAME);
		    dataSource.setUsername(DbCon.USERNAME);
		    dataSource.setPassword(DbCon.PASSWORD);			
		    
		    System.out.println("DB connected");

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}
