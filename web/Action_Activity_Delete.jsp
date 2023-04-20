<%-- 
    Document   : Delete_Asset
    Created on : Apr 20, 2023, 2:53:20 PM
    Author     : thegr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*, java.util.*, tableControl.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Deleting Asset Activity</title>
    </head>
    <body>
        <jsp:useBean id="actBean" class="tableControl.Asset_Activity" scope="session"/>
        <%
            String selectedOption = request.getParameter("asset_id");
            String[] values = selectedOption.split(",");
            String value1 = values[0];
            String value2 = values[1];
            actBean.id = Integer.parseInt(value1);
            actBean.activity_date = value2;
            
            Boolean permission = Boolean.parseBoolean(request.getParameter("permission"));
            
            actBean.deleteActivity(permission);
        %>
        
        <%if (permission) {%>
            <h1>Asset Activity Deleted!!! (With President Permission)</h1>
        <%} else {%>
            <h1>Asset Activity Deleted!!</h1>
        <%}%>
        <a href="Act_Delete.jsp">Delete another asset activity?</a><br>
        <a href="index.jsp">Back to menu</a><br>
    </body>
</html>
