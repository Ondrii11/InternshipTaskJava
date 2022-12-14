package dao;

import connection.ConnectionManager;
import model.User;

import java.sql.*;


public class UserDAO {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public UserDAO() {

    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = ConnectionManager.getInstance().getConnection();
        return conn;
    }

    private void getResultSet(String queryString) throws SQLException {
        connection = getConnection();
        preparedStatement = connection.prepareStatement(queryString);
        resultSet = preparedStatement.executeQuery();
    }
    public void createUser(User user) {
        if(user.getFirstName().length() > 12 || user.getLastName().length() > 12 || user.getUserName().length() > 20 ||
           user.getTasksNumber() > 0) {
            System.out.println("FN < 12 chars, LN < 12 chars, UN < 20chars, tasksNR must be init with 0.");
            return;
        }
        try {
            String queryString = "INSERT INTO users(firstName, lastName, userName, tasksNumber) VALUES(?, ?, ?, ?)";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUserName());
            preparedStatement.setInt(4, user.getTasksNumber());
            preparedStatement.executeUpdate();
            System.out.println("Data added successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean checkUserName(String userName) {
        boolean sameUserName = false;

        try {
            String queryString = "SELECT * FROM users";
            getResultSet(queryString);

            while (resultSet.next()) {

                if (resultSet.getString("userName").equals(userName)) {
                    sameUserName = true;
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            freeResources();
        }

        return sameUserName;

    }

    public void showALlUsers() {

        try {
            String queryString = "SELECT * FROM users";
            getResultSet(queryString);

            System.out.println("First Name" + "\t" + "Last Name" + "\t" + "User Name " + "\t" + "Nr of Tasks");
            while (resultSet.next()) {
                System.out.println("" + resultSet.getString("firstName") + "\t" +
                        resultSet.getString("lastName") + "\t" + resultSet.getString("userName")
                        + "\t" + resultSet.getInt("tasksNumber"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            freeResources();
        }
    }

    private void freeResources() {

        try {
            if (resultSet != null)
                resultSet.close();
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
