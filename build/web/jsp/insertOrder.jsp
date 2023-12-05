<%-- 
    Document   : insertOrder
    Created on : Jun 23, 2023, 1:47:08 PM
    Author     : lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet,entity.Customer" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Customer c=(Customer)session.getAttribute("account");
            String id=c.getCustomerID();
            String contactname=c.getContactName();
            ResultSet rsVia=(ResultSet)request.getAttribute("rsVia");
            ResultSet rsEmp=(ResultSet)request.getAttribute("rsEmp");
        %>
        <form action="OrderController">
            <input type="hidden" name="customerID" value="<%=id%>">
            <table>
                <tr>
                    <td><%=contactname%></td>
                </tr>
                <tr>
                    <td>[EmployeeID]</td>
                    <td><select name="employeeID" >
                            <%
                                while(rsEmp.next()){
                            %>
                            <option value="<%=rsEmp.getInt(1)%>" ><%=rsEmp.getString(2)%></option>
                            <%
                                }
                            %>
                        </select></td>
                </tr>
                <tr>
                    <td>[ShipVia]</td>
                    <td><select name="shipVia" >
                            <%
                                while(rsVia.next()){
                            %>
                            <option value="<%=rsVia.getInt(1)%>"><%=rsVia.getString(2)%></option>
                            <%
                                }
                            %>
                        </select></td>
                </tr>
                <tr>
                    <td>[OrderDate]</td>
                    <td><input type="text" name="orderDate" ></td>
                </tr>
                <tr>
                    <td>[RequiredDate]</td>
                    <td><input type="text" name="requiredDate" ></td>
                </tr>
                <tr>
                    <td>[ShippedDate]</td>
                    <td><input type="text" name="shippedDate" ></td>
                </tr>
                <tr>
                    <td>[Freight]</td>
                    <td><input type="text" name="freight" ></td>
                </tr>
                <tr>
                    <td>[ShipName]</td>
                    <td><input type="text" name="shipName" ></td>
                </tr>
                <tr>
                    <td>[ShipAddress]</td>
                    <td><input type="text" name="shipAddress" ></td>
                </tr>
                <tr>
                    <td>[ShipCity]</td>
                    <td><input type="text" name="shipCity" ></td>
                </tr>
                <tr>
                    <td>[ShipRegion]</td>
                    <td><input type="text" name="shipRegion" ></td>
                </tr>
                <tr>
                    <td>[ShipPostalCode]</td>
                    <td><input type="text" name="shipPostalCode" ></td>
                </tr>
                <tr>
                    <td>[ShipCountry]</td>
                    <td><input type="text" name="shipCountry" ></td>
                </tr>
                <tr>
                    <td><input type="submit" value="AddOrder" name="submit"></td>
                    <td><input type="reset" value="Reset"></td>
                </tr>
            </table>
            <input type="hidden" name="service" value="insert">
        </form>
    </body>
</html>
