/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tableControl;
import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 *
 * @author thegr
 */
public class Assets {
    
    
    public String acquisition_date;
    public String description;
    public Integer id;
    public String name;
    public double value;
    public Integer enclosing_asset;
    public Boolean forrent;
    public String hoa_name;
    public double lattitude;
    public double longiture;
    public String status;
    public String type_asset;
    
    private Boolean connected;
    private Connection connection;
    
    public Assets() {}
    
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
            System.out.println("Connection Unsuccessful");
            connected = false;
            return null;
        }
    }
    
    public int recordAsset() throws SQLException {
        
        try {
            connection = connect();
        
            PreparedStatement statement = connection.prepareStatement("INSERT INTO assets VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setString(3, description);
            statement.setString(4, acquisition_date);
            statement.setBoolean(5, forrent);
            statement.setDouble(6, value);
            statement.setString(7, type_asset);
            statement.setString(8, status);
            statement.setDouble(9, lattitude);
            statement.setDouble(10, longiture);
            statement.setString(11, hoa_name);
            if (enclosing_asset == 0) {
                statement.setNull(12, enclosing_asset);
            } else {
                statement.setInt(12, enclosing_asset);
            }

            statement.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("recording failed");
            System.out.println("SQL Exception:");
            System.out.println("Message: " + e.getMessage());
            System.out.println("Error code: " + e.getErrorCode());
            System.out.println("SQL state: " + e.getSQLState());    
            return 0;
        }
        System.out.println("recording success");
        connection.close();
        return 1;
    }
       
    public int updateAsset() throws SQLException {
        try {
            connection = connect();
            
            PreparedStatement statement = connection.prepareStatement(
                            "UPDATE assets SET acquisition_date = ?, asset_description = ?," +
                            "asset_name = ?, asset_value = ?," +
                            "enclosing_asset = ?, forrent = ?, hoa_name = ?," +
                            "loc_lattitude = ?, loc_longiture = ?, status = ?," +
                            "type_asset = ? WHERE asset_id = ?"
            );        
            statement.setString(1,acquisition_date);
            statement.setString(2,description);
            statement.setString(3,name);
            statement.setDouble(4,value);
            if (enclosing_asset == 0) {
                statement.setNull(5, enclosing_asset);
            } else {
                statement.setInt(5, enclosing_asset);
            }            
            statement.setBoolean(6,forrent);
            statement.setString(7,hoa_name);
            statement.setDouble(8,lattitude);
            statement.setDouble(9,longiture);
            statement.setString(10,status);
            statement.setString(11,type_asset);
            statement.setInt(12,id);

            statement.executeUpdate();
            statement.close();
            System.out.println("updating success");
        } catch (SQLException e) {
            System.out.println("updating failed");
            System.out.println("SQL Exception:");
            System.out.println("Message: " + e.getMessage());
            System.out.println("Error code: " + e.getErrorCode());
            System.out.println("SQL state: " + e.getSQLState());
            return 0;
        }
        connection.close();
        return 1;
    }
    
    public int destroyAsset() throws SQLException {
        try {
            connection = connect();
            
            PreparedStatement statement = connection.prepareStatement("SELECT enclosing_asset FROM assets WHERE asset_id = ?");
            
            statement.setInt(1, id);
            ResultSet results = statement.executeQuery();
            
            
            //Updates all other assets upon removal of an asset 
            while (results.next()) {
                enclosing_asset = results.getInt("enclosing_asset");
            }
            if (enclosing_asset != null) {
                statement = connection.prepareStatement("UPDATE assets SET enclosing_asset = ? WHERE asset_id = ?");
                statement.setNull(1, enclosing_asset);
                statement.setInt(2, id);
            }
            //End
            
            statement = connection.prepareStatement("DELETE FROM assets WHERE asset_id = ?");
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
    
    public int viewAsset() throws SQLException {
        try {
            connection = connect();
            
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM assets WHERE asset_id = ?");
            
            statement.setInt(1, id);
            
            ResultSet results = statement.executeQuery();
            
            while (results.next()) {
                acquisition_date = results.getString("acquisition_date");
                description = results.getString("asset_description");
                id = results.getInt("asset_id");
                name = results.getString("asset_name");
                value = results.getDouble("asset_value");
                enclosing_asset = results.getInt("enclosing_asset");
                forrent = results.getBoolean("forrent");
                hoa_name = results.getString("hoa_name");
                lattitude = results.getDouble("loc_lattitude");
                longiture = results.getDouble("loc_longiture");
                status = results.getString("status");
                type_asset = results.getString("type_asset");
            }
            
            results.close();
            statement.close();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;        
        }
        
        connection.close();
        return 1;    
    }
    
    public int disposeAsset() {
        try {
            connection = connect();
            
            PreparedStatement statement = connection.prepareStatement("UPDATE assets SET status = ? WHERE asset_id = ?");
            
            statement.setString(1, "X");
            statement.setInt(2, id);
            
            statement.executeUpdate();   
            statement.close();
            connection.close();
            return 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;        
        }
    }

    
    
    public int getID() {
        try {
            connection = connect();
            
            PreparedStatement statement = connection.prepareStatement("SELECT MAX(asset_id) FROM assets");
            
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
    
    public ArrayList<String> getHoaList() throws SQLException {
        ArrayList<String> assets = new ArrayList<>();
        try {
            connection = connect();
            
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM hoa");
                        
            ResultSet results = statement.executeQuery();
            
            while (results.next()) {
                String a = results.getString("hoa_name");
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
    
    public ArrayList<Assets> getAssetList() throws SQLException {
        
        ArrayList<Assets> assets = new ArrayList<>();
        try {
            connection = connect();
            
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM assets");
                        
            ResultSet results = statement.executeQuery();
            
            while (results.next()) {
                Assets a = new Assets();
                a.acquisition_date = results.getString("acquisition_date");
                a.description = results.getString("asset_description");
                a.id = results.getInt("asset_id");
                a.name = results.getString("asset_name");
                a.value = results.getDouble("asset_value");
                a.enclosing_asset = results.getInt("enclosing_asset");
                a.forrent = results.getBoolean("forrent");
                a.hoa_name = results.getString("hoa_name");
                a.lattitude = results.getDouble("loc_lattitude");
                a.longiture = results.getDouble("loc_longiture");
                a.status = results.getString("status");
                a.type_asset = results.getString("type_asset");
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
    
    public ArrayList<Assets> getDeletableAssetList() throws SQLException {
        
        ArrayList<Assets> assets = new ArrayList<>();
        try {
            connection = connect();
            
            PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM assets " + 
                "WHERE NOT EXISTS (" +
                "SELECT * FROM asset_activity WHERE assets.asset_id = asset_activity.asset_id) " +
                "AND NOT EXISTS (" +
                "SELECT * FROM asset_rentals WHERE assets.asset_id = asset_rentals.asset_id)"    
            );
                        
            ResultSet results = statement.executeQuery();
            
            while (results.next()) {
                Assets a = new Assets();
                a.acquisition_date = results.getString("acquisition_date");
                a.description = results.getString("asset_description");
                a.id = results.getInt("asset_id");
                a.name = results.getString("asset_name");
                a.value = results.getDouble("asset_value");
                a.enclosing_asset = results.getInt("enclosing_asset");
                a.forrent = results.getBoolean("forrent");
                a.hoa_name = results.getString("hoa_name");
                a.lattitude = results.getDouble("loc_lattitude");
                a.longiture = results.getDouble("loc_longiture");
                a.status = results.getString("status");
                a.type_asset = results.getString("type_asset");
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
    
    public ArrayList<Assets> getAssetList(String column, String filter, Boolean include) throws SQLException { 
        ArrayList<Assets> assets = new ArrayList<>();
        try {
            connection = connect();
            
            PreparedStatement statement;
            
            if (include) {
                statement = connection.prepareStatement("SELECT * FROM assets WHERE " + column + " = ?");
            } else {
                statement = connection.prepareStatement("SELECT * FROM assets WHERE " + column + " <> ?");
            }
            //SELECT * FROM assets WHERE status = 'X'
            
            statement.setString(1, filter);
            
            ResultSet results = statement.executeQuery();
            
            while (results.next()) {
                Assets a = new Assets();
                a.acquisition_date = results.getString("acquisition_date");
                a.description = results.getString("asset_description");
                a.id = results.getInt("asset_id");
                a.name = results.getString("asset_name");
                a.value = results.getDouble("asset_value");
                a.enclosing_asset = results.getInt("enclosing_asset");
                a.forrent = results.getBoolean("forrent");
                a.hoa_name = results.getString("hoa_name");
                a.lattitude = results.getDouble("loc_lattitude");
                a.longiture = results.getDouble("loc_longiture");
                a.status = results.getString("status");
                a.type_asset = results.getString("type_asset");
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
    
    public static void main (String[] args) throws SQLException {
        /**try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/HOADB?useTimezone=true&serverTimezone=UTC&user=root&password=Azyneth2142473787!");
            System.out.println("Connection Successful");
        } catch (SQLException e) {
            System.out.println("Connection Unsuccessful");
            System.out.println("SQL Exception:");
            System.out.println("Message: " + e.getMessage());
            System.out.println("Error code: " + e.getErrorCode());
            System.out.println("SQL state: " + e.getSQLState());           
        }
        System.out.println("Wa Ta Hell");*/
    }
}
