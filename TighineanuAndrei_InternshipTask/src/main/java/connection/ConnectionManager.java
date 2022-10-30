package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionManager {

    private String driverClassName = "oracle.jdbc.OracleDriver";
    private String connectionURL = "jdbc:oracle:thin:@localhost:1521:xe";
    private String username = "STUDENT";
    private String password = "STUDENT";

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
        Connection connection = null;
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
