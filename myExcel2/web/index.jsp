<%-- 
    Document   : index
    Created on : Feb 29, 2012, 4:54:23 PM
    Author     : kamal.hossen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>

        <form action="generateExcel.jsp" method=post>
            
            <input type="text" name="txtSavePath" value="" />
            <input type="submit" value="Save" name="btnSave" />
            <br>            
        </form>

     

        <form action="importData.jsp" method="post">
            <%--
            <input type="button" value="save" onClick="doSaveAs()" />
            <input type="button" value="Save" onclick="document.location='success.jsp';" />
            --%>
            <br>
            <INPUT NAME="f1" TYPE="file">
            <input type="submit" value="Save" name="btnSave" />
        </form>

            <form action="UpdateData.jsp">
                <input type="submit" value="update" />

            </form>

    </body>
</html>
