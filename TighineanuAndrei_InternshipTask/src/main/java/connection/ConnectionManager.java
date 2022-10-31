package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionManager {

    private final String driverClassName = "oracle.jdbc.OracleDriver";
    private final String connectionURL = "jdbc:oracle:thin:@localhost:1521:xe";
    private final String username = "STUDENT";
    private final String password = "STUDENT";

    private static ConnectionManager connectionManager = null;

    //singleton class with private constructor
    private ConnectionManager() {
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        Connection connection;
        connection = DriverManager.getConnection(connectionURL, username, password);
        return connection;
    }

    //static method that returns the ConnectionManager(singleton) type object
    public static ConnectionManager getInstance() {
        if (connectionManager == null) {
            connectionManager = new ConnectionManager();
        }
        return connectionManager;
    }

}
