/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tableControl;

import java.sql.*;
import java.util.ArrayList;


/**
 *
 * @author thegr
 */
public class Asset_Trans {
    public Integer id;
    public String transaction_date;
    public Integer trans_hoid;
    public String trans_position;
    public String trans_electiondate;
    public Boolean isdeleted;
    public Integer approval_hoid;
    public String approval_position;
    public String approval_electiondate;
    public Integer ornum;
    public String transaction_type;
        
    private Boolean connected;
    private Connection connection;
    
    public Asset_Trans() {}
    
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
    
    public int addTransaction() throws SQLException {
        try {
            connection = connect();
            
            PreparedStatement statement = connection.prepareStatement("INSERT INTO ref_ornumbers VALUES (?)");
            statement.setInt(1, ornum);
            statement.executeUpdate();

            statement = connection.prepareStatement("INSERT INTO asset_transactions VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            statement.setInt(1, id);
            statement.setString(2, transaction_date);
            statement.setInt(3, trans_hoid);
            statement.setString(4, trans_position);
            statement.setString(5, trans_electiondate);
            statement.setBoolean(6, false);
            statement.setNull(7, 0);
            statement.setNull(8, 0);
            statement.setNull(9, 0);
            statement.setInt(10, ornum);
            statement.setString(11, "A");
            
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
        connection.close();
        return 1;
    }
    
    public int updateTransaction() throws SQLException {
        try {
            connection = connect();
            
            PreparedStatement statement = connection.prepareStatement(
                            "UPDATE asset_transaction " +
                            "SET trans_hoid = ?, trans_position = ?, " +
                            "trans_electiondate = ?" +
                            " WHERE asset_id = ? AND transaction_date = ?"
            );

            statement.setInt(1, trans_hoid);
            statement.setString(2, trans_position);
            statement.setString(3, trans_electiondate);
            statement.setInt(4, id);
            statement.setString(5, transaction_date);
            
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;        
        }
        connection.close();
        return 1;
    }
    
    public void setOfficer(Integer hoid, Boolean approving) throws SQLException {
        try {
            connection = connect();
            
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM officer WHERE ho_id = ?");
            
            statement.setInt(1, hoid);
            
            ResultSet results = statement.executeQuery();
            
            while (results.next()) {
                if (approving) {
                    approval_hoid = results.getInt("ho_id");
                    approval_position = results.getString("position");
                    approval_electiondate = results.getString("election_date");
                } else {
                    trans_hoid = results.getInt("ho_id");
                    trans_position = results.getString("position");
                    trans_electiondate = results.getString("election_date");          
                }
            }
            
            results.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        connection.close();
    }
    
    public ArrayList<String> getOfficers() throws SQLException {
        ArrayList<String> a = new ArrayList<>();
        try {
            connection = connect();
            
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM officer");
                        
            ResultSet results = statement.executeQuery();
            
            while (results.next()) {
                String hoid = String.valueOf(results.getInt("ho_id"));
                a.add(hoid);
            }
            
            results.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return a;
        }
        connection.close();
        return a;
    }
}
