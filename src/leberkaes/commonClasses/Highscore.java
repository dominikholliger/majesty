package leberkaes.commonClasses;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.TimeZone;

public class Highscore {
	/**
	 * Highscore Schisseln
	 */

	// Highscore Singleton
	private static Highscore instance = new Highscore();

	private Highscore() {
	}

	public static Highscore getInstance() {
		return instance;
	}

	private static final String USERNAME = "fhnw_leber";
	private static final String PASSWORD = "RegenWasser2018";
	// Da es eine Maria-DB ist muss der connection string mit der Timezone ein
	// bisschen aufgebläht werden... schiesst sonst...
	private static final String CONN_STRING = "jdbc:mysql://holliger-it.ch:3306/fhnw_majesty?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

	private Connection conn = null;

	private Connection makeConnection() {
		if (this.conn == null) {
			Connection conn = null;
			if (connectionTest()) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
					System.out.println("Connected to Mysql Server holliger-it.ch");
				} catch (SQLException | ClassNotFoundException e) {
					System.err.println(e);
				}
			}
			this.conn = conn;
		}
		return this.conn;
	}

	private boolean connectionTest() {
		boolean isAlive = false;
		SocketAddress socketAddress = new InetSocketAddress("holliger-it.ch", 3306);
		Socket socket = new Socket();
		int timeout = 2000;
		try {
			socket.connect(socketAddress, timeout);
			socket.close();
			isAlive = true;
		} catch (SocketTimeoutException exception) {
			System.out.println("Verbindungstimeout mit Highscore Server...");
		} catch (IOException exception) {
			System.out.println("IOException bei Verbinden mit dem Highscore Server");
		}
		return isAlive;
	}

	public HashMap getHighscore() {
		HashMap<Integer, String> hmap = new HashMap<Integer, String>();
		Connection conn = makeConnection();
		try {
			Statement stmt = null;
			String sql = "SELECT * FROM `majesty` order by count desc, timestamp asc LIMIT 100;";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int count = rs.getInt("count");
				String name = rs.getString("name");
				java.util.Date datetime = new java.util.Date();
				datetime = rs.getTime("timestamp");
				java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd.MM.yyyy HH:mm");
				String dateTimeofEntry = sdf.format(datetime);
				hmap.put(count, name + " - " + dateTimeofEntry);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hmap;
	}

	public void writeHighscore(String name, int count) {
		Connection conn = makeConnection();
		Statement stmt = null;
		try {
			java.util.Date dt = new java.util.Date();
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currentTime = sdf.format(dt);
			stmt = conn.createStatement();
			String sql = "INSERT INTO majesty (name, count, timestamp)" + "VALUES ('" + name + "', '" + count + "', '"
					+ currentTime + "');";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
