/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tableControl;
import java.sql.*;
import java.util.*;

/**
 *
 * @author thegr
 */
public class Asset_Activity {
    
    public String act_end;
    public String act_start;
    public String activity_date;
    public String activity_description;
    public Integer id;
    public Double cost;
    public String status;
    public String tent_end;
    public String tent_start;
    
    public Integer ornum;
    public Integer officer;
    public Integer approving_officer;
    
    public Boolean president_approval;

    
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
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/HOADB?useTimezone=true&serverTimezone=UTC&user=root&password=Azyneth2142473787!");
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
            Asset_Trans trans = new Asset_Trans();
            connection = connect();
        
            PreparedStatement statement = connection.prepareStatement("INSERT INTO asset_activity VALUES (?,?,?,?,?,?,?,?,?)");
            statement.setInt(1, id);
            statement.setString(2, activity_date);
            statement.setString(3, activity_description);
            statement.setString(4, tent_start);
            statement.setString(5, tent_end);
            statement.setString(6, act_start);
            statement.setString(7, act_end);
            statement.setDouble(8, cost);
            statement.setString(9, status);
            statement.executeUpdate();
            
            trans.id = id;
            trans.transaction_date = activity_date;
            trans.setOfficer(officer, false);

            trans.addTransaction();            
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
        connection.close();
        return 1;
    }
    
    public int updateActivity() throws SQLException {
        try {
            Asset_Trans trans = new Asset_Trans();
            connection = connect();
            
            PreparedStatement statement = connection.prepareStatement(
                            "UPDATE asset_activity" +
                            "SET act_end = ?, act_start = ?," +
                            "activity_date = ?, activity_description = ?," +
                            "cost = ?, status = ?, tent_end = ?, tent_start = ? " + 
                            "WHERE asset_id = ?"
            );
            
            
            statement.setString(1,act_end);
            statement.setString(2,act_start);
            statement.setString(3,activity_date);
            statement.setString(4,activity_description);
            statement.setDouble(5,cost);
            statement.setString(6, status);
            statement.setString(7,tent_end);
            statement.setString(8,tent_start);
            statement.setInt(9,id);
            
            
            
            statement.executeUpdate();
            statement.close();
            
            trans.id = id;
            trans.transaction_date = activity_date;
            trans.setOfficer(officer, false);
            trans.setOfficer(approving_officer, true);

            trans.updateTransaction();
            
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
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;        
        }
        connection.close();
        return 1;    
    }
    
    public int deleteActivity(Boolean presidentApproval) throws SQLException {
        try {
            connection = connect();
            PreparedStatement statement;
            if (presidentApproval) {
                statement = connection.prepareStatement("DELETE asset_activity WHERE asset_id = ? AND transaction_date = ?");
                statement.setInt(1, id);
                statement.setString(2, activity_date);
                
                statement.executeUpdate();
                
                statement = connection.prepareStatement("DELETE asset_transaction WHERE asset_id = ? AND transaction_date = ?");
                statement.setInt(1, id);
                statement.setString(2, activity_date);
                
                statement.executeUpdate();
            } else {
                statement = connection.prepareStatement("UPDATE asset_transaction SET isdeleted = true WHERE asset_id = ? AND transaction_date = ?");
                statement.executeUpdate();
            }

            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;        
        }
        connection.close();
        return 1;            
    }
    
    public ArrayList<Asset_Activity> getAssetActivityList() throws SQLException {
        ArrayList<Asset_Activity> assets = new ArrayList<>();
        try {
            connection = connect();
            
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM asset_activity");
                        
            ResultSet results = statement.executeQuery();
            
            while (results.next()) {
                Asset_Activity a = new Asset_Activity();
                a.act_end = results.getString("act_end");
                a.act_start = results.getString("act_start");
                a.activity_date = results.getString("activity_date");
                a.activity_description = results.getString("activity_description");
                a.id = results.getInt("asset_id");
                a.cost = results.getDouble("cost");
                a.status = results.getString("status");
                a.tent_end = results.getString("tent_end");
                a.tent_start = results.getString("tent_start");
                assets.add(a);
            }
            
            results.close();
            statement.close();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return assets;        
        }
        
        connection.close();
        return assets;     
    }
    
    public ArrayList<String> getORNums () throws SQLException {
        ArrayList<String> assets = new ArrayList<>();
        try {
            connection = connect();
            
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ref_ornumbers");
                        
            ResultSet results = statement.executeQuery();
            
            while (results.next()) {
                String a = String.valueOf(results.getInt("ornum"));
                assets.add(a);
            }
            
            results.close();
            statement.close();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return assets;        
        }
        
        connection.close();
        return assets;  
    }
}
