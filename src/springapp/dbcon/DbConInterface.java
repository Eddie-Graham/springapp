package springapp.dbcon;

import java.sql.ResultSet;

public interface DbConInterface{

	public ResultSet makeConnectionAndRunQuery(String query);

	public void makeConnectionAndExecuteQuery(String query);

	public String makeConnectionAndExecuteQueryGettingAutoId(String query);
}
