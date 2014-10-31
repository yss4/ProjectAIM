<%-- 
    Document   : create
    Created on : Nov 5, 2013, 10:21:33 AM
    Author     : Steeve Yoo
--%>
<%@page import="com.cse308.projectaim.beans.CourseOfferingBean"%>
<%@page import="com.cse308.projectaim.beans.ResultBean"%>
<jsp:useBean id="courseOfferingBean" class="com.cse308.projectaim.beans.CourseOfferingBean" scope="session" />
<jsp:useBean id="resultBean" class="com.cse308.projectaim.beans.ResultBean" scope="session" />
<%
	resultBean.setRequestName("Create CourseOffering");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create CourseOffering</title>
    </head>
    <body>
        <form action="controller" method="post">
            <input type="hidden" name="_method" value="post" />
            SectionNumber <input type="text" name="sectionNumber" value="${courseOfferingBean.sectionNumber}" /><br />
            Semester:  <input type="text" name="semester" value="${courseOfferingBean.semester}" /><br />
            CourseAttainmentTarget: <input type="text" name="courseAttainmentTarget" value="${courseOfferingBean.courseAttainmentTarget}" /><br />
            CourseOutcomeSurveyResults: <input type="text" name="courseOutcomeSurveyResults" value="${courseOfferingBean.courseOutcomeSurveyResults}" /><br />
            <input id="button" type="submit" value="Create CourseOffering" />
        </form>
    </body>
</html>
