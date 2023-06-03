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

    public static  void   AddResturant(Restaurant restaurant) throws SQLException, ClassNotFoundException {
        MyJDBC jdbc = MyJDBC.getInstance();

        jdbc.insertRestaruants( restaurant);




    }
    public  Restaurant  GetestaurantData(String restaurantName) throws SQLException, ClassNotFoundException {
            MyJDBC jdbc = MyJDBC.getInstance();
             return jdbc.getRestaurantData(restaurantName);

    }

    public  void UpdateRestaurant(Restaurant restaurant) throws SQLException, ClassNotFoundException {
        MyJDBC jdbc = MyJDBC.getInstance();
        jdbc.updateRestaurant(restaurant);
    }


   /* public void update(Restaurant restaurant)throws SQLException, ClassNotFoundException{

       MyJDBC jdbc= MyJDBC.getInstance();
        jdbc.updateRestaurant( restaurant);

    }
    public void addBranch(Restaurant restaurant,String newlocation)throws SQLException,ClassNotFoundException{
       MyJDBC jdbc= MyJDBC.getInstance();
       jdbc.addBranchRestaurant(restaurant,newlocation);

    }
    public void RemoveResturant(int id)throws SQLException,ClassNotFoundException{
       MyJDBC jdbc= MyJDBC.getInstance();


    }
*/

}