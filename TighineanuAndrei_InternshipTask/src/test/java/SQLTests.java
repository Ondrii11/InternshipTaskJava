import connection.ConnectionManager;
import dao.UserDAO;
import model.User;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SQLTests {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = ConnectionManager.getInstance().getConnection();
        return conn;
    }

    @Test
    public void createUser() throws SQLException {
        UserDAO userDAO = new UserDAO();
        connection = getConnection();
        connection.setAutoCommit(false);

        preparedStatement.executeQuery("DELETE FROM users");

        String firstName = "test";
        String lastName = "test";
        String userName = "test";
        int tasksNumber = 0;

        User user = new User(firstName, lastName, userName, tasksNumber);

        assertEquals(firstName, user.getFirstName());
        assertEquals(lastName, user.getLastName());
        assertEquals(userName, user.getUserName());
        assertEquals(tasksNumber, user.getTasksNumber());


        try{
            String queryString = "INSERT INTO users(firstName, lastName, userName, tasksNumber) VALUES(?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUserName());
            preparedStatement.setInt(4, user.getTasksNumber());
            preparedStatement.executeUpdate();

            preparedStatement.executeQuery("SELECT * from USERS");
            assertTrue(resultSet.next());

            assertEquals(firstName, resultSet.getString("userName"));
            assertEquals(lastName, resultSet.getString("lastName"));
            assertEquals(userName, resultSet.getString("userName"));
            assertEquals(tasksNumber, resultSet.getInt("tasksNumber"));
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            connection.rollback();
        }

    }
}
