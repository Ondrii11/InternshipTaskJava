package model;

public class User {
    String firstName;
    String lastName;
    String userName;
    int tasksNumber;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getTasksNumber() {
        return tasksNumber;
    }

    public void setTasksNumber(int tasksNumber) {
        this.tasksNumber = tasksNumber;
    }

    public User(){

    }

    public User(String firstName, String lastName, String userName, int tasksNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.tasksNumber = tasksNumber;
    }

}
