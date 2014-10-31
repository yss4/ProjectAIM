<%@page import="com.cse308.projectaim.beans.CourseOutcomeDirectAssessmentBean"%>
<%@page import="com.cse308.projectaim.beans.ResultBean"%>
<jsp:useBean id="courseOutcomeDirectAssessmentBean" class="com.cse308.projectaim.beans.CourseOutcomeDirectAssessmentBean" scope="session" />
<jsp:useBean id="resultBean" class="com.cse308.projectaim.beans.ResultBean" scope="session" />
<jsp:useBean id="cOutcomeList" class="com.cse308.projectaim.beans.AIMEntityListBean" scope="session" /> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    resultBean.setRequestName("Create CourseOutcomeDirectAssessment");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href='http://fonts.googleapis.com/css?family=Prosto+One' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Viga' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="../css/CreateCODA.css" />
        <title>Create Course Outcome Direct Assessment</title>
        <div style="position:relative; text-align:right;" ><a id="MainPageLink" href="../test.jsp" >Go to menu page</a></div>
    </head>
    <body>
        
        <div id="banner">Create Course Outcome Direct Assessment</div>

        <form action="controller" method="post">
            <input type="hidden" name="_method" value="post" />
            Id: <input id="Id" type="text" name="id" value="" /><br /></br>
            Course Outcome:
            <select id="CourseOutcome" name="courseoutcome">
                <c:forEach var="co" items="${cOutcomeList.list}">
                    <option>${co.id}</option>
                </c:forEach>
            </select><br /></br>
            Assessment Instrument: <input id="AI" type="text" name="assessmentinstrument" value=""/><br />
            Rationale: <input id="Rationale" type="text" name="rationale" value=""/><br />
            Threshold Score: <input id="TS" type="text" name="thresholdscore" value=""/><br />
            Attainment Level: <input id="AL" type="text" name="attainmentlevel" value=""/><br />
            <input id="SubmitButton" type="submit" value="Create CourseOutcomeDirectAssessment" />
        </form>
    </body>
</html>
