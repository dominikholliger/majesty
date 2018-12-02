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
	
	//Highscore Singleton
   private static Highscore instance = new Highscore();
   private Highscore(){}
   public static Highscore getInstance(){
	      return instance;
   }
	
	
	private static final String USERNAME = "fhnw_leber";
	private static final String PASSWORD = "RegenWasser2018";
	// Da es eine Maria-DB ist muss der connection string mit der Timezone ein bisschen aufgebläht werden... schiesst sonst...
	private static final String CONN_STRING = "jdbc:mysql://holliger-it.ch:3306/fhnw_majesty?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	
	
	private Connection conn = null;
	
	private Connection makeConnection() {
		if(this.conn == null) {
			Connection conn = null;
		    try {
		        Class.forName("com.mysql.cj.jdbc.Driver");
		        conn = DriverManager.getConnection(CONN_STRING,USERNAME,PASSWORD);
		        System.out.println("Connected to Mysql Server holliger-it.ch");
		    }catch (SQLException | ClassNotFoundException e){
		    	System.err.println(e);
		    }
		    this.conn = conn;
		} 
		return this.conn;
	}
	public void getHighscore() {
		Connection conn = makeConnection();
		
		
	}
	public void writeHighscore(String name, int count) {
		Connection conn = makeConnection();
		Statement stmt = null;
		try {
			java.util.Date dt = new java.util.Date();
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currentTime = sdf.format(dt);
			stmt = conn.createStatement();
			String sql = "INSERT INTO majesty (name, count, timestamp)" + "VALUES ('"+name+"', '"+count+"', '"+currentTime+"');";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
