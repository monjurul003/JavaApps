<%-- 
    Document   : addnumber
    Created on : Mar 7, 2012, 5:08:39 PM
    Author     : kamal.hossen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <%
            int n1, n2;
            n1 = Integer.parseInt( request.getParameter("num1"));
            n2 = Integer.parseInt( request.getParameter("num2"));
            out.println(n1 + n2);
        %>
