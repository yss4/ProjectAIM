<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.cse308.projectaim.beans.ResultBean"%>
<jsp:useBean id="resultBean" class="com.cse308.projectaim.beans.ResultBean" scope="session" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Success to handle the request</title>
    </head>
    <body>
        <h1>Success: <% out.write(resultBean.getRequestName()); %> </h1>
		<h2><a href="../test.jsp" >Go to menu page</a></h2>
    </body>
</html>
