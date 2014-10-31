<%-- 
    Document   : create
    Created on : Nov 5, 2013, 10:37:59 AM
    Author     : Steeve Yoo
--%>
<%@page import="com.cse308.projectaim.beans.CourseOutcomeDirectAssessmentBean"%>
<%@page import="com.cse308.projectaim.beans.ResultBean"%>
<jsp:useBean id="courseOutcomeDirectAssessmentBean" class="com.cse308.projectaim.beans.CourseOutcomeDirectAssessmentBean" scope="session" />
<jsp:useBean id="resultBean" class="com.cse308.projectaim.beans.ResultBean" scope="session" />
<%
	resultBean.setRequestName("Create Course Outcome Direct Assessment");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Course Outcome Direct Assessment</title>
    </head>
    <body>
        <form action="controller" method="post">
            <input type="hidden" name="_method" value="post" />
            Id: <input type="text" name="id" value="${courseOutcomeDirectAssessmentBean.id}" /><br />
            Course Outcome <input type="text" name="courseOutcome" value="${courseOutcomeDirectAssessmentBean.courseOutcome}" /><br />
            Assessment Instrument  <input type="text" name="assessmentInstrument" value="${courseOutcomeDirectAssessmentBean.assessmentInstrument}" /><br />
            Rationale: <input type="text" name="rationale" value="${courseOutcomeDirectAssessmentBean.rationale}" /><br />
            Threshold Score: <input type="text" name="thresholdScore" value="${courseOutcomeDirectAssessmentBean.thresholdScore}" /><br />
            Attainment Level: <input type="text" name="attainmentLevel" value="${courseOutcomeDirectAssessmentBean.attainmentLevel}" /><br />
            <input id="button" type="submit" value="Create Course Outcome Direct Assessment" />
        </form>
    </body>
</html>
