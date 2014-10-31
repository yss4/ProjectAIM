<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.cse308.projectaim.hibernate.types.CourseOutcome"%>
<%@page import="com.cse308.projectaim.beans.AIMEntityListBean"%>
<jsp:useBean id="cOutcomeList" class="com.cse308.projectaim.beans.AIMEntityListBean" scope="session"/>
<jsp:useBean id="soList" class="com.cse308.projectaim.beans.AIMEntityListBean" scope="session" /> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href='http://fonts.googleapis.com/css?family=Prosto+One' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Viga' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="../css/CourseOutcomeModify.css" />
     
        <title>Course Outcome List</title>
        <div style="position:relative; text-align:right;" ><a id="MainPageLink" href="../test.jsp" >Go to menu page</a></div>
        <script type="text/javascript" src="../js/util.js"></script>
        <script type="text/javascript">
			var coSoIdList;
			var soListForUpdate;

			function showInfo(idx) {
                var coId = document.getElementById("co-id"+idx).innerHTML;
                var coDescription = document.getElementById("co-description"+idx).innerHTML;
                var coRationale = document.getElementById("co-rationale"+idx).innerHTML;
                var coAssessed = document.getElementById("co-assessed"+idx).innerHTML;
                var coStudentOutcome = document.getElementById("co-studentoutcome"+idx).innerHTML;

				document.getElementById("index-update").value = idx;
				document.getElementById("Id-update").value = coId;
				document.getElementById("Description-update").value = coDescription;
				document.getElementById("Rationale-update").value = coRationale;
                document.getElementById("Assessed-update").checked 
                    = (coAssessed === 'true') ? true : false;
				document.getElementById("studentoutcome-update").value = coStudentOutcome;

                document.getElementById("updatePopup").style.visibility = "visible";
			}
		</script>
    </head>
    <body>
	<div id="banner">Modify Course Outcome</div><br />
        <div id="Title">Course Outcomes:</div><br /><br /> 
    <div id="Table">    
        <form id="" action="controller" method="post">
            <input type="hidden" name="_method" value="get" />
            <table border="1">
                <tr>
                    <th>Index</th>
                    <th>Id</th>
                    <th>Description</th>
                    <th>Rationale</th>
                    <th>Assessed</th>
                    <th>StudentOutcome</th>
				</tr>
				<c:forEach var="co" items="${cOutcomeList.list}" varStatus="counter">
					<tr>
						<td><span id="counter"><c:out value="${counter.count}"/></span></td>
                        <td><span id="co-id${counter.index}"><c:out value="${co.id}" /></span></td>
                        <td><span id="co-description${counter.index}"><c:out value="${co.description}" /></span></td>
                        <td><span id="co-rationale${counter.index}"><c:out value="${co.rationale}" /></span></td>
                        <td><span id="co-assessed${counter.index}"><c:out value="${co.assessed}" /></span></td>
						<td><span id="co-studentoutcome${counter.index}"><c:out value="${co.studentOutcome.studentOutcomeId}" /></span></td>
                        <td><input type="button" value="View/Modify CourseOutcome" onclick="showInfo(${counter.index})" /></td> 
					</tr>
				</c:forEach>
			</table>
		</form>
        <div id="updatePopup" style="visibility:hidden">
            <form id="Change" action="controller" method="post">
                <input type="hidden" name="_method" value="put" />
                <input id="index-update" type="hidden" name="index" value="" />
                Identifier: <input id="Id-update" type="text" name="identifier" value="" /><br />
                Description: <input id="Description-update" type="text" name="description" value="" /><br />
                Rationale:  <input id="Rationale-update" type="text" name="rationale" value="" /><br />
                Assessed: <input id="Assessed-update" type="checkbox" name="assessed" value="true"/><br />
                </br>
                Student Outcome: 
                <select id="studentoutcome-update" name="studentoutcome" >
                    <c:forEach var="so" items="${soList.list}" varStatus="counter">
						<option value="${so.studentOutcomeId}">${so.studentOutcomeId}</option>
                    </c:forEach>
                </select><br />
                <input id="ModifyButton" type="submit" value="Modify CourseOutcome" />
			</form>
		</div>

    </body>
</html>