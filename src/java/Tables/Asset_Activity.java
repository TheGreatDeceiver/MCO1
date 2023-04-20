/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tables;
import java.sql.*;
import java.util.*;

/**
 *
 * @author thegr
 */
public class Asset_Activity {
    
    public String activity_end;
    public String activity_start;
    public String activity_date;
    public String activity_description;
    public Integer id;
    public Double cost;
    public String status;
    public String tent_end;
    public String tent_start;
    
    
    
    private Boolean connected;
    private Connection connection;
    
    public Asset_Activity() {}
    
    public Boolean isConnected() {
        return connected;
    }
    
    private Connection connect() throws SQLException {
        Connection connection;
        connection = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Success!");
        } catch (ClassNotFoundException e) {
            connected = false;
        }
        
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/HOADB?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            System.out.println("Connection Successful");
            connected = true;
            return connection;
        } catch (SQLException e) {
            connected = false;
            return null;
        }
    }
    
    public int addActivity() throws SQLException {
        try {
            connection = connect();
        
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO asset_activity VALUES (?,?,?,?,?,?,?,?,?)")) {
                statement.setInt(1, id);
                statement.setString(2, activity_date);
                statement.setString(3, activity_description);
                statement.setString(4, tent_start);
                statement.setString(5, tent_end);
                statement.setString(6, activity_start);
                statement.setString(7, activity_end);
                statement.setDouble(8, cost);
                statement.setString(9, status);
                
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
        connection.close();
        return 1;
    }
    
    public int updateActivity() throws SQLException {
        try {
            connection = connect();
            
            PreparedStatement statement = connection.prepareStatement(
                            "UPDATE asset_activity" +
                            "SET act_end = ?, act_start = ?," +
                            "activity_date = ?, activity_description = ?, asset_id = ?," +
                            "cost = ?, status = ?, tent_end = ?, tent_start = ?"
            );
            
            
            statement.setString(1,activity_end);
            statement.setString(2,activity_start);
            statement.setString(3,activity_date);
            statement.setString(4,activity_description);
            statement.setInt(5,id);
            statement.setDouble(6,cost);
            statement.setString(7, status);
            statement.setString(8,tent_end);
            statement.setString(9,tent_start);
            
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;        
        }
        connection.close();
        return 1;
    }
    
    public int completeActivity() throws SQLException {
        try {
            connection = connect();
            
            PreparedStatement statement = connection.prepareStatement("UPDATE asset_activity SET status = 'C' WHERE asset_activityID = ?");
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;        
        }
        connection.close();
        return 1;    
    }
    
    public int deleteActivity() throws SQLException {
        try {
            connection = connect();
            
            PreparedStatement statement = connection.prepareStatement("DELETE asset_activity WHERE asset_activityID = ?");
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;        
        }
        connection.close();
        return 1;            
    }
}
