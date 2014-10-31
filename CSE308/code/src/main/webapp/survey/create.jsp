<%@page import="com.cse308.projectaim.beans.SurveyBean"%>
<%@page import="com.cse308.projectaim.beans.ResultBean"%>
<jsp:useBean id="surveyBean" class="com.cse308.projectaim.beans.SurveyBean" scope="session" />
<jsp:useBean id="resultBean" class="com.cse308.projectaim.beans.ResultBean" scope="session" />
<jsp:useBean id="semesterList" class="com.cse308.projectaim.beans.AIMEntityListBean" scope="session" />
<jsp:useBean id="peoList" class="com.cse308.projectaim.beans.AIMEntityListBean" scope="session" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
        resultBean.setRequestName("Create Survey");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href='http://fonts.googleapis.com/css?family=Prosto+One' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Viga' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="../css/CreateSurvey.css" />
        <script type="text/javascript" src="../js/util.js"></script>
        <title>Create Survey</title>
        <div style="position:relative; text-align:right;" ><a id="MainPageLink" href="../test.jsp" >Go to menu page</a></div>
        <script type="text/javascript">
            var surveyDpIdList;
            var dpListForUpdate;

            surveyDpIdList = new Array();
            dpListForUpdate = document.getElementsByClassName("dp-update");
            
            function updateDpIdList(idx) {
                var selectedId = dpListForUpdate[idx].id;
                surveyDpIdList = updateIdList(selectedId, surveyDpIdList);
                console.log(surveyDpIdList);
                document.getElementById("dp-id-list-update").value = surveyDpIdList;
            }

			var peoLevelMap = new JMap(); 
			var peoListForUpdate = document.getElementsByClassName("peo-update");
			var levelListForUpdate = document.getElementsByClassName("attainmentlevel-update");

			function updatePeo(idx) {
				var selectedId = peoListForUpdate[idx].id;
				var level = document.getElementById(selectedId+"level").value;
				
				if(peoLevelMap.containsKey(selectedId) && !peoListForUpdate[idx].checked) {
					peoLevelMap.remove(selectedId);
				} else if(!peoLevelMap.containsKey(selectedId) && peoListForUpdate[idx].checked){
					peoLevelMap.put(selectedId, level);
				}
				saveDataInList();
			}	

			function updateLevel(idx) {
				var selectedId = peoListForUpdate[idx].id;
				if(!peoListForUpdate[idx].checked) {
					return;
				}
				var level = document.getElementById(selectedId+"level").value;
				peoLevelMap.put(selectedId, level);
				saveDataInList();
			}

			function saveDataInList() {
				var keys = peoLevelMap.keys();
				var surveyPeoIdList = new Array();
				var surveyLevelList = new Array();
				for(var i = 0; i < keys.length; ++i)  {
					surveyPeoIdList[i] = keys[i];
					surveyLevelList[i] = peoLevelMap.get(keys[i]);
				}
				
				console.log(surveyPeoIdList);
				console.log(surveyLevelList);
				document.getElementById("peo-id-list-update").value = surveyPeoIdList;
				document.getElementById("level-id-list-update").value = surveyLevelList;
			}
        </script>        
    </head>
    <body>
        <div id="banner">Upload Survey to AIM</div>
        <form id="CreateSurveyForm" action="controller" method="post" enctype="multipart/form-data">
            <input type="hidden" name="_method" value="post" />
            Semester:
            <select name="semester" id="Semester">
                <c:forEach var="item" items="${semesterList.list}">
                    <option>
                        ${item.semester.year}/${item.semester.term}
                    </option>
                </c:forEach>
            </select><br /><br />
            
            Group Surveyed: 
            <select Id="GroupSurveyed" name="surveygroup">
                <option value="alumni">Alumni</option>
                <option value="employers">Employers</option>
            </select><br />
            Survey Initiator: 
            <select Id="Initiator" name="initiator">
                <option value="department">Department</option>
                <option value="college">College</option>
                <option value="university">University</option>
            </select><br />
            <!-- They should be able to view a list of all PEOs here?  Or a file?  -->
            <div id="PEOA">PEO Attainment</div>
            <div id="Levels"> Levels:</div> 
            <input id="PEO" type="text" name="peoattainmentlevel" value=""/><br />
            Results: <input id="FileBrowseTextbox" type="file" name="result"/>
         
            <div id="DP"> Degree </div>Programs: <br />
            <input id="dp-id-list-update" type="hidden" name="dpIdList" value="" />
            <div id="scroll">
            <c:forEach var="dp" items="${dpList.list}" varStatus="counter">
                <input 
                    onclick="updateDpIdList(${counter.index})"
                    type="checkbox" 
                    class="dp-update"
                    id="${dp.degreeProgramId}"
                    value="${dp.degreeProgramId}"/>${dp.degreeProgramId}<br />
            </c:forEach>
         </div>
            <div id="PEOA">PEO Attainment Levels</div>
            <input id="peo-id-list-update" type="hidden" name="peoIdList" value="" />
            <input id="level-id-list-update" type="hidden" name="levelIdList" value="" />
            <c:forEach var="peo" items="${peoList.list}" varStatus="counter">
                <input 
                    onclick="updatePeo(${counter.index})"
                    type="checkbox" 
					checked="off"
                    class="peo-update"
                    id="${peo.id}"
                    value="${peo.id}"/>
				${peo.id}
				<select onchange="updateLevel(${counter.index})" 
						name="attainmentlevel"
						class="attainmentlevel-update" 
						id="${peo.id}level">
					<option>0.0</option> 
					<option>0.5</option> 
					<option>1.0</option> 
					<option>1.5</option> 
					<option>2.0</option> 
				</select>
				<br />
            </c:forEach>
            Results: <input type="file" name="result"/>
            <input id="SubmitButton" type="submit" value="Create Survey" />
        </form>

		<script type="text/javascript">
			var peoListForUpdate = document.getElementsByClassName("peo-update");
			for(var i = 0; i < peoListForUpdate.length; ++i) {
				peoListForUpdate[i].checked = false;
			}

		</script>
    </body>
</html>
