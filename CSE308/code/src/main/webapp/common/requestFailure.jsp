<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.cse308.projectaim.beans.ResultBean"%>
<jsp:useBean id="resultBean" class="com.cse308.projectaim.beans.ResultBean" scope="session" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Failure</title>
    </head>
    <body>
        <h1>Failure: <% out.write(resultBean.getRequestName()); %> </h1>
		<span>Message: <% out.write(resultBean.getMessage()); %> </span>
    </body>
</html>
