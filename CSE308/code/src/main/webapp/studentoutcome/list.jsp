<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.cse308.projectaim.hibernate.types.StudentOutcome"%>
<%@page import="com.cse308.projectaim.beans.AIMEntityListBean"%>
<jsp:useBean id="soList" class="com.cse308.projectaim.beans.AIMEntityListBean" scope="session" /> 
<jsp:useBean id="cOutcomeList" class="com.cse308.projectaim.beans.AIMEntityListBean" scope="session" /> 
<jsp:useBean id="dpList" class="com.cse308.projectaim.beans.AIMEntityListBean" scope="session" /> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href='http://fonts.googleapis.com/css?family=Prosto+One' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Viga' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="../css/StudentOutcomeModify.css" />
        <title>Student Outcome List</title>
        <div style="position:relative; text-align:right;" ><a id="MainPageLink" href="../test.jsp" >Go to menu page</a></div>
        <script type="text/javascript" src="../js/util.js"></script>
        <script type="text/javascript">
            var soCoIdList;
            var coListForUpdate;

            function showInfo(idx) {
                var soId = document.getElementById("so-id"+idx).innerHTML;
                var soSequence = document.getElementById("so-sequence"+idx).innerHTML;
                var soShortname = document.getElementById("so-shortname"+idx).innerHTML;
                var soDescription = document.getElementById("so-description"+idx).innerHTML;
                var soDegreeprogram = document.getElementById("so-degreeprogram"+idx).innerHTML.trim();
                var soCourseOutcomes = document.getElementById("so-courseoutcomes"+idx).innerHTML;
                //var soTargetdirectattainment = document.getElementById("so-targetdirectattainment"+idx);
                //var soTargetsurveyattainment = document.getElementById("so-targetsurveyattainment"+idx);
                
                document.getElementById("index-update").value = idx;
                document.getElementById("Id-update").value = soId;
                document.getElementById("Sequence-update").value = soSequence;
                document.getElementById("Shortname-update").value = soShortname;
                document.getElementById("Description-update").value = soDescription;
                document.getElementById("DegreeProgram-update").value = soDegreeprogram;
                
                //console.log(soDegreeprogram);
                //console.log(document.getElementById("DegreeProgram-update").value);

                soCoIdList = new Array();
                coListForUpdate = document.getElementsByClassName("co-update");
                revisingCheckbox(soCoIdList, soCourseOutcomes, coListForUpdate);

                document.getElementById("co-id-list-update").value = soCoIdList; 
                document.getElementById("dp-id-update").value = soDegreeprogram; 
                document.getElementById("updatePopup").style.visibility = "visible";
            }

            function updateCoIdList(idx) {
                var selectedId = coListForUpdate[idx].id;
                updateIdList(selectedId, soCoIdList);
                console.log(soCoIdList);
                document.getElementById("co-id-list-update").value = soCoIdList;
            }
            
            function updateSelectedDegreeProgram(selectedIndex) {
                console.log(selectedIndex);
                document.getElementById("dp-id-update").value = document.getElementsByTagName("option")[selectedIndex].value;
            }
            
        </script>
    </head>
    <body>
        <div id="banner">Modify Student Outcomes</div><br />
        <div id="Title">Student Outcomes:</div><br /><br /> 
    <div id="Table">
        <form id="" action="controller" method="post">
            <input type="hidden" name="_method" value="get" />
        
            <table id="TableList" border="1">
                <tr>
                    <th>Index</th>
                    <th>Id</th>
                    <th>Sequence</th>
                    <th>Short Name</th>
                    <th>Description</th>
                    <th><!--Target Direct-Assessment Attainment Level in Each Semester--></th>
                    <th><!--Target Survey-Assessment Attainment Level in Each Semester--></th>
                    <th>Degree Program</th>
                    <th>Course Outcomes</th>
                    <th></th>
                </tr>
                <c:forEach var="so" items="${soList.list}" varStatus="soCounter">
                    <tr>
                        <td><span id="soCounter"><c:out value="${soCounter.count}"/></span></td>
                        <td><span id="so-id${soCounter.index}"><c:out value="${so.studentOutcomeId}" /></span></td>
                        <td><span id="so-sequence${soCounter.index}"><c:out value="${so.sequence}" /></span></td>
                        <td><span id="so-shortname${soCounter.index}"><c:out value="${so.shortName}" /></span></td>
                        <td><span id="so-description${soCounter.index}"><c:out value="${so.description}" /></span></td>
                        <td><span id="so-targetdirectattainment${soCounter.index}"><c:out value="" /></span></td>
                        <td><span id="so-targetsurveyattainment${soCounter.index}"><c:out value="" /></span></td>
                        <td><span id="so-degreeprogram${soCounter.index}">
                                <c:out value="${so.degreeProgram.degreeProgramId}" />
                                <c:set var="so_degreeProgramId" value="${so.degreeProgram.degreeProgramId}${soCounter.count}"/>
                            </span></td>
                        <td>
                            <span id="so-courseoutcomes${soCounter.index}">
                                <c:forEach var="so_co" items="${so.courseOutcomes}" >
                                    <c:out value="${so_co.id},"/>
                                </c:forEach>
                            </span>
                        </td>
                        <td><input type="button" value="View/Modify StudentOutcome" onclick="showInfo(${soCounter.index})" /></td> 
                    </tr>
                </c:forEach>
            </table>
        </form>
    </div>
        <div id="updatePopup" style="visibility:hidden">
            <form id="Change" action="controller" method="post">
                <input type="hidden" name="_method" value="put" />
                <input id="index-update" type="hidden" name="index" value="" />
                Identifier: <input id="Id-update" type="text" name="identifier" value="" /><br />
                Sequence:  <input id="Sequence-update" type="text" name="sequence" value="" /><br />
                ShortName: <input id="Shortname-update" type="text" name="shortname" value=""/><br />
                Description: <input id="Description-update" type="text" name="description" value="" /><br />
                <!-- Need to add these fields in model class
				Target Direct-Assessment Attainment Level in Each Semester: <input id="TargetDAAL-update" type="text" name="targetdirectattainment" value="" /><br />
				Target Survey-Assessment Attainment Level in Each Semester: <input id="TargetSAAL-update" type="text" name="targetsurveyattainment" value="" /><br />
                -->
                <br/>
               
                Degree Program: <br />
                <input id="dp-id-update" type="hidden" name="dpId" value=""/>
                <select id="DegreeProgram-update" name="degreeprogram" onchange="updateSelectedDegreeProgram(this.selectedIndex)">
                    <c:forEach var="dp" items="${dpList.list}" varStatus="loopCounter">
						<option value="${dp.degreeProgramId}">${dp.degreeProgramId}</option>
                    </c:forEach>
                </select><br />
                Course Outcomes: <br />
               <input id="co-id-list-update" type="hidden" name="coIdList" value="" /> 
            <div id="scroll">
                 <c:forEach var="co" items="${cOutcomeList.list}" varStatus="counter">
                   <input 
                        onclick="updateCoIdList(${counter.index})"
                        class="co-update" 
                        id="${co.id}" 
                        type="checkbox"  />
                    ${co.id}<br />
                
                </c:forEach>
            </div>
                <input id="ModifyButton" type="submit" value="Modify StudentOutcome" />
            </form>
        </div>  
    </body>
</html>
