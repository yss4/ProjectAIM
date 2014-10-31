<%-- 
    Document   : create
    Created on : Nov 5, 2013, 10:06:42 AM
    Author     : Steeve Yoo
--%>
<%@page import="com.cse308.projectaim.beans.CourseOutcomeBean"%>
<%@page import="com.cse308.projectaim.beans.ResultBean"%>
<jsp:useBean id="courseOutcomeBean" class="com.cse308.projectaim.beans.CourseOutcomeBean" scope="session" />
<jsp:useBean id="resultBean" class="com.cse308.projectaim.beans.ResultBean" scope="session" />
<%
	resultBean.setRequestName("Create Course Outcome");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Course Outcome</title>
    </head>
    <body>
        <form action="controller" method="post">
            <input type="hidden" name="_method" value="post" />
            SequenceNumber: <input type="text" name="sequencenumber" value="${courseOutcomeBean.sequenceNumber}" /><br />
            Description:  <input type="text" name="description" value="${courseOutcomeBean.description}" /><br />
            Rationale: <input type="text" name="rationale" value="${courseOutcomeBean.rationale}" /><br />
            Assessed: <input type="text" name="assessed" value="${courseOutcomeBean.assessed}" /><br />
            <input id="button" type="submit" value="Create Course Outcome" />
        </form>
    </body>
</html>
