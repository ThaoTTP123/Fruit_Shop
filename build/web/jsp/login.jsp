<%-- 
    Document   : login
    Created on : Jun 23, 2023, 2:49:46 PM
    Author     : lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%String error=(String)request.getAttribute("error");%>
        <form action ="AccountURL" method="post">
            <p> username <input type="text"  name ="username"></p>
            <p> password <input type="password"  name ="password"></p>
            <p style="color: red"><%=error!=null?error:""%></p>
            <p> <input type ="submit" name ="submit" value = "Login"></p>
        </form>
    </body>
</html>
