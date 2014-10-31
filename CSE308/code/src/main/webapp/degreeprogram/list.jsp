<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.cse308.projectaim.hibernate.types.DegreeProgram"%>
<%@page import="com.cse308.projectaim.beans.AIMEntityListBean"%>
<jsp:useBean id="dpList" class="com.cse308.projectaim.beans.AIMEntityListBean" scope="session"/>
<jsp:useBean id="peoList" class="com.cse308.projectaim.beans.AIMEntityListBean" scope="session"/>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href='http://fonts.googleapis.com/css?family=Prosto+One' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Viga' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="../css/DegreeProgramModify.css" />
        <script type="text/javascript" src="../js/util.js"></script>
        <title>Modify Degree Program</title>
        <div style="position:relative; text-align:right;" ><a id="MainPageLink" href="../test.jsp" >Go to menu page</a></div>
        <script type="text/javascript">

            var dpPeoIdList;
            var peoListForUpdate;

            function showInfo(idx) {
                var dpId = document.getElementById("dp-id"+idx).innerHTML;
                var desc = document.getElementById("desc"+idx).innerHTML;
                var depart = document.getElementById("depart"+idx).innerHTML;
                var dpPeos = document.getElementById("dp-peos"+idx).innerHTML;
				
                document.getElementById("index-update").value=idx;
                document.getElementById("id-update").value = dpId;
                document.getElementById("desc-update").value = desc;
                document.getElementById("depart-update").value = depart;

                dpPeoIdList = new Array();
                peoListForUpdate = document.getElementsByClassName("peo-update");
                revisingCheckbox(dpPeoIdList, dpPeos, peoListForUpdate);

                document.getElementById("peo-id-list-update").value = dpPeoIdList;
                document.getElementById("updatePopup").style.visibility = "visible";
            }

            function updatePeoIdList(idx) {
                var selectedId = peoListForUpdate[idx].id;
                dpPeoIdList = updateIdList(selectedId, dpPeoIdList);
                console.log(dpPeoIdList);
                document.getElementById("peo-id-list-update").value = dpPeoIdList;
            }
        </script>        
    </head>
    <body>
        <div id="banner">Modify Degree Program</div><br />
        <div id="Title">Degree Programs:</div><br /><br /> 
   <div id="Table">
        <table id="TableList" border="1">
            <tr>
                <th>Id</th>
                <th>description</th>
                <th>department</th>
                <th>PEOs</th>
                <th></th>
            </tr>
            <c:forEach var="dp" items="${dpList.list}" varStatus="dpCounter">
                <tr>
                    <td><span id="dp-id${dpCounter.index}"><c:out value="${dp.degreeProgramId}" /></span></td>
                    <td><span id="desc${dpCounter.index}"><c:out value="${dp.description}" /></span></td>
                    <td><span id="depart${dpCounter.index}"><c:out value="${dp.department}" /></span></td>
                    <td>
                        <span id="dp-peos${dpCounter.index}">
                            <c:forEach var="dp_peo" items="${dp.PEOs}" >
                                <c:set var="dp_peo_id" value="${dp_peo.id}" />
                                <c:out value="${dp_peo_id}," />
                            </c:forEach>
                        </span>
                    </td>
                    <td>
                        <span id="dp-studentoutcomes${dpCounter.index}">
                            <c:forEach var="dp_studentoutcome" items="${dp.studentOutcomes}" >
                                <c:set var="dp_studentoutcome_id" value="${dp_studentoutcome.studentOutcomeId}" />
                                <c:out value="${dp_studentoutcome.studentOutcomeId}," />
                            </c:forEach>
                        </span>
                    </td>
                    <td><input name="ViewDegreeProgram" type="button" value="View/Modify DegreeProgram" onclick="showInfo(${dpCounter.index})" /></td> 
                </tr>
            </c:forEach>
        </table>
   </div>
        <div id="updatePopup" style="visibility:hidden;">
            <form id="TextForm" action="controller" method="post" style="position:initial !important">
                <input type="hidden" name="_method" value="put" />
                <input id="index-update" type="hidden" name="index" value="" />
                Identifier: <input id="id-update" type="text" name="identifier" value="" /><br />
                Description:  <input id="desc-update" type="text" name="description" value="" /><br />
                Department: <input id="depart-update" type="text" name="department" value="" /><br />
                PEOs: <br />
                <input id="peo-id-list-update" type="hidden" name="peoIdList" value="" />
            <div id="scroll">
                <c:forEach var="peo" items="${peoList.list}" varStatus="counter">
                    <input
                        onclick="updatePeoIdList(${counter.index})"
                        class="peo-update"
                        id="${peo.id}"
                        type="checkbox" />
                    ${peo.id}<br />
                </c:forEach>
            </div>
                <input id="ModifyButton" type="submit" value="Modify Degree Program" />
            </form>
        </div>
    </body>
</html>
