<%-- 
    Document   : create
    Created on : Nov 5, 2013, 9:23:50 AM
    Author     : Steeve Yoo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.cse308.projectaim.beans.PeoBean"%>
<%@page import="com.cse308.projectaim.beans.ResultBean"%>
<jsp:useBean id="studentOutcomeBean" class="com.cse308.projectaim.beans.StudentOutcomeBean" scope="session" />
<jsp:useBean id="resultBean" class="com.cse308.projectaim.beans.ResultBean" scope="session" />
<%
	resultBean.setRequestName("Create StudentOutcome");
%>
<!DOCTYPE html>
<html>
   <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create StudentOutcome</title>
    </head>
    <body>
        <form action="controller" method="post">
            <input type="hidden" name="_method" value="post" />
            Identifier: <input type="text" name="identifier" value="${studentOutcomeBean.studentOutcomeId}" /><br />
            Sequence:  <input type="text" name="sequence" value="${studentOutcomeBean.sequence}" /><br />
            ShortName: <input type="text" name="shortname" value="${studentOutcomeBean.shortName}"/><br />
            Description: <input type="text" name="description" value="${studentOutcomeBean.description}" /><br />
            <input id="button" type="submit" value="Create StudentOutcome" />
        </form>
    </body>
</html>
