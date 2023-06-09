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
        <title>New Asset Activity Added</title>
    </head>
    <body>
        <jsp:useBean id="actBean" class="tableControl.Asset_Activity" scope="session"/>
        <h1>Hello World!</h1>
        <%
            actBean.ornum = actBean.getNewORNumber();

            actBean.activity_date = request.getParameter("activity_date");
            actBean.activity_description = request.getParameter("activity_description");
            
            if (request.getParameter("cost") == null || request.getParameter("cost").isBlank()) {
                actBean.cost = 0.d;
            } else {
                actBean.cost = Double.parseDouble(request.getParameter("cost"));
            }
            actBean.id = Integer.parseInt(request.getParameter("asset_id"));
            actBean.status = request.getParameter("status");
            actBean.tent_end = request.getParameter("tent_end");
            actBean.tent_start = request.getParameter("tent_start");
            actBean.officer = Integer.parseInt(request.getParameter("officer"));
           
        %>
        <%  if (actBean.addActivity() == 1) { %>
            <h1>Adding new Asset Activity!</h1>
            OR NUMBER REF: <%=actBean.ornum%><br>
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
            <button onclick="window.location.href='Act_Record.jsp'">Record Again</button><br>
            <button onclick="window.location.href='index.jsp'">Main menu</button><br>
        <%} else {%>
            <h1>Item has an activity on this date, please try again</h1>
            <button onclick="window.location.href='Act_Record.jsp'">Try Again</button>
        <%}%>
    </body>
</html>
