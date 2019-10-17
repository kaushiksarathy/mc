package model;


public class User {
    private String id;
    private String userName;
    private String role;


    public String getRole() {
        return role;
    }


    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }


    public User(String id, String userName, String role) {
        this.id = id;
        this.userName = userName;
        this.role = role;
    }
}
