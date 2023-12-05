<%-- 
    Document   : Menu
    Created on : Jun 16, 2023, 1:17:18 PM
    Author     : lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.DAOProduct,entity.Product,java.sql.ResultSet,entity.Customer" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="css/styleMenu.css"/>
    </head>
    <body>
        <%
            ResultSet mRs=(ResultSet)request.getAttribute("mRs");
            String cate=(String)request.getAttribute("cate");
        %>
        <ul class="menu">
            <input type="hidden" id="cate" value="<%=cate%>">
            <li onclick="sort('a.CategoryID')">All</li>
            <%while(mRs.next()){%>
            <li onclick="sort(<%=mRs.getString(1)%>)"><%=mRs.getString(2)%></a></li>
            <%}%>
        </ul>
    </body>
</html>
