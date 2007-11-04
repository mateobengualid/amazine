<%-- 
Document   : error
Created on : 02-nov-2007, 5:13:20
Author     : Mateo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
    </head>
    <body>
        Ups, ha habido un error medio fierito, fiera, acá el getMessage:
        
        <%
        out.print(request.getSession().getAttribute("Error"));
        request.getSession().removeAttribute("Error");
        %>
    </body>
</html>