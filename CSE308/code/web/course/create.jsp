<%-- 
    Document   : create
    Created on : Nov 5, 2013, 9:35:53 AM
    Author     : Steeve Yoo
--%>

<%@page import="com.cse308.projectaim.beans.CourseBean"%>
<%@page import="com.cse308.projectaim.beans.ResultBean"%>
<jsp:useBean id="courseBean" class="com.cse308.projectaim.beans.CourseBean" scope="session" />
<jsp:useBean id="resultBean" class="com.cse308.projectaim.beans.ResultBean" scope="session" />
<%
	resultBean.setRequestName("Create Course");
%>
<!DOCTYPE html>
<html>
   <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Course</title>
    </head>
    <body>
        <form action="controller" method="post">
            <input type="hidden" name="_method" value="post" />
            Identifier: <input type="text" name="identifier" value="${courseBean.id}" /><br />
            Name:  <input type="text" name="name" value="${courseBean.name}" /><br />
            Primary Course Coordinator: <input type="text" name="primarycoursecoordinator" value="${courseBean.primaryCourseCoordinator}"/><br />
            <input id="button" type="submit" value="Create Course" />
        </form>
    </body>
</html>
