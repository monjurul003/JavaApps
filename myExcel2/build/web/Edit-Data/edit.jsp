<%-- 
    Document   : edit
    Created on : Mar 8, 2012, 3:17:47 PM
    Author     : kamal.hossen
--%>

<%@page import="gpit.excel.dbConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@page language="java"%>
<%@page import="java.sql.*"%>
<form method="post" action="update.jsp">
<table border="1">
<tr><th>Name</th><th>Address</th><th>Contact No</th><th>Email</th></tr>
<%
Connection conn=null;
dbConnection dbcon=new dbConnection();

String id=request.getParameter("id");
int no=Integer.parseInt(id);
int sumcount=0;
try {
conn=dbcon.getDBConnection();
String query = "select * from jsp_upload where row_id='"+no+"'";
Statement st = conn.createStatement();
ResultSet rs = st.executeQuery(query);

while(rs.next()){
%>
<tr border="0">
<td><input type="text" name="msisdn" value="<%=rs.getString("msisdn")%>"></td>
<td><input type="text" name="name" value="<%=rs.getString("user_name")%>"></td>
<td><input type="text" name="remarks" value="<%=rs.getString("remarks")%>"></td>
<td><input type="text" name="email" value="<%=rs.getString("email")%>"></td>
<td border="0"><input type="hidden" name="id" value="<%=rs.getString("row_id")%>"></td>
</tr>
<tr>
<td><input type="submit" name="Submit" value="Update" style="background-color:#49743D;font-weight:bold;color:#ffffff;"></td>
</tr>
<%
}
}
catch(Exception e){}
%>
</table>
</form>