<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.cse308.projectaim.hibernate.types.CourseOffering"%>
<%@page import="com.cse308.projectaim.beans.AIMEntityListBean"%>
<jsp:useBean id="cOfferingList" class="com.cse308.projectaim.beans.AIMEntityListBean" scope="session"/>
<jsp:useBean id="assignmentList" class="com.cse308.projectaim.beans.AIMEntityListBean" scope="session" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href='http://fonts.googleapis.com/css?family=Prosto+One' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Viga' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="../css/CourseOfferingModify.css" />
     
        
        <title>Course Offering List</title>
        <div style="position: relative; text-align: right;"><a id="MainPageLink" href="../test.jsp" >Go to menu page</a></div>
        <script type="text/javascript" src="../js/util.js"></script>
        <script type="text/javascript">
			var courseOfferingAssignmentIdList;
			var assignmentListForUpdate;
			var courseOfferingCodaIdList;
			var codaListForUpdate;

			function showInfo(idx) {
                var semester = document.getElementById("co-semester"+idx).innerHTML.trim();
                var course = document.getElementById("co-course"+idx).innerHTML;
                var instructor = document.getElementById("co-instructor"+idx).innerHTML;
                var assignments = document.getElementById("co-assignment"+idx).innerHTML;
                var codas = document.getElementById("co-coda"+idx).innerHTML;
                
                document.getElementById("index-update").value=idx;
                document.getElementById("semester-update").value = semester; 
                document.getElementById("course-update").value = course;
                document.getElementById("instructor-update").value = instructor;

				courseOfferingAssignmentIdList = new Array();
				assignmentListForUpdate = document.getElementsByClassName("assignment-update");
				revisingCheckbox(courseOfferingAssignmentIdList, assignments, assignmentListForUpdate);
                document.getElementById("assignment-id-list-update").value = courseOfferingAssignmentIdList;

				courseOfferingCodaIdList = new Array();
				codaListForUpdate = document.getElementsByClassName("coda-update");
				revisingCheckbox(courseOfferingCodaIdList, codas, codaListForUpdate);
                document.getElementById("coda-id-list-update").value = courseOfferingCodaIdList;

                document.getElementById("updatePopup").style.visibility = "visible";
            }

			function updateAssignmentIdList(idx) {
                var selectedId = assignmentListForUpdate[idx].id;
                courseOfferingAssignmentIdList = updateIdList(selectedId, courseOfferingAssignmentIdList);
                console.log(courseOfferingAssignmentIdList);
                document.getElementById("assignment-id-list-update").value = courseOfferingAssignmentIdList;
            }

			function updateCodaIdList(idx) {
                var selectedId = codaListForUpdate[idx].id;
                courseOfferingCodaIdList = updateIdList(selectedId, courseOfferingCodaIdList);
                console.log(courseOfferingCodaIdList);
                document.getElementById("coda-id-list-update").value = courseOfferingCodaIdList;
            }
        </script>        
    </head>
    <body>
  <div id="banner">Modify Course Offerings</div><br />
        <div id="Title">Course Offerings:</div><br /><br /> 
    <div id="Table">
        <table id="TableList" border="1">
            <tr>
                <th>Section Number</th>
                <th>Semester</th>
                <th>Course</th>
                <th>Instructor</th>
                <th>Syllabus</th>
                <th>Lecture Schedule</th>
                <th>End-Of-Semester Report</th>
                <th>Course Coordinator Report</th>
                <th>CIC Report</th>
				<th>Lecture Notes</th>
				<th>Assignments</th>
				<th>CourseOutcome DirectAssessment</th>
            </tr>
            <c:forEach var="co" items="${cOfferingList.list}" varStatus="cCounter">
                <tr>
                    <td><span id="co-id${cCounter.index}"><c:out value="${co.sectionNumber}" /></span></td>
                    <td><span id="co-semester${cCounter.index}"><c:out value="${co.semester.semester.year}/${co.semester.semester.term}" /></span></td>
                    <td><span id="co-course${cCounter.index}"><c:out value="${co.course.id}" /></span></td>
                    <td><span id="co-instructor${cCounter.index}"><c:out value="${co.instructor.username}" /></span></td>
                    <td>
						<a target="_blank" href="../file/controller?fileId=${co.syllabus.id}">
							<c:out value="${co.syllabus.fileName}" />
						</a>
					</td>
                    <td>
						<a target="_blank" href="../file/controller?fileId=${co.lectureSchedule.id}">
							<c:out value="${co.lectureSchedule.fileName}" />
						</a>
					</td>
                    <td>
						<a target="_blank" href="../file/controller?fileId=${co.endOfSemesterReport.id}">
							<c:out value="${co.endOfSemesterReport.fileName}" />
						</a>
					</td>
                    <td>
						<a target="_blank" href="../file/controller?fileId=${co.courseCoordinatorReport.id}">
							<c:out value="${co.courseCoordinatorReport.fileName}" />
						</a>
					</td>
                    <td>
						<a target="_blank" href="../file/controller?fileId=${co.cicReport.id}">
							<c:out value="${co.cicReport.fileName}" />
						</a>
					</td>
					<td>
						<c:forEach var="lectureNote" items="${co.lectureNotes}" >
							<a target="_blank" href="../file/controller?fileId=${lectureNote.id}">
								<c:out value="${lectureNote.fileName},"/>
							</a>
						</c:forEach>
					</td>
					<td>
						<span id="co-assignment${cCounter.index}" style="display:none;">
							<c:forEach var="assignment" items="${co.assignments}" >
								<c:out value="${assignment.name},"/>
							</c:forEach>
						</span>
						<c:forEach var="assignment" items="${co.assignments}" >
							<a class="assignment-link" target="_blank" href="../assignment/list.jsp?name=${assignment.name}">
								<c:out value="${assignment.name}"/>
							</a>,
						</c:forEach>
					</td>
					<td>
						<span id="co-coda${cCounter.index}" style="display:none;">
							<c:forEach var="coda" items="${co.courseOutcomeDirectAssessments}" >
								<c:out value="${coda.id},"/>
							</c:forEach>
						</span>
						<c:forEach var="coda" items="${co.courseOutcomeDirectAssessments}" >
							<a class="coda-link" target="_blank" href="../courseoutcomedirectassessment/list.jsp?id=${coda.id}">
								<c:out value="${coda.id}"/>
							</a>,
						</c:forEach>
					</td>
                    <td><input name="ViewCourseOffering" type="button" value="View/Modify Course Offering" onclick="showInfo(${cCounter.index})" /></td> 
                </tr>
            </c:forEach>
        </table>
    </div>
        <div id="updatePopup" style="visibility:hidden">
            <form id="Change" action="controller" method="post" style="position:initial !important" enctype="multipart/form-data">
                <input type="hidden" name="_method" value="put" />
                <input id="index-update" type="hidden" name="index" value="" />
                Semester:
                <select id="semester-update" name="semester">
                    <c:forEach var="item" items="${semesterList.list}">
                        <option>
                            ${item.semester.year}/${item.semester.term}
                        </option>
                    </c:forEach>
                </select><br />
                Course:
                <select id="course-update" name="course">
                    <c:forEach var="course" items="${courseList.list}">
                        <option class="selected-course-id">${course.id}</option>
                    </c:forEach>
                </select><br />
                Instructor:
                <select id="instructor-update" name="instructor">
                    <c:forEach var="instructor" items="${userList.list}">
                        <option class="selected-instructor-username">${instructor.username}</option>
                    </c:forEach>
                </select><br />
                Change Syllabus: <input type="file" name="syllabus"/><br />
                Change Schedule of Lectures: <input type="file" name="lectureschedule"/><br />
                Change End-Of-Semester Report: <input type="file" name="endofsemesterreport"/><br />
                Change Course Coordinator Report: <input type="file" name="coursecoordinatorreport"/><br />
                Change CIC Report: <input type="file" name="cicreport"/><br />
                Add Lecture Notes: <input type="file" name="lecturenotes" multiple/><br /> 
                <div id="label1">Assignments:</div> 
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
                                <div id="label2">CourseOutcome</div> <div id="label22">DirectAssessments:</div> 
				<input id="coda-id-list-update" type="hidden" name="codaIdList" value="" />
			<div id="scroll2">
				<c:forEach var="coda" items="${codaList.list}" varStatus="counter">
					<input 
						onclick="updateCodaIdList(${counter.index})"
						type="checkbox" 
						class="coda-update"
						id="${coda.id}"
						value="${coda.id}"/>${coda.id}<br />
				</c:forEach>
			</div>
                <input id="ModifyButton" type="submit" value="Modify Course Offering" />
            </form>
        </div>
    </body>
</html>