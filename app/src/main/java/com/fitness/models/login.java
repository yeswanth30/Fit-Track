package com.fitness.models;

public class login {
    private String  username;
    private String  password;

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


    // constructor
    public login(String username,
                       String password)
    {
        this.username = username;
        this.password = password;

    }
}
