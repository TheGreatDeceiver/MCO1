<%-- 
    Document   : Action_Activity_Complete
    Created on : Apr 20, 2023, 9:50:19 PM
    Author     : thegr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Asset Activity Completed</title>
    </head>
    <body>
        <jsp:useBean id="actBean" class="tableControl.Asset_Activity" scope="session"/>
        <h1>Asset Activity Marked as Complete!</h1>
        <%
            String selectedOption = request.getParameter("asset_id");
            String[] values = selectedOption.split(",");
            String value1 = values[0];
            String value2 = values[1];
            actBean.id = Integer.parseInt(value1);
            actBean.activity_date = value2;
                        
            actBean.completeActivity();
        %>
        <a href="Act_Complete.jsp">Complete another asset activity?</a><br>
        <a href="index.jsp">Back to menu</a><br>
    </body>
</html>
