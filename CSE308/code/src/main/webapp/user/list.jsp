<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.cse308.projectaim.hibernate.types.User"%>
<%@page import="com.cse308.projectaim.beans.AIMEntityListBean"%>
<jsp:useBean id="userList" class="com.cse308.projectaim.beans.AIMEntityListBean" scope="session" />
<jsp:useBean id="dpList" class="com.cse308.projectaim.beans.AIMEntityListBean" scope="session"/>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="../js/util.js"></script>
        
        <link href='http://fonts.googleapis.com/css?family=Prosto+One' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Viga' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="../css/UserModify.css" />
        <div style="position:relative; text-align:right;" ><a id="MainPageLink" href="../test.jsp" >Go to menu page</a></div>
        <script type="text/javascript">
            var userDpIdList;
            var dpListForUpdate;

            function showInfo(idx) {
                var index = idx-1;
                var userUsername = document.getElementById("user-username"+idx).innerHTML;
                var userPassword = document.getElementById("user-password"+idx).innerHTML;
                var userEmail = document.getElementById("user-email"+idx).innerHTML;
                var userCicmemeber = document.getElementById("user-cicmember"+idx).innerHTML;
                var userEvaluator = document.getElementById("user-evaluator"+idx).innerHTML;
                //var userEvaluatorStartDate = document.getElementById("user-evaluatorstartdate"+idx);
                //var userEvaluatorEndDate = document.getElementById("user-evaluatorenddate"+idx);
                var userDegreePrograms = document.getElementById("user-degreeprograms"+idx).innerHTML;
                
                document.getElementById("index-update").value = index;
                document.getElementById("Username-update").value = userUsername;
                document.getElementById("Password-update").value = userPassword;
                document.getElementById("Email-update").value = userEmail;
                document.getElementById("CIC_Member-update").checked 
                    = (userCicmemeber === 'true') ? true : false;
                document.getElementById("Evaluator-update").checked 
                    = (userEvaluator === 'true') ? true : false;
				
                userDpIdList = new Array();
                dpListForUpdate = document.getElementsByClassName("degree-program-update");
                revisingCheckbox(userDpIdList, userDegreePrograms, dpListForUpdate);
                
                document.getElementById("dp-id-list-update").value = userDpIdList;
                document.getElementById("updatePopup").style.visibility = "visible";
            }

            function updateDpIdList(idx) {
                var selectedId = dpListForUpdate[idx].id;
                userDpIdList = updateIdList(selectedId, userDpIdList);
                console.log(userDpIdList);
                document.getElementById("dp-id-list-update").value = userDpIdList;
            }
        </script>

    </head>
    <body>
        <div id="banner">Modify Users</div><br />
        <div id="Title">Users:</div><br /><br /> 
    <div id="Table">    
            <form id="" action="controller" method="post">
           <input type="hidden" name="_method" value="get" />
            <table id="TableList" border="1">
                <tr>
                    <th>Index</th>
                    <th>User name</th>
                    <th><!--Password--></th>
                    <th>Email Address</th>
                    <th>CIC Member</th>
                    <th>Evaluator</th>
                    <th>Degree Programs</th>
                    <!--<th>Evaluator Start Date</th>-->
                    <!--<th>Evaluator End Date</th>-->
                    <th></th>
                </tr>
                <c:forEach var="user" items="${userList.list}" varStatus="userCounter">
                    <tr>
                        <td><span id="userCounter"><c:out value="${userCounter.count}"/></span></td>
                        <td><span id="user-username${userCounter.count}"><c:out value="${user.username}" /></span></td>
                        <td><span style="display: none;" id="user-password${userCounter.count}"><c:out value="${user.password}" /></span></td>
                        <td><span id="user-email${userCounter.count}"><c:out value="${user.emailAddress}" /></span></td>
                        <td><span id="user-cicmember${userCounter.count}"><c:out value="${user.cicMemberStatus}" /></span></td>
                        <td><span id="user-evaluator${userCounter.count}"><c:out value="${user.evaluatorStatus}" /></span></td>
                        <td>
                            <span id="user-degreeprograms${userCounter.count}">
                                <c:forEach var="user_degreeprogram" items="${user.degreePrograms}" >
                                    <c:set var="user_degreeProgram_degreeProgramId" value="${user_degreeprogram.degreeProgramId}"/>
                                    <c:out value="${user_degreeprogram.degreeProgramId},"/>
                                </c:forEach>
                            </span>
                        </td>
                        <td><span id="user-evaluatorstartdate${userCounter.count}"><c:out value="${user.evaluationStartDate}" /></span></td>
                        <td><span id="user-evaluatorenddate${userCounter.count}"><c:out value="${user.evaluationEndDate}" /></span></td>
                        <td><input name="ViewUser" type="button" value="View/Modify User" onclick="showInfo(${userCounter.count})" /></td> 
                    </tr>
                </c:forEach>
            </table>
        </form>
    </div>
        <div id="updatePopup" style="visibility:hidden">
            <form id="Change" action="controller" method="post">
                <input type="hidden" name="_method" value="put" />
                <input id="index-update" type="hidden" name="index" value="" />
                User name: <input id="Username-update" type="text" name="username" value="" /><br />
                Password:  <input id="Password-update" type="password" name="password" value="" /><br />
                Email Address: <input id="Email-update" type="text" name="email" value="" /><br />
                CIC Member: <input id="CIC_Member-update" type="checkbox" name="cicmember" value="true" /><br />
                Evaluator: <input id="Evaluator-update" type="checkbox" name="evaluator" value="true" /><br />
                <br />
                Degree Programs: <br />
                <input id="dp-id-list-update" type="hidden" name="dpIdList" value="" />
             <div id="scroll">
                <c:forEach var="dp" items="${dpList.list}" varStatus="loopCounter">
                    <input
						type="checkbox"
                        onclick="updateDpIdList(${loopCounter.index})"
                        class="degree-program-update" 
                        id="${dp.degreeProgramId}" 
                        type="checkbox"
                       
                     />
                    ${dp.degreeProgramId}</br>
                </c:forEach>
             </div>
                      
                <input id="ModifyButton" type="submit" value="Modify User" />
            </form>
        </div>     
    </body>
</html>
