<%-- 
    Document   : jsExample_time
    Created on : Mar 10, 2012, 6:44:55 PM
    Author     : kamal.hossen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<script type="text/javascript">
var c=0;
var t;
var timer_is_on=0;

function timedCount()
{
document.getElementById('txt').value=c;
c=c+1;
t=setTimeout("timedCount()",100);
}

function doTimer()
{
if (!timer_is_on)
  {
  timer_is_on=1;
  timedCount();
  }
}

function stopCount()
{
clearTimeout(t);
timer_is_on=0;
}

</script>
</head>

<body>
<form>
<input type="button" value="Start count!" onclick="doTimer()">
<input type="text" id="txt" />
<input type="button" value="Stop count!" onclick="stopCount()">
</form>
</body>
</html>