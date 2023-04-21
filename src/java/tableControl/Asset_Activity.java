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
            
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM asset_activity WHERE asset_id = ? AND activity_date = ?");
            statement.setInt(1, id);
            statement.setString(2, activity_date);
            ResultSet results = statement.executeQuery();
            
            if (results.next()) {
                connection.close();
                results.close();
                return 0;
            }
            
            results.close();
            
            trans.id = id;
            trans.transaction_date = activity_date;
            trans.ornum = ornum;
            trans.setOfficer(officer, false);
            
            trans.addTransaction();
            
            statement = connection.prepareStatement("INSERT INTO asset_activity VALUES (?,?,?,?,?,?,?,?,?)");
            statement.setInt(1, id);
            statement.setString(2, activity_date);
            statement.setString(3, activity_description.isBlank() ? null : activity_description);
            if (tent_start.isBlank()) {
                statement.setNull(4, 0);
            } else {
                statement.setString(4, tent_start);
            }
            if (tent_end.isBlank()) {
                statement.setNull(5, 0);
            } else {
                statement.setString(5, tent_end);
            }
            statement.setNull(6, 0);
            statement.setNull(7, 0);
            statement.setDouble(8, cost);
            statement.setString(9, status);
            statement.executeUpdate();
            
        
            
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
            
            trans.id = id;
            trans.transaction_date = activity_date;
            trans.ornum = ornum;
            trans.setOfficer(officer, false);

            trans.updateTransaction();
            
            PreparedStatement statement = connection.prepareStatement(
                            "UPDATE asset_activity " +
                            "SET act_end = ?, act_start = ?," +
                            "activity_description = ?," +
                            "cost = ?, status = ?, tent_end = ?, tent_start = ?, " +
                            "act_end = ?, act_start = ? " +
                            "WHERE asset_id = ? AND activity_date = ?"
            );
            
            
            statement.setString(1,act_end);
            statement.setString(2,act_start);
            statement.setString(3,activity_description.isBlank() ? null : activity_description);
            statement.setDouble(4,cost);
            statement.setString(5, status);
            statement.setString(6,tent_end.isBlank() ? null : tent_end);
            statement.setString(7,tent_start.isBlank() ? null : tent_start);
            statement.setString(8,act_end.isBlank() ? null : act_end);
            statement.setString(9,act_start.isBlank() ? null : act_start);
            statement.setInt(10,id);
            statement.setString(11,activity_date);
            
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
            
            PreparedStatement statement = connection.prepareStatement("UPDATE asset_activity SET status = 'C' WHERE asset_id = ? AND activity_date = ?");
            statement.setInt(1, id);
            statement.setString(2, activity_date);
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
            PreparedStatement statement = connection.prepareStatement("UPDATE asset_transactions SET isdeleted = 1 WHERE asset_id = ? AND transaction_date = ?");
            statement.setInt(1, id);
            statement.setString(2, activity_date);
            statement.executeUpdate();
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
    
    public ArrayList<Asset_Activity> getDeletableActivities() throws SQLException {
        ArrayList<Asset_Activity> assets = new ArrayList<>();
        try {
            connection = connect();
            
            PreparedStatement statement;
            

            statement = connection.prepareStatement(
                    "SELECT * FROM asset_activity act LEFT JOIN " + 
                    "asset_transactions trans ON act.asset_id = trans.asset_id AND " +
                    "act.activity_date = trans.transaction_date WHERE trans.isdeleted = 0"
            );

            
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
    
    public ArrayList<Asset_Activity> getAssetActivityList(String column, String filter, Boolean include) throws SQLException {
        ArrayList<Asset_Activity> assets = new ArrayList<>();
        try {
            connection = connect();
            
            PreparedStatement statement;
            
            if (include) {
                statement = connection.prepareStatement("SELECT * FROM asset_activity WHERE " + column + " = ?");
            } else {
                statement = connection.prepareStatement("SELECT * FROM asset_activity WHERE " + column + " <> ?");
            }
            statement.setString(1, filter);
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

    public int getNewORNumber() {
        try {
            connection = connect();
            
            PreparedStatement statement = connection.prepareStatement("SELECT MAX(ornum) FROM ref_ornumbers");
            
            ResultSet results = statement.executeQuery();
            
            int id = -1;
            
            while (results.next()) {
                id = results.getInt(1);
            }
            
            results.close();
            statement.close();
            connection.close();
            
            return id + 1;
        } catch (SQLException ex) {
            return 0;
        }
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
