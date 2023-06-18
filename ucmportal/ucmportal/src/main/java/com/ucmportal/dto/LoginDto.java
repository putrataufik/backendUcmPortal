package com.ucmportal.dto;

public class LoginDto {
    private String name;
    private String password;
    
    public LoginDto(String name, String password) {
        this.name = name;
        this.password = password;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginDto [name=" + name + ", password=" + password + "]";
    }
}
