<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.cse308.projectaim.beans.PeoBean"%>
<%@page import="com.cse308.projectaim.beans.ResultBean"%>
<jsp:useBean id="peoBean" class="com.cse308.projectaim.beans.PeoBean" scope="session" />
<jsp:useBean id="resultBean" class="com.cse308.projectaim.beans.ResultBean" scope="session" />
<%
	resultBean.setRequestName("Create PEO");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create PEO</title>
    </head>
    <body>
        <form action="controller" method="post">
            <input type="hidden" name="_method" value="post" />
            Identifier: <input type="text" name="identifier" value="${peoBean.id}" /><br />
            Sequence:  <input type="text" name="sequence" value="${peoBean.sequence}" /><br />
            ShortName: <input type="text" name="shortname" value="${peoBean.shortName}"/><br />
            Description: <input type="text" name="description" value="${peoBean.description}" /><br />
            TargetAttainmentLevel: <input type="text" name="targetattainmentlevel" value="${peoBean.targetAttainmentLevel}" /><br />
            <input id="button" type="submit" value="Create PEO" />
        </form>
    </body>
</html>
