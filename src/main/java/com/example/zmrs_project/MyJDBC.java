package com.example.zmrs_project;


import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

public class MyJDBC {
    private static MyJDBC jdbc;
    static Connection connection = null;
    String url = "jdbc:mysql://localhost:3306/try";
    String user = "root";
    String password = "Radwan123456";
    LocalDateTime Time ;
    MyJDBC()throws SQLException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
        catch (SQLException e){
            System.out.println("not connected + "+ e.getMessage());
        }
    }
    public static MyJDBC getInstance() throws SQLException, ClassNotFoundException {
        if(jdbc == null){
            jdbc = new MyJDBC();
        } else if (jdbc.getConnection().isClosed()) {
            jdbc = new MyJDBC();
        }
        return jdbc;
    }
    public static Connection getConnection() throws SQLException{

        return connection;
    }

    public void insertUser(String username, String password, String email) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = this.getConnection();
            ps = c.prepareStatement("insert into user (userName, password, email, type) values (?, ?, ?, ?)");

            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.setString(4, "1");

            ps.executeUpdate();
        }finally {
            if(ps != null){
                ps.close();
            }
            if (c != null){
                c.close();
            }
        }
    }
    public int searchUser(String username, String password){
        Connection c = null;
        try {
            c = this.getConnection();
            Statement statement = c.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from user");
            while(resultSet.next()){
                if(resultSet.getString("userName").equals(username)){
                    return resultSet.getInt("type");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public User getUsersData(String username, String password) throws SQLException {
        Connection c = null;
        String email = null;
        try {
            c = this.getConnection();
            Statement statement = c.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from user");
            while(resultSet.next()){
                if(resultSet.getString("userName").equals(username)){
                     email = resultSet.getString("email");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new User(username, password, email, this.getUserReviews(new User(username)));
    }
    public int noOfUsers() throws SQLException {
        Connection c = null;
        ResultSet rs = null;
        int noOfUsers = 0;
        try {
            c = this.getConnection();

            Statement st = c.createStatement();

            rs = st.executeQuery("SELECT COUNT(userName) FROM user WHERE type = 1");


            while(rs.next()){
                String s = rs.getString("count(userName)");
                noOfUsers = Integer.parseInt(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (rs != null)
                rs.close();
            if(c != null)
                c.close();
        }
        return noOfUsers;
    }
    public Location searchLocation(String location){
        Connection c = null;
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        try{
            c = this.getConnection();
            Statement st = c.createStatement();
            ResultSet resultSet = st.executeQuery("select * from restaurant");
            while (resultSet.next()){
                if(resultSet.getString("location").equals(location)){
                    restaurants.add(new Restaurant(resultSet.getString("restaurantName"),
                           this.getRestaurantBranches(new Restaurant(resultSet.getString("restaurantName"))),
                            resultSet.getString("cusine"),
                            this.getRestaurantReviews(new Restaurant(resultSet.getString("restaurantName")))));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new Location(location, restaurants);
    }
    public ArrayList<Review> getUserReviews(User user){
        Connection c = null;
        ArrayList<Review> reviews = new ArrayList<>();
        try{
            c = this.getConnection();
            Statement st = c.createStatement();
            ResultSet resultSet = st.executeQuery("select * from review");
            while (resultSet.next()){
                if(resultSet.getString("userName").equals(user.getUsername())){
                    ArrayList<String> comment = new ArrayList<>();

                    String sql = "SELECT * FROM comment WHERE userName = ? AND restaurantName = ?";

                    PreparedStatement preparedStatement = connection.prepareStatement(sql);


                    preparedStatement.setString(1, user.getUsername());
                    preparedStatement.setString(2, resultSet.getString("restaurantName"));

                    ResultSet resultSet1 = preparedStatement.executeQuery();

                    while (resultSet1.next()){

                            //System.out.println(resultSet1.getString("comment"));
                            System.out.println(resultSet1.getString("time"));
                            comment.add(resultSet1.getString("comment")+" "+" / at :"+resultSet1.getString("time"));
                            //comment.add(resultSet1.getString("comment"));
                    }

                    resultSet1.close();
                    preparedStatement.close();

                    reviews.add(new Review(user, resultSet.getDouble("rate"),
                            new Restaurant(resultSet.getString("restaurantName")),comment));
                }

            }

            return reviews;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean addUserReview(String userName, String restaurantName, double rate) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = this.getConnection();
            Statement st = c.createStatement();
            ResultSet resultSet1 = st.executeQuery("select * from review");
            while(resultSet1.next()){
                if(resultSet1.getString("userName").equals(userName)
                        && resultSet1.getString("restaurantName").equals(restaurantName))
                    return false;
            }
            ps = c.prepareStatement("insert into review (userName, restaurantName, rate) values (?, ?, ?)");

            ps.setString(1, userName);
            ps.setString(2, restaurantName);
            ps.setDouble(3, rate);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            if (ps != null){
                ps.close();
            }
            if (c != null){
                c.close();
            }
        }
        return true;
    }
    public ArrayList<Review> getRestaurantReviews(Restaurant restaurant){
        Connection c = null;
        ArrayList<Review> reviews = new ArrayList<>();
        try{
            c = this.getConnection();
            Statement st = c.createStatement();
            ResultSet resultSet = st.executeQuery("select * from review");
            while (resultSet.next()){
                if(resultSet.getString("restaurantName").equals(restaurant.getRestaurantName())){
                    ArrayList<String> comment = new ArrayList<>();

                    String sql = "SELECT * FROM comment WHERE userName = ? AND restaurantName = ?";

                    PreparedStatement preparedStatement = connection.prepareStatement(sql);


                    preparedStatement.setString(1, resultSet.getString("userName"));
                    preparedStatement.setString(2, restaurant.getRestaurantName());

                    ResultSet resultSet1 = preparedStatement.executeQuery();
                    while (resultSet1.next()){

                        //System.out.println(resultSet1.getString("comment"));
                        comment.add(resultSet1.getString("comment"));
                    }

                    resultSet1.close();
                    preparedStatement.close();

                    reviews.add(new Review(new User(resultSet.getString("userName")), resultSet.getDouble("rate"),
                            restaurant,comment));
                }

            }

            return reviews;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Restaurant> getCusineRestaurant(String cusine, String location){
        Connection c = null;
        Statement st = null;
        Hashtable<String, Integer> hashtable = new Hashtable<String, Integer>();
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        try {
            c = this.getConnection();
            st = c.createStatement();
            ResultSet resultSet = st.executeQuery("select * from restaurant");
            while(resultSet.next()){
                if(resultSet.getString("cusine").equals(cusine) && resultSet.getString("location") == location){
                    restaurants.add(new Restaurant(resultSet.getString("restaurantName"),
                            this.getRestaurantBranches(new Restaurant(resultSet.getString("restaurantName"))),
                            resultSet.getString("cusine")));

                    hashtable.put(resultSet.getString("restaurantName"), 1);
                }
            }
            return restaurants;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Restaurant> getCusineRestaurant(String cusine){
        Connection c = null;
        Statement st = null;
        Hashtable<String, Integer> hashtable = new Hashtable<String, Integer>();
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        try {
            c = this.getConnection();
            st = c.createStatement();
            ResultSet resultSet = st.executeQuery("select * from restaurant");
            while(resultSet.next()){
                if(resultSet.getString("cusine").equals(cusine) && (hashtable.get(resultSet.getString("restaurantName")) == null)){
                    restaurants.add(new Restaurant(resultSet.getString("restaurantName"),
                            this.getRestaurantBranches(new Restaurant(resultSet.getString("restaurantName"))),
                            resultSet.getString("cusine")));

                    hashtable.put(resultSet.getString("restaurantName"), 1);
                }
            }
            return restaurants;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<String> getRestaurantBranches(Restaurant restaurant){
        Connection c = null;
        Statement st = null;
        ArrayList<String> branches = new ArrayList<>();
        try {
            c = this.getConnection();
            st = c.createStatement();
            ResultSet resultSet = st.executeQuery("select * from restaurant");
            while (resultSet.next()){
                if(resultSet.getString("restaurantName").equals(restaurant.getRestaurantName())){
                    branches.add(resultSet.getString("location"));
                }
            }
            return branches;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public double getRate(User user, Restaurant restaurant){
        try {
            Connection c = this.getConnection();
            Statement statement = c.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from review");
            while (resultSet.next()){
                if(resultSet.getString("userName").equals(user.getUsername()) &&
                        resultSet.getString("restaurantName").equals(restaurant.getRestaurantName())){
                    return resultSet.getDouble("rate");
                }
            }
            return 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public boolean setRate(User user, double Rate,Restaurant restaurant){
        double value = this.getRate(user,restaurant);
        if(value == 0){
            try {
                Connection c = this.getConnection();
                PreparedStatement ps = c.prepareStatement("insert into review (userName, restaurantName, rate) values (?, ?, ?)");

                ps.setString(1, user.getUsername());
                ps.setString(2, restaurant.getRestaurantName());
                ps.setDouble(3, Rate);

                ps.executeUpdate();
                return true;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            return false;
        }

    }


    public void addComment(User user,Restaurant restaurant,String COMMENT, LocalDateTime time){
            try {
                Connection c = this.getConnection();
                PreparedStatement ps = c.prepareStatement("insert into comment (userName, restaurantName, comment, time) values (?, ?, ?, ?)");

                ps.setString(1, user.getUsername());
                ps.setString(2, restaurant.getRestaurantName());
                ps.setString(3, COMMENT);
                ps.setTimestamp(4, Timestamp.valueOf(time));

                ps.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

      //Admin functiouns
      public void insertRestaruants(String restaurantName,String location, String cusine,String phone) throws SQLException {
          Connection c = null;
          PreparedStatement ps = null;
          try {
              c = this.getConnection();
              String query = "insert into restaurant (restaurantName, location, cusine, phone) VALUES (?, ?, ?, ?)";
              ps = c.prepareStatement(query);
              ps.setString(1, restaurantName);
              ps.setString(2, location);
              ps.setString(3, cusine);
              ps.setString(4, phone);

              ps.executeUpdate();
              ps.close();
              c.close();
          } catch (Exception e) {
              System.out.println(e.getMessage());
          }
      }

    public void UpdateOnelocationByremoveingTheAnother(Location newAddress){
        Connection c=null;
        PreparedStatement ps= null;
        try{
            c=this.getConnection();
            String qurey="update into Restaurant ( address )set value(?)";
            ps= c.prepareStatement(qurey);
            ps.setString(2,newAddress.toString());

            ps.executeUpdate();
            ps.close();
            c.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void addBranchRestaurant(Restaurant restaurant,String  MoreLocation){//resturant
        Connection c=null;
        PreparedStatement ps= null;
        try{
            c=this.getConnection();
            String qurey="insert into restaurant (location) value ( ?) where restaurantName= ? ";
            ps=c.prepareStatement(qurey);
            ps.setString(1,MoreLocation);
            ps.setString(2,restaurant.getRestaurantName());
            ps.executeUpdate();
            ps.close();
            c.close();


        }
        catch(Exception e){
            System.out.println( e.getMessage());
        }
    }
    public void Remove(String restaurantName ){
        Connection c=null;
        PreparedStatement ps= null;
        try{
            c=this.getConnection();
            String qurey= "delete  from restaurant  WHERE restaurantName = ?";
            ps=c.prepareStatement(qurey);
            ps.setString(0,restaurantName);
            ps.executeUpdate();
            ps.close();
            c.close();

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }


    }




