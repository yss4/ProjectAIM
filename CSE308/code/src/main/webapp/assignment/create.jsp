<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.cse308.projectaim.beans.ResultBean"%>
<%@page import="com.cse308.projectaim.beans.AssignmentBean"%>
<jsp:useBean id="resultBean" class="com.cse308.projectaim.beans.ResultBean" scope="session" />
<jsp:useBean id="assignmentBean" class="com.cse308.projectaim.beans.AssignmentBean" scope="session" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%
        resultBean.setRequestName("Create Assignment");
%>
<html>
    <head>
        <title>Create Assignment</title>
    </head>
    <body>
        <form action="controller" method="post" enctype="multipart/form-data">
            <input type="hidden" name="_method" value="post" />
            Name:  <input type="text" name="assignmentname" value="" /><br />
            Description: <input type="file" name="description"/><br />
            Student Sample (Quality: Good): <input type="file" name="studentsample_g"/><br /> 
            Student Sample (Quality: Average): <input type="file" name="studentsample_a"/><br />
            Student Sample (Quality: Poor): <input type="file" name="studentsample_p"/><br />
            <input id="button" type="submit" value="Create Assignment" />
        </form>
    </body>
</html>
