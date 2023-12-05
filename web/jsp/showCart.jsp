<%-- 
    Document   : showCart
    Created on : Jun 20, 2023, 4:42:11 PM
    Author     : lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="jakarta.servlet.http.HttpSession,model.DAOProduct,entity.Product" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <a href="AccountURL?service=logout">Logout</a>
        <h1>Shopping Cart Details</h1>
        <table width=50%>
            <tr>
                <td>Item ID</td>
                <td>Item Name</td>
                <td>Price</td>
                <td>Number of item in Shopping Cart</td>
                <<td>Total</td>
                <td>Remove</td>
            </tr>
            <form action="CartController" id="quanForm" hidden>
                <input type="hidden" name="service" value="update"/>
            </form>
            <%
            java.util.Enumeration em = session.getAttributeNames();
            DAOProduct dao=new DAOProduct();
            double total=0;
            while(em.hasMoreElements()){
                String raw_id= em.nextElement().toString();
                if(!raw_id.equals("account")&&!raw_id.equals("role")){
                int id=Integer.parseInt(raw_id);
                Product pro=dao.searchByID(id);
            %>
            <tr>
                <td><%=pro.getProductID()%></td>
                <td><%=pro.getProductName()%></td>
                <td><%=pro.getUnitPrice()%></td>
                <td><input form="quanForm" type="number" value="<%=session.getAttribute(raw_id)%>" 
                           name="pro<%=pro.getProductID()%>"/></td>
                <td><%=(int)session.getAttribute(raw_id)*pro.getUnitPrice()%></td>
                <td><a href="CartController?service=remove&id=<%=pro.getProductID()%>">Remove</a></td>
            </tr>
            <%
                total=total+(int)session.getAttribute(raw_id)*pro.getUnitPrice();
                }
            }
            %>
            <tr>
                <td><a href="javascript:void(0);" onclick="document.getElementById('quanForm').submit();">
                                        Upadate Cart</a></td>
                <td></td>
                <td></td>
                <td>GrandTotal:<%=total%></td>
                <td><a href="CartController?service=remove">Remove All</a></td>
            </tr>
        </table>
        <h2><a href="MainController">Items List</h2>
        <br>
        <h2><a href="OrderController?service=insert">Check-out</h2>
    </body>
</html>
