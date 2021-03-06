package ca.ubc.cs304.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class handles all database related transactions
 */
public class DatabaseConnectionHandler {
	// Use this version of the ORACLE_URL if you are running the code off of the server
	// private static final String ORACLE_URL = "jdbc:oracle:thin:@dbhost.students.cs.ubc.ca:1522:stu";
	// Use this version of the ORACLE_URL if you are tunneling into the undergrad servers
	private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
	private static final String EXCEPTION_TAG = "[EXCEPTION]";

    private Connection connection;

	// These are the handlers for each Models
	// We chose to modulate for organization sake
	public PublicAreaHandler publicAreaHandler;
	public BookableHandler bookableHandler;
	public MemberHandler memberHandler;
	public AccessHandler accessHandler;
	public BranchHandler branchHandler;
	public BookingHandler bookingHandler;
	public ReserveHandler reserveHandler;
	public EventHandler eventHandler;
	public EmployeeHandler employeeHandler;

	public DatabaseConnectionHandler() {
		try {
			// Load the Oracle JDBC driver
			// Note that the path could change for new drivers
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
	}
	
	public void close() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
	}


	public boolean login(String username, String password) {
		try {
			if (connection != null) {
				connection.close();
			}
	
			connection = DriverManager.getConnection(ORACLE_URL, username, password);
			connection.setAutoCommit(false);

			// These are the handlers for each Models
			// We chose to modulate for organization sake
			publicAreaHandler = new PublicAreaHandler(connection);
			bookableHandler = new BookableHandler(connection);
			memberHandler = new MemberHandler(connection);
			accessHandler = new AccessHandler(connection);
			branchHandler = new BranchHandler(connection);
			bookingHandler = new BookingHandler(connection);
			reserveHandler = new ReserveHandler(connection);
			eventHandler = new EventHandler(connection);
			employeeHandler = new EmployeeHandler(connection);
	
			System.out.println("\nConnected to Oracle!");
			return true;
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " f " + e.getMessage());
			return false;
		}
	}
}
