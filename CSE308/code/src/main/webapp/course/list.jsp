<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.cse308.projectaim.hibernate.types.Course"%>
<%@page import="com.cse308.projectaim.beans.AIMEntityListBean"%>
<jsp:useBean id="courseList" class="com.cse308.projectaim.beans.AIMEntityListBean" scope="session"/>
<jsp:useBean id="dpList" class="com.cse308.projectaim.beans.AIMEntityListBean" scope="session" /> 
<jsp:useBean id="cOutcomeList" class="com.cse308.projectaim.beans.AIMEntityListBean" scope="session" /> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href='http://fonts.googleapis.com/css?family=Prosto+One' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Viga' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="../css/CourseModify.css" />

        <script type="text/javascript" src="../js/util.js"></script>
        <title>View Course List</title>
        <div style="position: relative; text-align: right;"><a id="MainPageLink" href="../test.jsp" >Go to menu page</a></div>
        <script type="text/javascript">
            var courseDpIdList;
            var dpListForUpdate;
            var courseCoIdList;
            var coListForUpdate;
            var courseAccIdList;
            var accListForUpdate;

            function showInfo(idx) {
                var courseId = document.getElementById("course-id"+idx).innerHTML;
                var name = document.getElementById("name"+idx).innerHTML;
                var courseDegreeprograms = document.getElementById("course-degreeprograms"+idx).innerHTML;
                var courseCourseoutcomes = document.getElementById("course-courseoutcomes"+idx).innerHTML;
                var coursePrimarycoordinator = document.getElementById("course-primarycoordinator"+idx).innerHTML.trim();
                var courseAltercoordinators = document.getElementById("course-alternatecoordinators"+idx).innerHTML;
				
                document.getElementById("index-update").value=idx;
                document.getElementById("id-update").value = courseId;
                document.getElementById("name-update").value = name;
                document.getElementById("primarycoordinator-update").value = coursePrimarycoordinator;
                
                courseDpIdList = new Array();
                courseCoIdList = new Array();
                courseAccIdList = new Array();
                dpListForUpdate = document.getElementsByClassName("dp-update");
                revisingCheckbox(courseDpIdList, courseDegreeprograms, dpListForUpdate);
                coListForUpdate = document.getElementsByClassName("co-update");
                revisingCheckbox(courseCoIdList, courseCourseoutcomes, coListForUpdate);
                accListForUpdate = document.getElementsByClassName("acc-update");
                revisingCheckbox(courseAccIdList, courseAltercoordinators, accListForUpdate);

                document.getElementById("dp-id-list-update").value = courseDpIdList;
                document.getElementById("co-id-list-update").value = courseCoIdList;
                document.getElementById("primary-course-coordinator-name-update").value = coursePrimarycoordinator;
                document.getElementById("acc-id-list-update").value = courseAccIdList;
                document.getElementById("updatePopup").style.visibility = "visible";
            }

            function updateDpIdList(idx) {
                var selectedId = dpListForUpdate[idx].id;
                courseDpIdList = updateIdList(selectedId, courseDpIdList);
				console.log(courseDpIdList);
                document.getElementById("dp-id-list-update").value = courseDpIdList;
            }
            
            function updateCoIdList(idx) {
                var selectedId = coListForUpdate[idx].id;
                courseCoIdList = updateIdList(selectedId, courseCoIdList);
				console.log(courseCoIdList);
                document.getElementById("co-id-list-update").value = courseCoIdList;
            }
            
            function updatePrimaryCourseCoordinator(selectedIndex) {
                document.getElementById("primary-course-coordinator-name-update").value = 
                    document.getElementsByTagName("option")[selectedIndex].value;
            }
            
            function updateAccIdList(idx) {
                var selectedId = accListForUpdate[idx].id;
                courseAccIdList = updateIdList(selectedId, courseAccIdList);
				console.log(courseAccIdList);
                document.getElementById("acc-id-list-update").value = courseAccIdList;
            }
        </script>        
    </head>
    <body>
        <div id="banner">Modify Student Outcomes</div><br />
        <div id="Title">Student Outcomes:</div><br /><br /> 
     <div id="Table">
        <table id="TableList" border="1">
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Degree Programs</th>
                <th>Course Outcomes</th>
                <th>Primary Course Coordinator</th>
                <th>Alternate Course Coordinators</th>
            </tr>
            <c:forEach var="course" items="${courseList.list}" varStatus="cCounter">
                <tr>
                    <td><span id="course-id${cCounter.index}"><c:out value="${course.id}" /></span></td>
                    <td><span id="name${cCounter.index}"><c:out value="${course.name}" /></span></td>
                    <td>
                        <span id="course-degreeprograms${cCounter.index}">
                            <c:forEach var="course_degreeprogram" items="${course.degreePrograms}" >
                                <c:set var="course_degreeprogram_id" value="${course_degreeprogram.degreeProgramId}" />
                                <c:out value="${course_degreeprogram_id}," />
                            </c:forEach>
                        </span>
                    <td>
                        <span id="course-courseoutcomes${cCounter.index}">
                            <c:forEach var="course_courseoutcomes" items="${course.courseOutcomes}" >
                                <c:set var="course_courseoutcomes_id" value="${course_courseoutcomes.id}" />
                                <c:out value="${course_courseoutcomes_id}," />
                            </c:forEach>
                        </span>
                    </td>
                    <td>
                        <span id="course-primarycoordinator${cCounter.index}">
                            <c:set var="course_primarycoordinator" value="${course.primaryCourseCoordinator.username}"/>
                            <c:out value="${course.primaryCourseCoordinator.username}"/>
                        </span>
                    </td>
                    <td>
                        <span id="course-alternatecoordinators${cCounter.index}"> 
                            <c:forEach var="course_altercoordinators" items="${course.alternateCourseCoordinator}" >
                                <c:set var="course_altercoordinators_id" value="${course_altercoordinators.username}" />
                                <c:out value="${course_altercoordinators_id}," />
                            </c:forEach>
                        </span>
                    </td>
                    <td><input name="ViewCourse" type="button" value="View/Modify Course" onclick="showInfo(${cCounter.index})" /></td> 
                </tr>
            </c:forEach>
        </table>
     </div>
        <div id="updatePopup" style="visibility:hidden">
            <form id="Change" action="controller" method="post" style="position:initial !important">
                <input type="hidden" name="_method" value="put" />
                <input id="index-update" type="hidden" name="index" value="" />
                Identifier: <input id="id-update" type="text" name="identifier" value="" /><br />
                Name:  <input id="name-update" type="text" name="name" value="" /><br />
                <div id="PC">  Primary Course</div> </br>Coordinator:
                <input 
                    id="primary-course-coordinator-name-update" 
                    type="hidden" 
                    name="primaryCourseCoordinatorName" 
                    value="" />
                <select id ="primarycoordinator-update" name="primarycoursecoordinator" onchange="updatePrimaryCourseCoordinator(this.selectedIndex)">
                    <c:forEach var="pcc" items="${userList.list}" varStatus="counter">
                        <option value="${pcc.username}">${pcc.username}</option>
                    </c:forEach>
                </select><br />
           
                <div id="label1">Degree</div> <div id="label12">Programs:</div> <br />
          <input id="dp-id-list-update" type="hidden" name="dpIdList" value="" />
             <div id="scroll1"> 
                <c:forEach var="dp" items="${dpList.list}" varStatus="counter">
                    <input
                        onclick="updateDpIdList(${counter.index})"
                        class="dp-update"
                        id="${dp.degreeProgramId}"
                        type="checkbox" />
                    ${dp.degreeProgramId}<br />
                </c:forEach>
             </div>
                 <div id="label2"> Course</div> <div id="label22">Outcomes:</div> <br />
           <input id="co-id-list-update" type="hidden" name="coIdList" value="" />
             <div id="scroll2">
                <c:forEach var="co" items="${cOutcomeList.list}" varStatus="counter">
                    <input
                        onclick="updateCoIdList(${counter.index})"
                        class="co-update"
                        id="${co.id}"
                        type="checkbox" />
                    ${co.id}<br />
                </c:forEach>
             </div>
               <div id="label3">   Alternate Course</div> <div id="label32">Coordinators:</div><br/>
           <input id="acc-id-list-update" type="hidden" name="accIdList" value="" />
              <div id="scroll3">
                <c:forEach var="acc" items="${userList.list}" varStatus="counter">
                    <input
                        onclick="updateAccIdList(${counter.index})"
                        class="acc-update"
                        id="${acc.username}"
                        type="checkbox" />
                    ${acc.username}<br />
                </c:forEach>
              </div>
                <input id="ModifyButton" type="submit" value="Modify Course" />
            </form>
        </div>
    </body>
</html>