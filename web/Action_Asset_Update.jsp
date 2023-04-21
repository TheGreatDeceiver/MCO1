<%-- 
    Document   : Action_Asset_Update
    Created on : Apr 20, 2023, 5:22:51 PM
    Author     : thegr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*, java.util.*, tableControl.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Asset Added to Table</title>
    </head>
    <body>
        <jsp:useBean id="aBean" class="tableControl.Assets" scope="session" />
        <%                        
            aBean.acquisition_date = request.getParameter("acquisition_date");
            aBean.description = request.getParameter("asset_description");
            aBean.id = Integer.parseInt(request.getParameter("asset_id"));
            aBean.name = request.getParameter("asset_name");
            aBean.value = Double.parseDouble(request.getParameter("asset_value"));
            
            
            if (request.getParameter("enclosing_asset") == null || request.getParameter("enclosing_asset").isBlank()) {
                aBean.enclosing_asset = 0;
                System.out.println(request.getParameter("enclosing_asset"));
            } else {
                aBean.enclosing_asset = Integer.parseInt(request.getParameter("enclosing_asset"));
            }
            
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
            
            aBean.updateAsset();

            System.out.println(aBean.enclosing_asset + " == " + aBean.id);
            System.out.println(aBean.enclosing_asset == aBean.id);
            
            %>
            <% if (aBean.enclosing_asset == aBean.id) { %>
                <h1>Error, Enclosing Asset is itself!</h1>
                <button onclick="window.location.href='Asset_Update.jsp'">Try Again?</button><br>
            <%} else {
                aBean.updateAsset(); %>
                <h1>Updating old Asset!</h1>
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
                <button onclick="window.location.href='Asset_Update.jsp'">Update Another Asset?</button><br>
                <button onclick="window.location.href='index.jsp'">Main menu</button><br>            
            <%}%>
    </body>
</html>

