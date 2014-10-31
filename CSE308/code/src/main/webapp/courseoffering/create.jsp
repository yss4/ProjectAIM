<%@page import="com.cse308.projectaim.beans.CourseOfferingBean"%>
<%@page import="com.cse308.projectaim.beans.ResultBean"%>
<jsp:useBean id="courseOfferingBean" class="com.cse308.projectaim.beans.CourseOfferingBean" scope="session" />
<jsp:useBean id="semesterBean" class="com.cse308.projectaim.beans.SemesterBean" scope="session" />
<jsp:useBean id="semesterPKBean" class="com.cse308.projectaim.beans.SemesterPKBean" scope="session" />
<jsp:useBean id="resultBean" class="com.cse308.projectaim.beans.ResultBean" scope="session" />
<jsp:useBean id="courseList" class="com.cse308.projectaim.beans.AIMEntityListBean" scope="session" /> 
<jsp:useBean id="userList" class="com.cse308.projectaim.beans.AIMEntityListBean" scope="session" /> 
<jsp:useBean id="cOutcomeList" class="com.cse308.projectaim.beans.AIMEntityListBean" scope="session" /> 
<jsp:useBean id="codaList" class="com.cse308.projectaim.beans.AIMEntityListBean" scope="session" /> 
<jsp:useBean id="semesterList" class="com.cse308.projectaim.beans.AIMEntityListBean" scope="session" />
<jsp:useBean id="assignmentList" class="com.cse308.projectaim.beans.AIMEntityListBean" scope="session" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    resultBean.setRequestName("Create CourseOffering");
%>
<!DOCTYPE html>
<html>
    <head>	
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href='http://fonts.googleapis.com/css?family=Prosto+One' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Viga' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="../css/CreateCourseOffering.css" />
        <title>Create Course Offering</title>
        <div style="position: relative; text-align: right;"><a id="MainPageLink" href="../test.jsp" >Go to menu page</a></div>
        <script type="text/javascript" src="../js/util.js"></script>
        <script type="text/javascript">
			var courseOfferingAssignmentIdList = new Array();
			var assignmentListForUpdate = document.getElementsByClassName("assignment-update");
			var courseOfferingCodaIdList = new Array();
			var codaListForUpdate = document.getElementsByClassName("coda-update");

			function updateAssignmentIdList(idx) {
                var selectedId = assignmentListForUpdate[idx].id;
                courseOfferingAssignmentIdList
					= updateIdList(selectedId, courseOfferingAssignmentIdList);
                console.log(courseOfferingAssignmentIdList);
                document.getElementById("assignment-id-list-update").value 
					= courseOfferingAssignmentIdList;
            }

			function updateCodaIdList(idx) {
                var selectedId = codaListForUpdate[idx].id;
                courseOfferingCodaIdList
					= updateIdList(selectedId, courseOfferingCodaIdList);
                console.log(courseOfferingCodaIdList);
                document.getElementById("coda-id-list-update").value 
					= courseOfferingCodaIdList;
            }

            function visibilityOnCourseOutcome(id) {
                /*if(document.getElementById(id).style.visibility == "hidden" ||
                    document.getElementById(id).style.visibility == "") {
                    document.getElementById(id).style.opacity="1";
                    document.getElementById(id).style.visibility="visible";
                }*/
                var selectedCourse = document.getElementById("selectedCourse").value;
                console.log(selectedCourse);
                document.getElementsByTagName("span")
            }
        </script>
    </head>
    <body>
        <div id="banner">Create Course Offering</div>
        <form id="CreateSurveyForm" action="controller" method="post" enctype="multipart/form-data">
            <input type="hidden" name="_method" value="post" />
            Semester:
            <select name="semester" id="Semester">
                <c:forEach var="semesterObject" items="${semesterList.list}">
                    <option>${semesterObject.semester.year}/${semesterObject.semester.term}</option>
                </c:forEach>
            </select>
            <div id="Ilabel">Instructor:</div>
            <select name="instructor" id="Instructor">
                <c:forEach var="instructor" items="${userList.list}">
                    <option>${instructor.username}</option>
                </c:forEach>
            </select><br />
            <div id="Clabel">Course:</div>
            <select id="selectedCourse" name="course" onchange="visibilityOnCourseOutcome('courseOutcomePopup')">
                <c:forEach var="course" items="${courseList.list}">
                    <option>${course.id}</option>
                </c:forEach>
            </select>
            <div id="f1Label">Syllabus:</div> <input id="file1" type="file" name="syllabus"/><br />
            <div id="f2Label">Lecture Schedule:</div> <input id="file2" type="file" name="lectureschedule"/>
            <div id="f5Label">CIC Report:</div> <input id="file5" type="file" name="cicreport"/><br />
            <div id="f3Label">End-Of-Semester</div> <div id="R1"> Report:</div> <input id="file3" type="file" name="endofsemesterreport"/>
            <div id="f4Label">Course Coordinator</div> <div id="R2">Report:</div> <input id="file4" type="file" name="coursecoordinatorreport"/></br>
            <div id="f6Label">Lecture Notes:</div> <input id="file6" type="file" name="lecturenotes" multiple /><br />
            <div id="ALabel">Assignments:</div> 
            <input id="assignment-id-list-update" type="hidden" name="assignmentIdList" value="" />
		<div id="scroll1">
            <c:forEach var="assignment" items="${assignmentList.list}" varStatus="counter">
                <input 
                    onclick="updateAssignmentIdList(${counter.index})"
                    type="checkbox" 
                    class="assignment-update"
                    id="${assignment.name}"
                    value="${assignment.name}"/>${assignment.name}<br />
            </c:forEach>
		</div>
            <div id="CODA">	Course Outcome Direct Assessments:</div> 
            <input id="coda-id-list-update" type="hidden" name="codaIdList" value="" />
		<div id="scroll2">
            <c:forEach var="coda" items="${codaList.list}" varStatus="counter">
                <input 
                    onclick="updateCodaIdList(${counter.index})"
                    type="checkbox" 
                    class="coda-update"
                    id="${coda.id}"
                    value="${coda.id}"/>
				${coda.id}-${coda.assessmentInstrument}<br />
            </c:forEach>
		</div>
            <input id="SubmitButton" type="submit" value="Create CourseOffering" />
        </form>
    </body>
</html>
