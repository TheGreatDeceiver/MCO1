<%-- 
    Document   : Action_Activity_Complete
    Created on : Apr 20, 2023, 9:50:19 PM
    Author     : thegr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*, java.util.*, tableControl.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Asset Activity Added</title>
    </head>
    <body>
        <jsp:useBean id="actBean" class="tableControl.Asset_Activity" scope="session"/>
        <h1>Hello World!</h1>
        <%
            String selectedOption = request.getParameter("asset_id");
            String[] values = selectedOption.split(",");
            String value1 = values[0];
            String value2 = values[1];
            actBean.act_end = request.getParameter("act_end");
            actBean.act_start = request.getParameter("act_start");
            actBean.activity_date = value2;
            actBean.activity_description = request.getParameter("activity_description");
            
            if (request.getParameter("cost").isBlank()) {
                actBean.cost = 0.d;
            } else {
                actBean.cost = Double.parseDouble(request.getParameter("cost"));
            }
            
            actBean.id = Integer.parseInt(value1);
            actBean.status = request.getParameter("status");
            actBean.tent_end = request.getParameter("tent_end");
            actBean.tent_start = request.getParameter("tent_start");
            
            actBean.updateActivity();
        %>
        <h1>Adding new Asset Activity!</h1>
        Asset Activity Info<br>
        Asset Id: <%=actBean.id%><br>
        Activity Date <%=actBean.activity_date%><br>
        Activity Description: <%=actBean.activity_description%><br>
        Tentative Start: <%=actBean.tent_start%><br>
        Tentative End: <%=actBean.tent_end%><br>
        Actual Start: <%=actBean.act_start%><br>
        Actual End: <%=actBean.act_end%><br>
        Cost: <%=actBean.cost%><br>
        Status: <%=actBean.status%><br>
        <a href="Act_Record.jsp">Record another asset activity?</a><br>
        <a href="index.jsp">Back to menu</a><br>
    </body>
</html>
