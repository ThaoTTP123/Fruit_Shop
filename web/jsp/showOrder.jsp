<%-- 
    Document   : showOrder
    Created on : Jun 26, 2023, 1:16:04 PM
    Author     : lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            ResultSet rs=(ResultSet)request.getAttribute("rsOrder");
            String role=(String)session.getAttribute("role");
            String name=(String)request.getAttribute("name");
        %>
        <a href="AccountURL?service=logout">Logout</a>
        <a href="MainController">Home</a>
        <h1>OrderList of <%=name%></h1>
        <table border="1">
            <thead>
                <tr>
                    <th>OrderID</th>
                    <th>RequiredDate</th>
                    <th><%=role.equals("cus")?"EmployeeID":"CustomerID"%></th>
                </tr>
            </thead>
            <tbody>
                <%while(rs.next()){%>
                <tr>
                    <td><a href="OrderDetailController?id=<%=rs.getString(1)%>&&cus=<%=rs.getString("CustomerID")%>"><%=rs.getString(1)%></a></td>
                    <td><%=rs.getString(2)%></td>
                    <td><%=rs.getString(3)%></td>
                </tr>
                <%}%>
            </tbody>
        </table>

    </body>
</html>
