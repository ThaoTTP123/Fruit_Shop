<%-- 
    Document   : addProduct
    Created on : Jun 13, 2023, 3:39:00 PM
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
            ResultSet rsCate=(ResultSet)request.getAttribute("rsCate");
            ResultSet rsSupp=(ResultSet)request.getAttribute("rsSupp");
        %>
        <form action="ProductController">

            <table>
                <tr>
                    <td>productName</td>
                    <td><input type="text" name="pname" ></td>
                </tr>
                <tr>
                    <td>supplier</td>
                    <td><select name="supplier" >
                            <%
                                while(rsSupp.next()){
                            %>
                            <option value="<%=rsSupp.getInt(1)%>" ><%=rsSupp.getString(2)%></option>
                            <%
                                }
                            %>
                        </select></td>
                </tr>
                <tr>
                    <td>Category</td>
                    <td><select name="Category" >
                            <%
                                while(rsCate.next()){
                            %>
                            <option value="<%=rsCate.getInt(1)%>"><%=rsCate.getString(2)%></option>
                            <%
                                }
                            %>
                        </select></td>
                </tr>
                <tr>
                    <td>quantityPerUnit</td>
                    <td><input type="text" name="quantityPerUnit" ></td>
                </tr>
                <tr>
                    <td>unitPrice</td>
                    <td><input type="text" name="unitPrice" ></td>
                </tr>
                <tr>
                    <td>unitsInStock</td>
                    <td><input type="text" name="unitsInStock" ></td>
                </tr>
                <tr>
                    <td>unitsOnOrder</td>
                    <td><input type="text" name="unitsOnOrder" ></td>
                </tr>
                <tr>
                    <td>reorderLevel</td>
                    <td><input type="text" name="reorderLevel" ></td>
                </tr>
                <tr>
                    <td>discontinued</td>
                    <td><input type="radio" name="discontinued" value="1" checked>
                        continue
                        <input type="radio" name="discontinued" value="0">
                        disContinue
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" value="Addproduct" name="submit"></td>
                    <td><input type="reset" value="Reset"></td>
                </tr>
            </table>
            <input type="hidden" name="service" value="insert">
        </form>
    </body>
</html>
