<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>El publicador de mensajes web</title>
    </head>
    <body>
        <form name="formu" action="login.php" method="post">
            <table bgcolor="#DADADA" border="0" cellpadding="1" cellspacing="1" align="center">
                <tr>
                    <td bgcolor="#CCCCCC" align="center" colspan="2">
			<b>LOGIN</b>
                    </td>
                </tr>
                <tr>
                    <td align="right">
			<b>Usuario:</b>&nbsp;
                    </td>
                    <td align="left">
                        <input type="text" maxlength="10" name="usu_form">&nbsp;
                    </td>
                </tr>
                <tr>
                    <td align="right">
			&nbsp;<b>Password:</b>&nbsp;
                    </td>
                    <td align="left">
                        <input type="password" maxlength="10" name="pass_form">&nbsp;
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="L O G I N">
                    </td>
                </tr>
                <tr>
                    <td align="center" colspan="2">
			&nbsp;¿No estas registrado? <a href=""><font color="#000000"><b>REGISTRATE</b></font></a>&nbsp;
                    </td>
                </tr>
            </form>
        </table> 
    </body>
</html>
