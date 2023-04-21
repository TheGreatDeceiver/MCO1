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
        <jsp:useBean id="actBean" class="tableControl.Asset_Activity" scope="session" />
        <div style="display: flex; justify-content: space-between;">
            <a href ="Asset_Register.jsp" style="text-align: center;">    Register an Asset</a><br>
            <a href ="Asset_Update.jsp" style="text-align: center;">    Update an Asset</a><br>
            <a href ="Asset_Delete.jsp" style="text-align: center;">    Delete an Asset</a><br>
            <a href ="Asset_Dispose.jsp" style="text-align: center;">    Dispose an Asset</a><br>
            <a href ="Act_Record.jsp" style="text-align: center;">    Record Asset Activity</a><br>
            <a href ="Act_Update.jsp" style="text-align: center;">    Update Asset Activity Information</a><br>
            <a style="text-align: center;">    Complete Asset Activity</a><br>
            <a href ="Act_Delete.jsp" style="text-align: center;">    Delete Asset Activity</a><br>
        </div>
        <h2 style="text-align: center;">Complete Activity</h2>
        <form name="Asset Info" action="Action_Activity_Complete.jsp" method="POST" style="text-align: center">
            <div style="display: flex; justify-content: center;">
                <div style="border: 1px solid black; padding: 20px;">
                    <div style="text-align: left;">
                        Asset ID :
                        <select name="asset_id" id="asset_id"> 
                            <% for (Asset_Activity a : actBean.getAssetActivityList("status", "C", false)) {%>
                                <option value="<%=a.id%>, <%=a.activity_date%>"><%=a.id%> | <%=a.activity_date%> | <%=a.activity_description%></option>                        
                            <% } %>
                        </select><br>   
                    </div>
                    <input type="submit" value="Complete Activity" name="Complete Activity"/>
                </div>
            </div>
        </form>
    </body>
</html>
