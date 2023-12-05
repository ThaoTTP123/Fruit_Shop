<%-- 
    Document   : index
    Created on : Jun 16, 2023, 1:06:18 PM
    Author     : lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Customer" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%Customer c=(Customer) session.getAttribute("account");%>
        <h3>Welcome <%=c!=null?c.getContactName():""%></h3>
        <a href="<%=c!=null?"OrderController":"jsp/login.jsp"%>">Show Order</a>
        <a href="<%=c!=null?"CartController":"jsp/login.jsp"%>">Show Cart</a>
        <%if(c!=null){%>
        <a href="AccountURL?service=logout">Logout</a>
        <%}else{%>
        <a href="AccountURL">Login</a>
        <%}%>
        <div style="text-align: center">Menu <jsp:include page="Menu.jsp"></jsp:include></div>
        <div>Content <jsp:include page="Content.jsp"></jsp:include></div>
        <script type="text/javascript">
            function sort(cate){
                var col = document.getElementById('col').value;
                var orderType = document.getElementsByName('order');
                var order="asc";
                if(!orderType[0].checked){
                    order="desc";
                }
                window.location = "MainController?col="+col+"&order="+order+"&cate="+cate;
            }
        </script>
    </body>
</html>
