<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.cse308.projectaim.hibernate.types.Survey"%>
<%@page import="com.cse308.projectaim.beans.AIMEntityListBean"%>
<jsp:useBean id="surveyList" class="com.cse308.projectaim.beans.AIMEntityListBean" scope="session"/>
<jsp:useBean id="semesterList" class="com.cse308.projectaim.beans.AIMEntityListBean" scope="session" />
<jsp:useBean id="dpList" class="com.cse308.projectaim.beans.AIMEntityListBean" scope="session" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Survey List</title>
        <div style="position:relative; text-align:right;" ><a id="MainPageLink" href="../test.jsp" >Go to menu page</a></div>
        <script type="text/javascript" src="../js/util.js"></script>
        <script type="text/javascript">
            var surveyDpIdList;
            var dpListForUpdate;

            function showInfo(idx) {
                var group = document.getElementById("survey-group"+idx).innerHTML;
                var initiator = document.getElementById("survey-initiator"+idx).innerHTML;
                var semester = document.getElementById("survey-semester"+idx).innerHTML.trim();
                var degreePrograms = document.getElementById("survey-degreeprograms"+idx).innerHTML;
				var levelPairs = document.getElementById("survey-peo-attainment-level"+idx).innerHTML;
				//console.log(levelPairs);

                document.getElementById("index-update").value = idx;
                document.getElementById("GroupSurveyed-update").value = group;
                document.getElementById("Initiator-update").value = initiator;
                document.getElementById("survey-semester-update").value = semester;

                surveyDpIdList = new Array();
                dpListForUpdate = document.getElementsByClassName("dp-update");
                revisingCheckbox(surveyDpIdList, degreePrograms, dpListForUpdate);
				
				var peoListForUpdate = document.getElementsByClassName("peo-update");
				for(var i = 0; i < peoListForUpdate.length; ++i) {
					peoListForUpdate[i].checked = false;
				}

				var peoIdListUpdate = new Array();
				var levelListUpdate = new Array();
				var pairList = levelPairs.split(",");
				for(var i = 0; i < pairList.length-1; ++i) {
					var pair = pairList[i].trim().split("=");
					var peoId = pair[0];
					var level = pair[1];

					document.getElementById(peoId).checked = true;
					document.getElementById(peoId+"level").value = level;
					//console.log(peoId + "=" + level);
					peoIdListUpdate.add(peoId);
					levelListUpdate.add(level);
				}
				document.getElementById("peo-id-list-update").value = peoIdListUpdate;
				document.getElementById("level-id-list-update").value = levelListUpdate;

                document.getElementById("dp-id-list-update").value = surveyDpIdList;
                document.getElementById("updatePopup").style.visibility = "visible";
            }

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
        Surveys: 
        <form id="" action="controller" method="post">
            <input type="hidden" name="_method" value="get" />
            <table>
                <tr>
                    <th>Index</th>
                    <th>Id</th>
                    <th>Semester</th>
                    <th>Degree Programs</th>
                    <th>Survey Group</th>
                    <th>Initiator</th>
                    <th>Peo Attainment Level</th>
                    <th>Result</th>
                    <th></th>
                </tr>
                <c:forEach var="survey" items="${surveyList.list}" varStatus="counter">
                    <tr>
                        <td><span id="surveyCounter"><c:out value="${counter.count}"/></span></td>
                        <td><span id="survey-id${counter.index}"><c:out value="${survey.id}" /></span></td>
                        <td>
                            <span id="survey-semester${counter.index}">
                                <c:out value="${survey.semester.semester.year}/${survey.semester.semester.term}" />
                            </span>
                        </td>
                        <td>
                            <span id="survey-degreeprograms${counter.index}">
                                <c:forEach var="survey_dp" items="${survey.degreePrograms}" >
                                    <c:out value="${survey_dp.degreeProgramId},"/>
                                </c:forEach>
                            </span>
                        </td>
                        <td><span id="survey-group${counter.index}"><c:out value="${survey.surveyGroup}" /></span></td>
                        <td><span id="survey-initiator${counter.index}"><c:out value="${survey.initiator}" /></span></td>
                        <td>
							<span id="survey-peo-attainment-level${counter.index}">
								<c:forEach var="attainmentlevel" items="${survey.attainmentLevels}" >
									<c:out value="${attainmentlevel.peo.id}=${attainmentlevel.level}, " />
								</c:forEach>
							</span>
						</td>
                        <td>
							<a target="_blank" href="../file/controller?fileId=${survey.results.id}">
								<c:out value="${survey.results.fileName}" />
							</a>
                        </td>
                        <td><input type="button" value="View/Modify Survey" onclick="showInfo(${counter.index})" /></td> 
                    </tr>
                </c:forEach>
            </table>
        </form>
        <div id="updatePopup" style="visibility:hidden">
            <form id="Change" action="controller" method="post" enctype="multipart/form-data">
                <input type="hidden" name="_method" value="put" />
                <input id="index-update" type="hidden" name="index" value="" />
                Semester:
                <select id="survey-semester-update" name="semester">
                    <c:forEach var="item" items="${semesterList.list}">
                        <option>
                            ${item.semester.year}/${item.semester.term}
                        </option>
                    </c:forEach>
                </select><br />
                Degree Programs: <br />
                <input id="dp-id-list-update" type="hidden" name="dpIdList" value="" />
                <c:forEach var="dp" items="${dpList.list}" varStatus="counter">
                    <input 
                        onclick="updateDpIdList(${counter.index})"
                        class="dp-update" 
                        id="${dp.degreeProgramId}" 
                        type="checkbox"  />
                    ${dp.degreeProgramId}<br />
                </c:forEach>
                Group Surveyed: 
                <select id="GroupSurveyed-update" name="surveygroup">
                    <option value="alumni">alumni</option>
                    <option value="employers">employers</option>
                </select><br />
                Survey Initiator: 
                <select id="Initiator-update" name="initiator">
                    <option value="department">department</option>
                    <option value="college">college</option>
                    <option value="university">university</option>
                </select><br />
				<div id="PEOA">PEO Attainment Levels</div>
				<input id="peo-id-list-update" type="hidden" name="peoIdList" value="" />
				<input id="level-id-list-update" type="hidden" name="levelIdList" value="" />
				<c:forEach var="peo" items="${peoList.list}" varStatus="counter">
					<input 
						onclick="updatePeo(${counter.index})"
						type="checkbox" 
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
                <input id="ModifyButton" type="submit" value="Modify Survey" />
            </form>
        </div>
    </body>
</html>