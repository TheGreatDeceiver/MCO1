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
        <title>Asset Update</title>
    </head>
    <body>
        <h1 style="text-align: center;">Monthly Dues, Payment, and Billing System</h1>
        <jsp:useBean id="aBean" class="tableControl.Assets" scope="session" />
        <div style="display: flex; justify-content: space-between;">
            <a href ="Asset_Register.jsp" style="text-align: center;">    Register an Asset</a><br>
            <a style="text-align: center;">    Update an Asset</a><br>
            <a href ="Asset_Delete.jsp" style="text-align: center;">    Delete an Asset</a><br>
            <a href ="Asset_Dispose.jsp" style="text-align: center;">    Dispose an Asset</a><br>
            <a href ="Act_Record.jsp" style="text-align: center;">    Record Asset Activity</a><br>
            <a href ="Act_Update.jsp" style="text-align: center;">    Update Asset Activity Information</a><br>
            <a href ="Act_Complete.jsp" style="text-align: center;">    Complete Asset Activity</a><br>
            <a href ="Act_Delete.jsp" style="text-align: center;">    Delete Asset Activity</a><br>
        </div>
        <h2 style="text-align: center;">Update Asset</h2>
        <form name="Asset Info" action="Action_Asset_Update.jsp" method="POST" style="text-align: center">
            <div style="display: flex; justify-content: center;">
                <div style="border: 1px solid black; padding: 20px;">
                    <div style="text-align: left;">
                        Asset ID: 
                        <select name="asset_id" id="asset_id"> 
                            <% for (Assets a : aBean.getAssetList()) {%>
                                <option value="<%=a.id%>"><%=a.id%> <%=a.name%></option>                        
                            <% } %>
                        </select><br>
                        New Asset Name: 
                        <input type="text" name="asset_name" id="asset_name" maxlength="45" required><br>
                        New Asset Description: 
                        <input type="text" name="asset_description" id="asset_description" maxlength="45" required><br>
                        New Acquisition Date: 
                        <input type="date" name="acquisition_date" id="acquisition_date" required><br>
                        New For Rent: 
                        <input type="checkbox" name="forrent" id="forrent"><br>
                        New Asset Type: 
                        <select name="type_asset" required>
                            <option value='P'>Property</option>
                            <option value='E'>Equipment</option>
                            <option value='F'>Furniture and Fixtures</option>
                            <option value='O'>Others</option>
                        </select><br>
                        New Asset Value: 
                        <input type="number" name="asset_value" id="asset_value" step="0.01" max="9999999.99" min="0.0" required><br>
                        New Status:     
                        <select name="status" required>
                            <option value='W'>Working Condition</option>
                            <option value='D'>Deteriorated</option>
                            <option value='P'>For Repair</option>
                            <option value='S'>For Disposal</option>
                            <option value='X'>Disposed</option>
                        </select><br>
                        New Latitude: 
                        <input type="number" name="loc_lattitude" id="loc_lattitude" max="180" min="0.0" step="0.0001" required><br>
                        New Longitude: 
                        <input type="number" name="loc_longiture" id="loc_longiture" max="360" min="0.0" step="0.0001" required><br>
                        New HOA Name: 
                        <select name="hoa_name" id="hoa_name"> 
                            <% for (String a : aBean.getHoaList()) {%>
                                <option value="<%=a%>"><%=a%></option>                        
                            <% } %>
                        </select><br>                
                        New Enclosing Asset (Existing Asset): 
                        <select name="enclosing_asset" id="enclosing_asset"> 
                            <option value="0">N/A</option>   
                            <% for (Assets a : aBean.getAssetList()) {%>
                                <option value="<%=a.id%>"><%=a.id%> <%=a.name%></option>                        
                            <% } %>
                        </select>
                        </div>
                    <input type="submit" value="Update Asset" name="Update Asset"/>
                </div>
            </div>
        </form>
    </body>
</html>