package com.example.calenderproject_202802039;

public class User {
    private User user;
    private String username;
    private String password;
    private int id;

    public void setUser(User user) {
        this.user = user;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String userid, String nameSurname, String tcNo, String phoneNumber, String mail, String username, String password, String usertype) {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
