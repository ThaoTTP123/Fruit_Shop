<%-- 
    Document   : admin
    Created on : Jun 26, 2023, 5:25:09 PM
    Author     : lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.DAOProduct,entity.Product,java.sql.ResultSet" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <a href="AccountURL?service=logout">Logout</a>
        <a href="OrderController">Show Order</a>
        <div style="text-align: center">Menu <jsp:include page="Menu.jsp"></jsp:include></div>
        <%
            ResultSet rs=(ResultSet)request.getAttribute("cRs");
            String col=(String)request.getAttribute("col");
            String order=(String)request.getAttribute("order");
        %>
        <h2 style="text-align: center">
            <a href="ProductController?service=insert">Insert Product</a>
        </h2>
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
                    <th>Update</th>
                    <th>Delete</th>
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
                    <td><a href="ProductController?service=update&id=<%=rs.getInt(1)%>">update</a></td>
                    <td><a href="#" onclick="doDelete(<%=rs.getInt(1)%>)">delete</a></td>
                </tr>
                <%}%>
            </tbody>
        </table>
        <script type="text/javascript">
            function doDelete(id) {
                if (confirm("U sure to delete this product")) {
                    window.location = "ProductController?service=delete&id=" + id;
                }
            }
            function sort(cate) {
                var col = document.getElementById('col').value;
                var orderType = document.getElementsByName('order');
                var order = "asc";
                if (!orderType[0].checked) {
                    order = "desc";
                }
                window.location = "MainController?col=" + col + "&order=" + order + "&cate=" + cate;
            }
        </script>
    </body>
</html>
