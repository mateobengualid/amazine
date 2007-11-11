<%-- 
Document   : CarritoCompras
Created on : 07-nov-2007, 10:46:35
Author     : Mateo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Contenido del carro de compras</title>
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
                <th>Eliminar<th>
            </tr>
        </thead>
        <tbody>            
            <c:forEach var="detalle" items="${carroCompras.detalles}">                    
            <form name="frmCarritoCompras" action="CarritoComprasServlet" method="Post">
                <tr>                    
                    <c:set var="product" scope="page" value="${detalle.producto}"/>
                    <c:set var="category" scope="page" value="${producto.categoria}"/>
                    <td><c:out value="${producto.nombre}"/></td>
                    <td><c:out value="${producto.descripcion}"/></td>
                    <td><c:out value="${producto.precio}"/></td>
                    <td><c:out value="${categoria.descripcion}"/></td>
                    <td><c:out value="${producto.nombre}"/></td>			    
                    <td><input type="submit" name="btnEliminar${detalle.id}" value="Eliminar"/></td>                    
                </tr>
            </form>
            </c:forEach>                                  
        </tbody>
    </table>
</body>
</html>
