<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.cse308.projectaim.hibernate.types.CourseOffering"%>
<%@page import="com.cse308.projectaim.beans.AIMEntityListBean"%>
<jsp:useBean id="cOfferingList" class="com.cse308.projectaim.beans.AIMEntityListBean" scope="session"/>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Identify/Request missing Course Offering Information Provided By Course Coordinators</title>
    </head>
    <body>
        <div>Identify Course Offering Information</div><br />
        <div>Course Offerings:</div><br />
        <table id="TableList" border="1">
            <tr>
                <th>Degree Program</th>
                <th>Section Number</th>
                <th>Semester</th>
                <th>Course Coordinator Report</th>
            </tr>
            <c:forEach var="co" items="${cOfferingList.list}" varStatus="cCounter">
                <tr>
                    <td><span id="co-degreeprogram${cCounter.index}"><%= request.getParameter("dpId")%></span></td>
                    <td><span id="co-id${cCounter.index}"><c:out value="${co.sectionNumber}" /></span></td>
                    <td><span id="co-semester${cCounter.index}"><c:out value="${co.semester.semester.year}/${co.semester.semester.term}" /></span></td>
                    <td><span id="co-courseCoordinatorReport${cCounter.index}">
                            <c:choose>
                                <c:when test="${co.courseCoordinatorReport.fileName == null}"><input type="checkbox" checked="checked"/></c:when>
                                <c:otherwise><c:out value="${co.courseCoordinatorReport.fileName}"/></c:otherwise>
                            </c:choose></span></td>
                    <td><input name="RequestCourseOffering" type="button" value="Request missing Information" /></td> 
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
