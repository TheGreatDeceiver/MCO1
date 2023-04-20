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
        <jsp:useBean id="transBean" class="tableControl.Asset_Trans" scope="session" />
        <jsp:useBean id="actBean" class="tableControl.Asset_Activity" scope="session" />
        <jsp:useBean id="aBean" class="tableControl.Assets" scope="session" />
        <div style="display: flex; justify-content: space-between;">
            <a href ="Asset_Register.jsp" style="text-align: center;">    Register an Asset</a><br>
            <a href ="Asset_Update.jsp" style="text-align: center;">    Update an Asset</a><br>
            <a href ="Asset_Delete.jsp" style="text-align: center;">    Delete an Asset</a><br>
            <a href ="Asset_Dispose.jsp" style="text-align: center;">    Dispose an Asset</a><br>
            <a>    Record Asset Activity</a><br>
            <a href ="Act_Update.jsp" style="text-align: center;">    Update Asset Activity Information</a><br>
            <a href ="Act_Complete.jsp" style="text-align: center;">    Complete Asset Activity</a><br>
            <a href ="Act_Delete.jsp" style="text-align: center;">    Delete Asset Activity</a><br>
        </div>
        <h2 style="text-align: center;">Record Activity</h2>
        <form name="Asset Info" action="Action_Activity_Add.jsp" method="POST" style="text-align: center">
            <div style="display: flex; justify-content: center;">
                <div style="border: 1px solid black; padding: 20px;">
                    <div style="text-align: left;">
                        Asset ID :
                        <select name="asset_id" id="asset_id"> 
                            <% for (Assets a : aBean.getAssetList()) {%>
                                <option value="<%=a.id%>"><%=a.id%> <%=a.name%></option>                        
                            <% } %>
                        </select><br>        
                        Authorizing Officer:
                        <select name="officer" id="officer"> 
                            <% for (String a : transBean.getOfficers()) {%>
                                <option value="<%=a%>"><%=a%></option>                        
                            <% } %>
                        </select>  
                        Activity Date: 
                        <input type="date" name="activity_date" id="activity_date" required><br>
                        Activity Description: 
                        <input type="text" name="activity_description" id="activity_description"><br>
                        Tentative Start: 
                        <input type="date" name="tent_start" id="tent_start">
                        Tentative End: 
                        <input type="date" name="tent_end" id="tent_end"><br>
                        Cost: 
                        <input type="number" name="cost" id="cost" step="0.01" max="9999999.99" min="0.0"> 
                        Status:     
                        <select name="status" required>
                            <option value='S'>Scheduled</option>
                            <option value='O'>On-Going</option>
                            <option value='C'>Completed</option>
                        </select><br>
                    </div>
                    <input type="submit" value="Add Asset Activity" name="Add Asset Activity"/>
                </div>
            </div>
        </form>
    </body>
</html>
