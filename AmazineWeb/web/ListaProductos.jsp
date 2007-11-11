<%-- 
Document   : ListaProductos
Created on : 07-nov-2007, 10:45:20
Author     : Mateo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Catalogo de productos</title>
    </head>
    <body>	
        <table border="1">
            <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Descripcion</th>
                    <th>Precio</th>
                    <th>Categoria</th>
                    <th>Fecha de Salida</th>
                    <th/>
                </tr>
            </thead>
            <tbody>
                <form name="frmCarritoCompras" action="ListaProductosServlet" method="Post">
                    <c:forEach var="producto" items="${Productos}">     
                        <tr>		    
                            <td><c:out value="${producto.nombre}"/></td>
			    <td><c:out value="${producto.descripcion}"/></td>
                            <td><c:out value="${producto.precio}"/></td>
                            <td><c:out value="${producto.categoria.descripcion}"/></td>
                            <td><c:out value="${producto.nombre}"/></td>			    
                            <td><input type="submit" name="btnComprar${producto.id}" value="Comprar"/></td>                    
                        </tr>
                    </c:forEach>                                  
                </form>            
            </tbody>
        </table>   
    </body>
</html>
