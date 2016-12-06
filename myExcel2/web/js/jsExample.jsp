<%-- 
    Document   : jsExample
    Created on : Mar 10, 2012, 4:05:39 PM
    Author     : kamal.hossen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<script type="text/javascript">
function displayDate()
{
document.getElementById("demo").innerHTML=Date();
}
</script>
</head>

<body>

<h1>My First Web Page</h1>

<p id="demo"></p>

<button type="button" onclick="displayDate()">Display Date</button>

</body>
</html>