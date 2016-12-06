<%-- 
    Document   : jsExample2
    Created on : Mar 10, 2012, 5:51:26 PM
    Author     : kamal.hossen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<script type="text/javascript">
function show_prompt()
{
var name=prompt("Please enter your name: ","Harry Potter");
if (name!=null && name!="")
  {
  document.write("<p>Hello " + name + "! How are you today?</p>");
  }
}

function show_confirm()
{
var r=confirm("Press a button");
if (r==true)
  {
  alert("You pressed OK!");
  }
else
  {
  alert("You pressed Cancel!");
  }
}
</script>
</head>
<body>

<input type="button" onclick="show_prompt()" value="Show prompt box"/>
<br/>
<input type="button" onclick="show_confirm()" value="Show confirm box" />


</body>
</html>