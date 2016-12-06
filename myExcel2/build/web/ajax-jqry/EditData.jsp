<%-- 
    Document   : EditData
    Created on : Mar 7, 2012, 1:08:33 PM
    Author     : kamal.hossen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Page</title>

        <script type="text/javascript" src="/js/jquery-1.7.1.js"></script>
        <script language="javascript" >

            function addNumbers()
            {
                // use get method to make an AJAX call to addnumbers.jsp
                //jQuery.get( url, [data], [callback], [type] )
                //jQuery.post( url, [data], [callback], [type] )

                $.get("addnumber.jsp",{num1 :$("#num1").val(), num2 : $("#num2").val()},doUpdate);

                alert("HELLO WORLD!");
                
            }
            
            function doUpdate(response)
            {
                if (response)
                    {
                        $("#result").val(response);

                    }
            }
            
        </script>

    </head>
    <body>
   
   <form id="form1" >

   <h2>Using AJAX to Add Numbers with JQUERY</h2>

    <table>
    <tr><td> First Number : </td> <td><input type=text id="num1"></td></tr>
    <tr><td> Second Number :</td><td> <input type=text id="num2" ></td></tr>
    <tr><td> Result:</td><td> <input type=text readonly id="result" ></td></tr>
    </table>
     <br />
        <input type=button ID="Button1" runat="server"  onclick="addNumbers()"  value="Add Numbers" />
  </form>
    </body>
</html>
