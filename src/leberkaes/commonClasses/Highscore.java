package leberkaes.commonClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TimeZone;

public class Highscore {
	/**
	 * Highscore Schisseln
	 */
	
	private static final String USERNAME = "fhnw_leber";
	private static final String PASSWORD = "RegenWasser2018";
	// Da es eine Maria-DB ist muss der connection string mit der Timezone ein bisschen aufgebläht werden... schiesst sonst...
	private static final String CONN_STRING = "jdbc:mysql://holliger-it.ch:3306/fhnw_majesty?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	
	private Connection makeConnection() {
	    Connection conn = null;
	    try {
	    	TimeZone timeZone = TimeZone.getTimeZone("Europe/Amsterdam"); 
	    	TimeZone.setDefault(timeZone);
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection(CONN_STRING,USERNAME,PASSWORD);
	        System.out.println("Connected to Mysql Server holliger-it.ch");
	    }catch (SQLException | ClassNotFoundException e){
	    	System.err.println(e);
	    }
	    return conn;
	}
	public void getHighscore() {
		Connection conn = makeConnection();
		
		
	}
	public void writeHighscore() {
		
	}
}
