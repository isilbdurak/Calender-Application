package com.example.calenderproject_202802039;

public class UserData {
    private static User user;

    public static User getUser() {
        return user;
    }

    public static void setUser(User newUser) {
        user = newUser;
    }
}
