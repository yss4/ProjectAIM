<%@page import="com.cse308.projectaim.beans.CourseOutcomeBean"%>
<%@page import="com.cse308.projectaim.beans.ResultBean"%>
<jsp:useBean id="courseOutcomeBean" class="com.cse308.projectaim.beans.CourseOutcomeBean" scope="session" />
<jsp:useBean id="resultBean" class="com.cse308.projectaim.beans.ResultBean" scope="session" />
<jsp:useBean id="soList" class="com.cse308.projectaim.beans.AIMEntityListBean" scope="session" /> 
<jsp:useBean id="courseList" class="com.cse308.projectaim.beans.AIMEntityListBean" scope="session" /> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    resultBean.setRequestName("Create CourseOutcome");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href='http://fonts.googleapis.com/css?family=Prosto+One' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Viga' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="../css/CreateCourseOutcome.css" />
       
        <title>Create CourseOutcome</title>
        <div style="position: relative; text-align: right;"><a id="MainPageLink"  href="../test.jsp" >Go to menu page</a></div>
    </head>
    <body>
        <div id="banner">Create Course Outcome</div>
        <form action="controller" method="post">
            <input type="hidden" name="_method" value="post" />
            Description:  <input id="Description" type="text" name="description" value="" /><br />
            Rationale: <input id="Rationale" type="text" name="rationale" value=""/><br />
            Assessed: <input id="Assessed" type="checkbox" name="assessed" value="true"/><br /></br>
<!--
            Course:
            <select name="course">
                <c:forEach var="course" items="${courseList.list}">
                    <option>${course.id}</option>
                </c:forEach>
            </select><br />
-->
            Student Outcome: 
            <select name="studentoutcome">
                <c:forEach var="so" items="${soList.list}">
                    <option>${so.studentOutcomeId}</option>
                </c:forEach>
            </select><br />
            <input id="SubmitButton" type="submit" value="Create Course Outcome" />
        </form>
    </body>
</html>
