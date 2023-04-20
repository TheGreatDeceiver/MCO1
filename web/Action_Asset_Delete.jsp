<%-- 
    Document   : Delete_Asset
    Created on : Apr 20, 2023, 2:53:20 PM
    Author     : thegr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Asset Deleted</title>
    </head>
    <body>
        <jsp:useBean id="aBean" class="tableControl.Assets" scope="session" />
        <%
            aBean.id = Integer.parseInt(request.getParameter("asset_id"));
            aBean.destroyAsset();
        %>
        <h1>Asset Deleted!</h1>
        <a href="Asset_Delete.jsp">Delete another asset?</a><br>
        <a href="index.jsp">Back to menu</a><br>
    </body>
</html>
