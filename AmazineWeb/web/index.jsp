<%@ page 
language="java"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"
isELIgnored="false"
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Amazine</title>
    </head>
    <body>
        <table width="760" cellspacing="0" cellpadding="0" border="0">
            <tbody>
                <tr class="content-area">
                    <td valign="top" height="350">
                        <table width="800" border="0">
                            <tbody>
                                <tr>                                 
                                    <td width="400" align="left">
                                        <c:forEach var="p" items="${privilegios}">					     
                                        <c:if test="${(p.esDeMenu == true) and (p.esAccesible)}">
                                        <!-- Forma el link dinamico -->  
                                        <c:set var="href" value='<a href="'/>
                                        <c:set var="endquote" value='">'/>
                                        <c:set var="endhref" value="</a>"/>
                                        <c:out escapeXml="false" value="${href}${p.pagina}${endquote}${p.nombre}${endhref}"/>
                                        <br/>
                                        </c:if>
                                        </c:forEach>                                   
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td valign="top" height="20" class="footer"></td>
                </tr>
            </tbody>	
        </table>
    </body>
</html><%-- /tpl:insert --%>