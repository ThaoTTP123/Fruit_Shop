<%-- 
    Document   : Content
    Created on : Jun 16, 2023, 1:23:26 PM
    Author     : lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.DAOProduct,entity.Product,java.sql.ResultSet,entity.Customer" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            ResultSet rs=(ResultSet)request.getAttribute("cRs");
            Customer c=(Customer) session.getAttribute("account");
            String col=(String)request.getAttribute("col");
            String order=(String)request.getAttribute("order");
        %>
        <table>
            <tbody>
                <tr>
                    <td>
                        <select id="col" onchange="sort(document.getElementById('cate').value)">
                            <option value="ProductID" <%=col.equals("ProductID")?"selected":""%>>ProductID</option>
                            <option value="ProductName"<%=col.equals("ProductName")?"selected":""%>>ProductName</option>
                            <option value="QuantityPerUnit" <%=col.equals("QuantityPerUnit")?"selected":""%>>QuantityPerUnit</option>
                            <option value="UnitPrice" <%=col.equals("UnitPrice")?"selected":""%>>UnitPrice</option>
                            <option value="UnitsInStock" <%=col.equals("UnitsInStock")?"selected":""%>>UnitsInStock</option>
                            <option value="UnitsOnOrder" <%=col.equals("UnitsOnOrder")?"selected":""%>>UnitsOnOrder</option>
                            <option value="CategoryID" <%=col.equals("CategoryID")?"selected":""%>>CategoryID</option>
                            <option value="CategoryName" <%=col.equals("CategoryName")?"selected":""%>>CategoryName</option>
                            <option value="SupplierID" <%=col.equals("SupplierID")?"selected":""%>>SupplierID</option>
                            <option value="CompanyName" <%=col.equals("CompanyName")?"selected":""%>>CompanyName</option>
                        </select>
                    </td>
                    <td>
                        <input type="radio" name="order" value="asc" <%=order.equals("asc")?"checked":""%> onclick="sort(document.getElementById('cate').value)">Asc</br>
                    </td>
                    <td>
                        <input type="radio" name="order" value="desc" <%=order.equals("desc")?"checked":""%> onclick="sort(document.getElementById('cate').value)">Desc</br>
                    </td>
                </tr>
            </tbody>
        </table>
        <table border="1">
            <caption>Product List</caption>
            <thead>
                <tr>
                    <th>ProductID</th>
                    <th>ProductName</th>
                    <th>QuantityPerUnit</th>
                    <th>UnitPrice</th>
                    <th>UnitsInStock</th>
                    <th>UnitsOnOrder</th>
                    <th>CategoryID</th>
                    <th>CategoryName</th>
                    <th>SupplierID</th>
                    <th>CompanyName</th>
                    <th>Cart</th>
                </tr>
            </thead>
            <tbody>
                <%while(rs.next()){%>
                <tr>
                    <td><%=rs.getString(1)%></td>
                    <td><%=rs.getString(2)%></td>
                    <td><%=rs.getString(3)%></td>
                    <td><%=rs.getString(4)%></td>
                    <td><%=rs.getString(5)%></td>
                    <td><%=rs.getString(6)%></td>
                    <td><%=rs.getString(7)%></td>
                    <td><%=rs.getString(8)%></td>
                    <td><%=rs.getString(9)%></td>
                    <td><%=rs.getString(10)%></td>
                    <%String url="CartController?service=addCart&id="+rs.getInt(1);%>
                    <td><a href="<%=c!=null?url:"jsp/login.jsp"%>">addCart</a></td>
                </tr>
                <%}%>
            </tbody>
        </table>
    </body>
</html>
