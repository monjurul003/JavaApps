<%-- 
    Document   : UpdateData
    Created on : Mar 6, 2012, 12:20:27 PM
    Author     : kamal.hossen
--%>

<%@page import="gpit.excel.getData"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.sql.*" %>
<%@page import="gpit.excel.getData.*" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <h1></h1>
        <%!
        public void fetchData(String MS)
                {

                    ResultSet rs=null;

                    getData s=new getData();

                    rs=s.getDatafromDB(MS);
                    
                try{
                    System.out.println("Reqturned rows: "+rs.getRow());

                   while(rs.next())
                       {

                       //out.println("<td> <input type='text' name='txmobileno' value="+rs.getString("MSISDN"));
                       //out.println("<td> <input type='text' name='usr' value="+rs.getString("user_name"));
                       //out.println("<td> <input type='text' name='rmo' value="+rs.getString("remarks"));
                       //out.println("<td> <input type='text' name='em' value="+rs.getString("email"));

                       }
                    rs.close();
                    }

                catch(Exception e)
                        {
                    System.out.println(e.getMessage());
                    }
              }
         %>
    </body>

    <table border="0" align="center">
        <thead>
            <tr>
                <th></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <tr border="1">                
                <td>
                    <% System.out.println("########"); %>

                        <input type="text" name="txtMSISDN" value="88017" />
                        <input type="button" value="Search" name="btnSearch" onclick="fetchData(txtMSISDN)" />
                </td>
                <td>                                        

                </td>
            </tr>
            <tr>
                <td></td>
                <td></td>
            </tr>
        </tbody>
    </table>


</html>
