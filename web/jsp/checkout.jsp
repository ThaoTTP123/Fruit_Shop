<%-- 
    Document   : checkout
    Created on : Jun 20, 2023, 5:26:41 PM
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
        <h1>Checkout Success!</h1>
        <%
            ResultSet rs=(ResultSet)request.getAttribute("rsOrderDetail");
            double grandTotal=0;
        %>
        <h1>Order Detail</h1>
        <table border="1">
            <thead>
                <tr>
                    <th>OrderID</th>
                    <th>ProductName</th>
                    <th>Quantity</th>
                    <th>UnitPrice</th>
                    <th>Discount</th>
                    <th>subTotal</th>
                </tr>
            </thead>
            <tbody>
                <%while (rs.next()) {%>
                <tr>
                    <td><%=rs.getString(1)%></td>
                    <td><%=rs.getString(2)%></td>
                    <td><%=rs.getString(3)%></td>
                    <td><%=rs.getString(4)%></td>
                    <td><%=rs.getString(5)%></td>
                    <td><%=rs.getString(6)%></td>
                </tr>
                <%grandTotal=grandTotal+rs.getDouble(6);%>
                <%}%>
            </tbody>
        </table>
        GrandTotal:   <%=String.format("%.2f", grandTotal)%>
        <h2><a href="MainController">New Shopping Cart</h2>
    </body>

</html>
