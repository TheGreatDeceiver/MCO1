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
        <title>Asset Disposed</title>
    </head>
    <body>
        <jsp:useBean id="aBean" class="tableControl.Assets" scope="session" />
        <%
            aBean.id = Integer.parseInt(request.getParameter("asset_id"));
            aBean.disposeAsset();
        %>
        <h1>Asset Disposed!</h1>
        <button onclick="window.location.href='Asset_Dispose.jsp'">Dispose of Another Asset?</button><br>
        <button onclick="window.location.href='index.jsp'">Main menu</button><br>
    </body>
</html>
