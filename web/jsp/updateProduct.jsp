<%-- 
    Document   : updateProduct
    Created on : Jun 9, 2023, 1:40:17 PM
    Author     : lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Product,java.sql.ResultSet" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Product pro=(Product)request.getAttribute("pro");
            ResultSet rsCate=(ResultSet)request.getAttribute("rsCate");
            ResultSet rsSupp=(ResultSet)request.getAttribute("rsSupp");
        %>
        <form action="ProductController" method="post">
            <table>
                <tr>
                    <td>productID</td>
                    <td><input type="text" name="pid" value="<%=pro.getProductID()%>" readonly></td>
                </tr>
                <tr>
                    <td>productName</td>
                    <td><input type="text" name="pname" value="<%=pro.getProductName()%>"></td>
                </tr>
                <tr>
                    <td>supplier</td>
                    <td><select name="supplier" >
                            <%
                                while(rsSupp.next()){
                            %>
                            <option value="<%=rsSupp.getInt(1)%>" <%=rsSupp.getInt(1)==pro.getSupplierID()?"selected":""%>><%=rsSupp.getString(2)%></option>
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
                            <option value="<%=rsCate.getInt(1)%>" <%=rsCate.getInt(1)==pro.getCategoryID()?"selected":""%>><%=rsCate.getString(2)%></option>
                            <%
                                }
                            %>
                        </select></td>
                </tr>
                <tr>
                    <td>quantityPerUnit</td>
                    <td><input type="text" name="quantityPerUnit" value="<%=pro.getQuantityPerUnit()%>"></td>
                </tr>
                <tr>
                    <td>unitPrice</td>
                    <td><input type="text" name="unitPrice" value="<%=pro.getUnitPrice()%>"></td>
                </tr>
                <tr>
                    <td>unitsInStock</td>
                    <td><input type="text" name="unitsInStock" value="<%=pro.getUnitsInStock()%>"></td>
                </tr>
                <tr>
                    <td>unitsOnOrder</td>
                    <td><input type="text" name="unitsOnOrder" value="<%=pro.getUnitsOnOrder()%>"></td>
                </tr>
                <tr>
                    <td>reorderLevel</td>
                    <td><input type="text" name="reorderLevel" value="<%=pro.getReorderLevel()%>"></td>
                </tr>
                <tr>
                    <td>discontinued</td>
                    <td><input type="radio" name="discontinued" value="1" 
                               <%=pro.isDiscontinued()==true?"checked":""%>>
                        continue
                        <input type="radio" name="discontinued" value="0"
                               <%=pro.isDiscontinued()==false?"checked":""%>>
                        disContinue
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" value="Updateproduct" name="submit"></td>
                    <td><input type="reset" value="Reset"></td>
                </tr>
            </table>
            <input type="hidden" name="service" value="update">
        </form>
    </body>
</html>
