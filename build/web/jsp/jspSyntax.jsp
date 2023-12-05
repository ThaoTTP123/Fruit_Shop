<%-- 
    Document   : jspSyntax
    Created on : Jun 6, 2023, 3:34:26 PM
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
        <!--        <script>
                    a = 100
                    alert("a=" + a)
                </script>
                <h1 style="color: red">Hello<script>document.write(a)</script></h1>-->
        <%
            int a=500;
        %>
        <h1 style="color: red"><%=a*2%></h1>
        <%
            for(int i=0;i<=a;i+=20){
        %>
        <hr width="<%=i%>"/>
        <%
        }
        %>
        <%! int MAX=1000;%>
        <%!
            String hello(String name){
                return "Hello "+name;
            }
        %>
        <h2><%=hello("Thanh Hai")%></h2>
    </body>
</html>
