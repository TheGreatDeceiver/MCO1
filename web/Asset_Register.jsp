<%-- 
    Document   : Asset_Register
    Created on : Apr 20, 2023, 1:43:57 PM
    Author     : thegr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*, java.util.*, tableControl.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1 style="text-align: center;">Monthly Dues, Payment, and Billing System</h1>
        <jsp:useBean id="aBean" class="tableControl.Assets" scope="session" />
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
        <form name="Asset Info" action="Action_Add_Asset.jsp" method="POST" style="text-align: center">
            <div style="display: flex; justify-content: center;">
                <div style="border: 1px solid black; padding: 20px;">
                    <div style="text-align: left;">
                        Asset Name: <input type="text" name="asset_name" id="asset_name" required><br>
                        Asset Description: <input type="text" name="asset_description" id="asset_description" required><br>
                        Acquisition Date: <input type="date" name="acquisition_date" id="acquisition_date" required><br>
                        For Rent: <input type="checkbox" name="forrent" id="forrent" required><br>
                        Asset Type: <select name="type_asset" required>
                            <option value="W">Property</option>
                            <option value="D">Equipment</option>
                            <option value="P">Furniture</option>
                            <option value="S">Fixtures</option>
                        </select><br>
                        Asset Value: <input type="number" name="asset_value" id="asset_value" step="0.01" required><br>
                        Status:     <select name="status" required>
                            <option value="W">Working Condition</option>
                            <option value="D">Deteriorated</option>
                            <option value="P">For Repair</option>
                            <option value="S">For Disposal</option>
                            <option value="X">Disposed</option>
                        </select><br>
                        Latitude: <input type="number" name="loc_lattitude" id="loc_lattitude" step="0.01" required><br>
                        Longitude: <input type="number" name="loc_longiture" id="loc_longiture" step="0.01" required><br>
                        Enter HOA Name: <input type="text" name="hoa_name" id="hoa_name" required><br>
                        Enclosing Asset (Existing Asset): <select name="products" id="products"> 
                                <option value="0">N/A</option>   
                                <% for (Assets a : aBean.getAssetList()) {%>
                                    <option value="<%=a.id%>"><%=a.id%> <%=a.name%></option>                        
                                <% } %>
                            </select>
                        </div>
                    <input type="submit" value="Add to database" name="Add to database"/>
                </div>
            </div>
        </form>
    </body>
</html>
