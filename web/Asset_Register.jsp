<%-- 
    Document   : Asset_Register
    Created on : Apr 20, 2023, 1:43:57 PM
    Author     : thegr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*, java.util.*, Tables.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1 style="text-align: center;">Monthly Dues, Payment, and Billing System</h1>
        <div style="display: flex; justify-content: space-between;">
            <a>    Register an Asset</a><br>
            <a href ="Asset_Update.jsp" style="text-align: center;">    Update an Asset</a><br>
            <a href ="Asset_Delete.jsp" style="text-align: center;">    Delete an Asset</a><br>
            <a href ="Asset_Dispose.jsp" style="text-align: center;">    Dispose an Asset</a><br>
            <a href ="Act_Record.jsp" style="text-align: center;">    Record Asset Activity</a><br>
            <a href ="Act_Update.jsp" style="text-align: center;">    Update Asset Activity Information</a><br>
            <a href ="Act_Complete.jsp" style="text-align: center;">    Complete Asset Activity</a><br>
            <a href ="Act_Delete.jsp" style="text-align: center;">    Delete Asset Activity</a><br>
        </div>
        <h2 style="text-align: center;">Register Asset</h2>
        <form name="selectproduct" action="recordasset.jsp" method="POST" style="text-align: center">
            <div style="display: flex; justify-content: center;">
                <div style="border: 1px solid black; padding: 20px;">
                    <div style="text-align: left;">
                                            Asset Name — <input type="text" name="asset_name" id="asset_name"><br>
                    Asset Description: <input type="text" name="asset_description" id="asset_description"><br>
                    Acquisition Date: <input type="date" name="acquisition_date" id="acquisition_date"><br>
                    For Rent: <input type="checkbox" name="forrent" id="forrent"><br>
                    Asset Type: <input type="text" name="type_asset" id="type_asset"><br>
                    Asset Value: <input type="text" name="asset_value" id="asset_value"><br>
                    Status:     <select name="status">
                        <option value="W">Working Condition</option>
                        <option value="D">Deteriorated</option>
                        <option value="P">For Repair</option>
                        <option value="S">For Disposal</option>
                        <option value="X">Disposed</option>
                     </select><br>
                    Lattitude: <input type="number" name="loc_lattitude" id="loc_lattitude"><br>
                    Longitude: <input type="number" name="loc_longitude" id="loc_longitude"><br>
                    Enter HOA Name: <input type="text" name="hoa_name" id="hoa_name"><br>
                    Enclosing Asset (Existing Asset): <input type="number" name="enclosing_asset" id="enclosing_asset"><br>
                    </div>
                    <input type="submit" value="Add to database" name="Add to database" />
                </div>
            </div>
        </form>
    </body>
</html>
