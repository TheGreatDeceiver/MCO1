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
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:useBean id="aBean" class="tableControl.Assets" scope="session" />
        <%
            Assets asset = new Assets();
                        
            aBean.acquisition_date = request.getParameter("acquisition_date");
            aBean.description = request.getParameter("asset_description");
            aBean.id = aBean.getID();
            aBean.name = request.getParameter("asset_name");
            aBean.value = Double.parseDouble(request.getParameter("asset_value"));
            aBean.enclosing_asset = Integer.parseInt(request.getParameter("enclosing_asset"));
            
            
            Boolean isChecked = false;
            if (request.getParameter("forrent") != null && request.getParameter("forrent").equals("on")){
                isChecked = true;
            }
            aBean.forrent = isChecked;
            aBean.hoa_name = request.getParameter("hoa_name");
            aBean.lattitude = Double.parseDouble(request.getParameter("loc_lattitude"));
            aBean.longiture = Double.parseDouble(request.getParameter("loc_longiture"));
            aBean.status = request.getParameter("status");
            aBean.type_asset = request.getParameter("type_asset");
            
            aBean.recordAsset();
        %>
        <h1>Adding new Asset!</h1>
        Asset Info<br>
        Asset Id: <%=aBean.id%><br>
        Asset Name: <%=aBean.name%><br>
        Asset Description: <%=aBean.description%><br>
        Date Acquired: <%=aBean.acquisition_date%><br>
        For Rent: <%=aBean.forrent%><br>
        Asset Value: <%=aBean.value%><br>
        Asset Type: <%=aBean.type_asset%><br>
        Status: <%=aBean.status%><br>
        Latitude: <%=aBean.lattitude%><br>
        Longitude: <%=aBean.longiture%><br>
        HOA Name: <%=aBean.hoa_name%><br>
        Enclosing Asset: <%=aBean.enclosing_asset%><br>
    </body>
</html>
