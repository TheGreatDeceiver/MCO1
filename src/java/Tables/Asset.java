/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tables;
import java.sql.*;
/**
 *
 * @author thegr
 */
public class Asset {
    
    public int asset_id;
    public String asset_description;
    public String asset_name;
    public float asset_value;
    public int enclosing_asset;
    public int forrent;
    public String hoa_name;
    public String acquisition_date;
    
    private Connection connect() {
        Connection connect = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded!");
        } catch (ClassNotFoundException e) {
            
        }
        
        return connect;
    }
}
