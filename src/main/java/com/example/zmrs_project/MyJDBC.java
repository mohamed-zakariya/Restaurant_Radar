package com.example.zmrs_project;


import java.sql.*;

public class MyJDBC {
    private static MyJDBC jdbc;
    static Connection connection = null;
    String url = "jdbc:mysql://localhost:3306/login";
    String user = "root";
    String password = "Mhdzikoo@123";
    private MyJDBC()throws SQLException{
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
            ps = c.prepareStatement("insert into login (userName, password, email) values (?, ?, ?)");

            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, email);

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
            ResultSet resultSet = statement.executeQuery("select * from login");
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
    public int noOfUsers() throws SQLException {
        Connection c = null;
        ResultSet rs = null;
        int noOfUsers = 0;
        try {
            c = this.getConnection();
            Statement st = c.createStatement();
            rs = st.executeQuery("SELECT COUNT(userName) FROM login WHERE type = 1");
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

}
