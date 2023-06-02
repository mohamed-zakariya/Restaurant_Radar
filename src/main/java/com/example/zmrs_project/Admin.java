package com.example.zmrs_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class Admin extends Person{
    private ArrayList<Location> locationResturants = new ArrayList<Location>();
    private ArrayList<Restaurant> RestaurantArrayList= new ArrayList<Restaurant>();




    public Admin(){

    }


    public Admin(String username,String password){
        super(username,password);
    }

    public  void  AddResturant(String restaurantName ,String location, String cusine,String phone) throws SQLException, ClassNotFoundException {
        MyJDBC jdbc = MyJDBC.getInstance();
        jdbc.insertRestaruants(restaurantName, location,cusine,phone);




    }

   /* public void update(String newAddress)throws SQLException, ClassNotFoundException{
       //by removing one location and changed it to another location
       MyJDBC jdbc= MyJDBC.getInstance();
       jdbc.UpdateOnelocationByremoveingTheAnother(newAddress);

    }
    public void addMoreLocation(Restaurant restaurant,Location newlocation)throws SQLException,ClassNotFoundException{
       MyJDBC jdbc= MyJDBC.getInstance();
       jdbc.addMoreLocation(restaurant,newlocation);

    }
    public void Remove(int id)throws SQLException,ClassNotFoundException{
       MyJDBC jdbc= MyJDBC.getInstance();


    }

*/
}