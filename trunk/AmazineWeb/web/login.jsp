<!-- tpl:insert page="/theme/A_blue.htpl" --><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <meta http-equiv="Content-Style-Type" content="text/css">
        <link rel="stylesheet" href="/BArg-war/theme/blue.css" type="text/css">
        <!-- tpl:put name="headarea" -->
        <title>Ingreso a Amazine</title>
        <!-- /tpl:put -->
    </head>
    <body>
        <table width="760" cellspacing="0" cellpadding="0" border="0">
            <tbody>
                <tr class="content-area">
                    <td valign="top" height="350"><!-- tpl:put name="bodyarea" -->

                        <!-- form name="frmLogin" action="Main?origen=login.html" method="Post"> -->
                        <form name="frmLogin" action="LoginServlet" method="Post">
                            <input type="hidden" name="origen" value="login.jsp"></input>
                            <input type="hidden" name="user" value=""></input>
                            <input type="hidden" name="password" value=""></input>
                            <br>
                            <br>
                            <table border="0">
                                <tbody>
                                    <tr>
                                        <td width="209">
                                        </td>
                                        <td align="right" width="97">
                                            Usuario:
                                        </td>
                                        <td width="162">
                                            <input type="text" name="txtUsr" size="20"></input>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="209"></td>
                                        <td align="right" width="97">Contraseña:</td>
                                        <td width="162"><input type="password" name="txtPasswd" size="20"></td>
                                    </tr>
                                    <tr>
                                        <td width="209"></td>
                                        <td colspan="2" align="center">
                                            <input type="submit" name="btnLogin" value="Ingresar" disabled="true"></input>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </form>
                        
                        <script type="text/javascript">
			function validate(f) {
				f.btnLogin.disabled = (f.txtUsr.value == '' || f.txtPasswd.value == '');
			}

			function login(f) {
				f.user.value = f.txtUsr.value;
				f.password.value = f.txtPasswd.value;
			}

			function addhandlers(f) {
				f.txtUsr.onkeyup = function( ) { validate(f); };
				f.txtPasswd.onkeyup = function( ) { validate(f); };
				f.btnLogin.onclick = function( ) { login(f); };
			}
			addhandlers(document.frmLogin);
                        </script>
                        
                    <!-- /tpl:put --></td>
                </tr>
                <tr>
                    <td valign="top" height="20" class="footer"></td>
                </tr>
            </tbody>
        </table>
    </body>
</html><!-- /tpl:insert -->