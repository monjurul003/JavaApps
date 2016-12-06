<%-- 
    Document   : application
    Created on : Mar 8, 2012, 3:13:47 PM
    Author     : kamal.hossen
--%>

<%@page import="gpit.excel.dbConnection"%>
<%@ page import="java.sql.*" %>
<html>
<head>
<script language="javascript">

function editRecord(id){
    var f=document.form;
    f.method="post";
    f.action='edit.jsp?id='+id;
    f.submit();
}
</script>
</head>
<body>

<br><br>
<form method="post" name="form">

    <table border="0" align="center">
        <thead>
            <tr>
                <th></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td border="1">
                    <input type="text" name="txtMSISDN" value="" />                    
                </td>
                <td>
                    <input type="button" value="Search" name="btnSearch" />
                </td>
            </tr>
            <tr>
                <td></td>
                <td></td>
            </tr>
        </tbody>
    </table>

</form>

<form method="post" name="form">
<table border="1" align="center">
<tr border><th>Id</th><th>Name</th><th>Address</th><th>Contact No</th><th>Email</th></tr>
<%
Connection con = null;
dbConnection dbcon= new dbConnection();

int sumcount=0;
Statement st;
try{
con=dbcon.getDBConnection();
String query = "select * from jsp_upload";
st = con.createStatement();
ResultSet rs = st.executeQuery(query);
%>
<%
while(rs.next()){
%>
<tr><td border="1"><%=rs.getString(1)%></td>
<td><%=rs.getString(2)%></td>
<td><%=rs.getString(3)%></td>
<td><%=rs.getString(4)%></td>
<td><%=rs.getString(5)%></td>
<td><input type="button" name="edit" value="Edit" style="background-color:green;font-weight:bold;color:white;" onclick="editRecord(<%=rs.getString(1)%>);" ></td>
</tr>
<%
}
%>
<%
}
catch(Exception e){
e.printStackTrace();
}
%>
</table>
</form>
</body>
</html>