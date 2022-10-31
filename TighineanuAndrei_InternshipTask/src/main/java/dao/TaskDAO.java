package dao;

import connection.ConnectionManager;
import model.Task;

import java.sql.*;

public class TaskDAO {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public TaskDAO() {

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

    public void updateUserTasksNumber(String userName){
        int nrOfTasks;
        try{
            String queryString = String.format("Select tasksNumber FROM users WHERE userName='%s'", userName);
            getResultSet(queryString);
            while (resultSet.next()){
                nrOfTasks = resultSet.getInt("tasksNumber");
                queryString = String.format("UPDATE users SET tasksNumber='%d' WHERE userName='%s'", nrOfTasks + 1, userName);
                connection = getConnection();
                preparedStatement = connection.prepareStatement(queryString);
                preparedStatement.execute();
                System.out.println("Nr of tasks updated successfully");
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void addTask(Task task) throws SQLException {
        UserDAO userDAO = new UserDAO();
        if(!userDAO.checkUserName(task.getUserName())){
            System.out.println("Username not found");
            return;
        }
        try{
            String queryString = "INSERT INTO tasks(userName, taskTitle, taskDescription) VALUES(?, ?, ?)";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, task.getUserName());
            preparedStatement.setString(2, task.getTaskTitle());
            preparedStatement.setString(3, task.getTaskDescription());
            preparedStatement.executeUpdate();
            System.out.println("Task Added Successfully");
            updateUserTasksNumber(task.getUserName());


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

    public void showUserTasks(String userName){
        try{
            String queryString = String.format("Select * FROM tasks WHERE userName='%s'",
                    userName);

            getResultSet(queryString);
            if (!resultSet.next()) {
                System.out.println("UserName not found");
                return ;
            }
            System.out.println("User Name" + "\t" + "Task Title" + "\t" + "Task Description");
            while (resultSet.next()){
                System.out.println(resultSet.getString("userName") + "\t" +
                        resultSet.getString("taskTitle") + "\t" +
                        resultSet.getString("taskDescription") + "\n" ) ;
            }

        } catch (SQLException e){
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
