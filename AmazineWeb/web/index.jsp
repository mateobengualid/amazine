<%@ page
language="java"
import="com.business.Privilegio"
import="java.util.Iterator"
import="java.util.ArrayList"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"
%>
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
                                        <%
                                        ArrayList privs = (ArrayList)request.getSession().getAttribute("Privilegios");
                                        
                                        Iterator i = privs.iterator();
                                        while(i.hasNext()) {
                                        Privilegio p = (Privilegio)i.next();
                                        if(p.isEsAccesible()&&p.isEsDeMenu()) {
                                        String vinculo = "<a href=\"" +
                                        p.getPagina() +
                                        "\">" +
                                        p.getNombre() +
                                        "</a>";
                                        out.println(vinculo);
                                        }
                                        }
                                        %>
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