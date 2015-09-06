package springapp.dbcon;

import java.sql.Connection;

public interface DbCon {
	
	public static final String DRIVER_CLASS_NAME = "org.postgresql.Driver";
	public static final String HOST = "jdbc:postgresql://localhost:5432/";
	public static final String DB_NAME = "postgres";
	public static final String USERNAME = "postgres";
	public static final String PASSWORD = "Password123";
}
