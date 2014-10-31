<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="resultBean" class="com.cse308.projectaim.beans.ResultBean" scope="session" />
<%
	resultBean.setRequestName("View Users");
%>
<!DOCTYPE html>
<html>
   <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Users</title>
    </head>
    <body>
        <form action="controller" method="post">
			<input type="hidden" name="_method" value="get" />
			<input id="button" type="submit" value="View Users" />
        </form>
    </body>
</html>
