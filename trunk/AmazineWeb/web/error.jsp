<%-- 
Document   : error
Created on : 02-nov-2007, 5:13:20
Author     : Mateo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
    </head>
    <body>	
        Error, error, warning, warning:<br/>
        <c:out value="${Error}"/>
        <c:remove scope="session" var="Error"/>
    </body>
</html>
