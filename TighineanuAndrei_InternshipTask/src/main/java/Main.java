import dao.TaskDAO;
import dao.UserDAO;
import model.User;
import model.Task;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        UserDAO userDAO = new UserDAO();
        TaskDAO taskDAO = new TaskDAO();

                switch(args[0]) {
                    case "-createUser":
                        String firstName , lastName , userName;
                        if(args.length < 4){
                            System.out.println("bad input");
                        }
                        if (args[1].startsWith("-fn=") && args[2].startsWith("-ln=") &&
                                args[3].startsWith("-un=")) {
                            firstName = args[1].substring(4).replace("'", "");
                            lastName = args[2].substring(4).replace("'", "");
                            userName = args[3].substring(4).replace("'", "");
                        } else {
                            System.out.println("bad input, retry.");
                            break;
                        }

                        if(!userDAO.checkUserName(userName)) {
                            User userToAdd = new User(firstName, lastName, userName, 0);
                            userDAO.createUser(userToAdd);
                        } else {
                            System.out.println("Username already taken.");
                        }

                        break;

                    case "-showAllUsers":
                        userDAO.showALlUsers();

                        break;

                    case "-addTask":
                        String userNameTask, taskTitle, taskDescription;
                        if(args.length < 4){
                            System.out.println("bad input");
                        }

                        if(args[1].startsWith("-un=") && args[2].startsWith("-tt=") &&
                                args[3].startsWith("-td=")){

                            userNameTask = args[1].substring(4).replace("'", "");
                            taskTitle = args[2].substring(4).replace("'","");
                            taskDescription = args[3].substring(4).replace("'","");
                        } else {
                            System.out.println("bad input, retry.");
                            break;
                        }

                        Task taskToAdd = new Task(userNameTask, taskTitle, taskDescription);
                        taskDAO.addTask(taskToAdd);

                        break;

                    case "-showTasks":
                        String userTaskFind;
                        if(args.length < 2){
                            System.out.println("bad input");
                        }

                        if(args[1].startsWith("-un=")){
                            userTaskFind = args[1].substring(4).replace("'","");
                        } else {
                            System.out.println("bad input, retry.");
                            break;
                        }

                        taskDAO.showUserTasks(userTaskFind);

                        break;

                    default:
                        System.out.println("Unknown command / bad input. Try again.");

                        break;

                }

    }

}