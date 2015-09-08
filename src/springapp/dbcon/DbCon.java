package springapp.dbcon;

public interface DbCon {
	
	public static final String DRIVER_CLASS_NAME = "org.postgresql.Driver";
	public static final String URL =  System.getenv("OPENSHIFT_POSTGRESQL_DB_MYURL");
	public static final String USERNAME = System.getenv("OPENSHIFT_POSTGRESQL_DB_USERNAME");
	public static final String PASSWORD = System.getenv("OPENSHIFT_POSTGRESQL_DB_PASSWORD");	
}
