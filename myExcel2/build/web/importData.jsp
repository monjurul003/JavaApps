<%-- 
    Document   : importData
    Created on : Mar 5, 2012, 5:13:54 PM
    Author     : kamal.hossen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
        <h1>Hello World!</h1>

        <table border="1" align="center">
            <thead>
                <tr>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>

                        <jsp:useBean id="abc" class="gpit.excel.myJexcel" scope="session" />
                        <jsp:setProperty name="abc" property="*" />
                        <%
                        out.println("<br>The file Path:" + abc.getF1());
                        abc.readExcel(abc.getF1());
                        abc.writeExcel("C:/java/pm");
                        %>
                        

                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                </tr>
            </tbody>
        </table>

    </body>
</html>
