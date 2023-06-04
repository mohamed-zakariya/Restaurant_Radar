package com.example.zmrs_project.classes;

import java.sql.SQLException;

public abstract class Person {
    private String username;
    private String password;
//    private  int type;
//    public int getType() {
//        return type;
//    }

    public Person(){

    }
    public Person(String username){
        this.username = username;
    }
    public Person(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public static Person login(String username, String password) throws SQLException, ClassNotFoundException {
        MyJDBC jdbc = MyJDBC.getInstance();

        // 1 -> user && 2 -> Admin && 0 -> notExist
        int type = jdbc.searchUser(username, password);
         if(type == 1)
             return new User(username, password);
         else if (type == 2) {
             return new Admin(username, password);
         }
         return null;
    }
    @Override
    public String toString() {
        return "the Username is: "+getUsername()+"\n"
                +"the password is: "+getPassword()+"\n";
    }
}
