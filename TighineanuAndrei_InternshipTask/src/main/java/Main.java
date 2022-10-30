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
                        String firstName = "", lastName = "", userName = "";
                        if(args.length < 4){
                            System.out.println("bad input");
                        }
                        if (args[1].substring(0, 4).equals("-fn=")) {
                            firstName = args[1].substring(4).replace("'", "");
                        }

                        if (args[2].substring(0, 4).equals("-ln=")) {
                            lastName = args[2].substring(4).replace("'", "");
                        }

                        if (args[3].substring(0, 4).equals("-un=")) {
                            userName = args[3].substring(4).replace("'", "");
                        }
                        User userToAdd = new User(firstName, lastName, userName, 0);
                        userDAO.createUser(userToAdd);

                        break;

                    case "-showAllUsers":
                        userDAO.showALlUsers();

                        break;

                    case "-addTask":
                        String userNameTask = "", taskTitle = "", taskDescription = "";
                        if(args.length < 4){
                            System.out.println("bad input");
                        }

                        if(args[1].substring(0, 4).equals("-un=")){
                            userNameTask = args[1].substring(4).replace("'", "");
                        }

                        if(args[2].substring(0,4).equals("-tt=")){
                            taskTitle = args[2].substring(4).replace("'","");
                        }

                        if(args[3].substring(0,4).equals("-td=")){
                            taskDescription = args[3].substring(4).replace("'","");
                        }

                        Task taskToAdd = new Task(userNameTask, taskTitle, taskDescription);
                        taskDAO.addTask(taskToAdd);

                        break;

                    case "-showTasks":
                        String userTaskFind = "";
                        if(args.length < 2){
                            System.out.println("bad input");
                        }

                        if(args[1].substring(0, 4).equals("-un=")){
                            userTaskFind = args[1].substring(4).replace("'","");
                        }

                        taskDAO.showUserTasks(userTaskFind);

                        break;

                    default:
                        System.out.println("Unknown command / bad input. Try again.");

                        break;

                }




    }
}