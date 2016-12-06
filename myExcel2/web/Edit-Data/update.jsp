<%-- 
    Document   : update
    Created on : Mar 8, 2012, 3:20:54 PM
    Author     : kamal.hossen
--%>

<%@page import="gpit.excel.dbConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%
Connection conn=null;
dbConnection dbcon=new dbConnection();

String ide=request.getParameter("id");
int num= Integer.parseInt(ide);
String name=request.getParameter("msisdn");
String address=request.getParameter("name");
String contact=request.getParameter("remarks");
String email=request.getParameter("email");
try{
conn=dbcon.getDBConnection();
Statement st=null;
st=conn.createStatement();
st.executeUpdate("update jsp_upload set msisdn='"+name+"',user_name='"+address+"',remarks='"+contact+"',email='"+email+"' where row_id='"+num+"'");
response.sendRedirect("/Edit-Data/application.jsp");
}
catch(Exception e){
System.out.println(e);
}
%>