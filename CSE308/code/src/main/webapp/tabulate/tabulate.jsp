<%@page import="com.cse308.projectaim.hibernate.types.StudentOutcome"%>
<%@page import="java.util.HashSet"%>
<%@page import="com.cse308.projectaim.hibernate.types.DegreeProgram"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.lang.Integer"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.cse308.projectaim.hibernate.AIMEntity"%>
<%@page import="com.cse308.projectaim.hibernate.types.Course"%>
<%@page import="com.cse308.projectaim.hibernate.types.CourseOutcome"%>
<%@page import="com.cse308.projectaim.beans.AIMEntityListBean"%>
<jsp:useBean id="dpList" class="com.cse308.projectaim.beans.AIMEntityListBean" scope="session" /> 
<jsp:useBean id="semesterList" class="com.cse308.projectaim.beans.AIMEntityListBean" scope="session" />
<jsp:useBean id="courseList" class="com.cse308.projectaim.beans.AIMEntityListBean" scope="session" />
<jsp:useBean id="soList" class="com.cse308.projectaim.beans.AIMEntityListBean" scope="session" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Tabulate Page</title>
    </head>
    <body>
        <h1>View Tabulate Page</h1>
        <h2>1.Tabulate Student Outcomes enabled by Courses Page</h2>
        <form target=_blank action="./studentOutcomesEnableByCourses.jsp" method="post">
            Degree Program:
            <select id="DegreeProgram" name="dpId" >
                <c:forEach var="dp" items="${dpList.list}">
                    <option value="${dp.degreeProgramId}">${dp.degreeProgramId}</option>
                </c:forEach>
            </select>
            <input type="submit" value="Tabulate">
        </form> 

        <h2>2.Tabulate Student Outcomes Assessed In Courses Page</h2>
        <form target=_blank action="./studentOutcomesAssessedInCourses.jsp" method="post">
            Degree Program:
            <select name="dpId" >
                <c:forEach var="dp" items="${dpList.list}">
                    <option value="${dp.degreeProgramId}">${dp.degreeProgramId}</option>
                </c:forEach>
            </select>
            <input type="submit" value="Tabulate">
        </form> 

        <h2>3.Tabulate PEO Attainment</h2>
        <form target=_blank action="./peoAttainment.jsp" method="post">
            Degree Program:
            <select name="dpId" >
                <c:forEach var="dp" items="${dpList.list}">
                    <option value="${dp.degreeProgramId}">${dp.degreeProgramId}</option>
                </c:forEach>
            </select>
            <br />
            Academic Year:
            <select id="Year" name="startYear">
                <c:forEach var="item" items="${semesterList.list}">
                    <option>${item.semester.year}</option>
                </c:forEach>
            </select>
            <span> Fall ~ </span>
            <select id="Year" name="endYear">
                <c:forEach var="item" items="${semesterList.list}">
                    <option>${item.semester.year}</option>
                </c:forEach>
            </select>
            <span> Summer </span>
            <input type="submit" value="Tabulate">
        </form> 
    </body>
</html>
