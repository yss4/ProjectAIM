<!--%@page contentType="text/html" pageEncoding="UTF-8"%-->
<%@page import="com.cse308.projectaim.beans.StudentOutcomeBean"%>
<%@page import="com.cse308.projectaim.beans.ResultBean"%>
<jsp:useBean id="studentOutcomeBean" class="com.cse308.projectaim.beans.StudentOutcomeBean" scope="session" />
<jsp:useBean id="resultBean" class="com.cse308.projectaim.beans.ResultBean" scope="session" />
<jsp:useBean id="dpList" class="com.cse308.projectaim.beans.AIMEntityListBean" scope="session"/>
<!--jsp:useBean id="cOutcomeList" class="com.cse308.projectaim.beans.AIMEntityListBean" scope="session"/-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	resultBean.setRequestName("Create StudentOutcome");
%>
<!DOCTYPE html>
<html>
   <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href='http://fonts.googleapis.com/css?family=Prosto+One' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Viga' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="../css/CreateStudentOutcome.css" />
        <title>Create Student Outcome</title>
        <div style="position:relative; text-align:right;" id="MainPageLink"><a style="color:white" href="../test.jsp" >Go to menu page</a></div>
    </head>
    <body>
        <div id="banner">Create Student Outcome</div>

        <form id="TextForm" action="controller" method="post">
            <input type="hidden" name="_method" value="post" />
            Identifier: <input id="Identifier" type="text" name="identifier" value="" /><br />
            Sequence:  <input id="Sequence" type="text" name="sequence" value="" /><br />
            ShortName: <input id="ShortName" type="text" name="shortname" value=""/><br />
            Description: <input id="Description" type="text" name="description" value="" /><br />
            <br/>
            Degree <div id="Program">Program:</div> 
            <select id="DegreeProgram" name="degreeprogram">
                <c:forEach var="dp" items="${dpList.list}">
                    <option>${dp.degreeProgramId}</option>
                </c:forEach>
            </select><br />
            <input id="SubmitButton" type="submit" value="Create StudentOutcome" />
        </form>
    </body>
</html>
