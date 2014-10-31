<%@page import="com.cse308.projectaim.beans.UserState"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.cse308.projectaim.beans.UserAccountBean"%>
<jsp:useBean id="userAccountBean" class="com.cse308.projectaim.beans.UserAccountBean" scope="session" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href='http://fonts.googleapis.com/css?family=Prosto+One' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Viga' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="./css/Login.css" />
        <title>CSE 308</title>
        
<script>
function display_alert()
  {
  alert("Login Failure");
  }
</script>
    </head>
    <body>
		<%@include file="incs/header.html"%>
                <div id="banner">Welcome to the ABET Information Manager (AIM)</div>
		<%
			if(UserState.LOGIN_FAIL == userAccountBean.getLoginStatus()) {
				%><script>display_alert()</script><%;
				userAccountBean.setLoginStatus(UserState.LOGIN_BEFORE);
			} else if(UserState.LOGIN_SUCCESS == userAccountBean.getLoginStatus()){
				out.print("<span style='color:Blue'>Valid User</span>");
				response.sendRedirect("test.jsp");
			}
		%>
      
        <form action="login" method="post">
           User name: <input id="field1" type="text" name="username" value="" /><br/>
           Password: <input id="field2" type="password" name="password" value="" /><br/>
           <input id="button" type="submit" value="Log in" />
 
        </form>
       </body>
</html>
