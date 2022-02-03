package com.example.authentication.model;

public class Model {
    private String id;
    private String Username;
    private String Email;
    private String Password;

    public Model(){}
    public Model(String Username,String Email,String Password){
        this.Username = Username;
        this.Email = Email;
        this.Password = Password;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setPassword(String password) {
        Password = password;
    }
}
