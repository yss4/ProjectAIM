<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="minuteList" class="com.cse308.projectaim.beans.AIMEntityListBean" scope="session"/>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="../js/util.js"></script>
        <title>Minute List</title>
        <script type="text/javascript">
            var minuteDpIdList;
            var dpListForUpdate;

            function showInfo(idx) {
                var minuteId = document.getElementById("minute-id"+idx).innerHTML;
                var date = document.getElementById("minute-date"+idx).innerHTML.split(" ")[0];
                var group = document.getElementById("minute-group"+idx).innerHTML;
                var minutes = document.getElementById("minute-minutes"+idx).innerHTML;
                var minuteDegreeprograms = document.getElementById("minute-degreeprograms"+idx).innerHTML;
                
                var year = date.split("-")[0];
                var month = date.split("-")[1];
                var day = date.split("-")[2];
                
                document.getElementById("index-update").value=idx;
                document.getElementById("year-update").value = year;
                document.getElementById("month-update").value = month;
                document.getElementById("day-update").value = day;
                document.getElementById("group-update").value = group;
                //document.getElementById("minutes-update").value = minutes;
                console.log(minutes);
                
                minuteDpIdList = new Array();
                dpListForUpdate = document.getElementsByClassName("dp-update");
                revisingCheckbox(minuteDpIdList, minuteDegreeprograms, dpListForUpdate);
                
                document.getElementById("dp-id-list-update").value = minuteDpIdList;
                document.getElementById("updatePopup").style.visibility = "visible";
            }
            
            function updateDpIdList(idx) {
                var selectedId = dpListForUpdate[idx].id;
                minuteDpIdList = updateIdList(selectedId, minuteDpIdList);
                console.log(minuteDpIdList);
                document.getElementById("dp-id-list-update").value = minuteDpIdList;
            }
        </script>    
    </head>
    <body>
        Minutes:
        <form id="" action="controller" method="post">
            <input type="hidden" name="_method" value="get" />
            <table>
                <tr>
                    <th>Index</th>
                    <th>Id</th>
                    <th>Date</th>
                    <th>Group</th>
                    <th>Minutes</th>
                    <th>Degree Programs</th>
                </tr>
                <c:forEach var="minute" items="${minuteList.list}" varStatus="mCounter">
                    <tr>
                        <td><span id="mCounter"><c:out value="${mCounter.count}"/></span></td>
                        <td><span id="minute-id${mCounter.index}"><c:out value="${minute.id}" /></span></td>
                        <td><span id="minute-date${mCounter.index}"><c:out value="${minute.date}" /></span></td>
                        <td><span id="minute-group${mCounter.index}"><c:out value="${minute.group}" /></span></td>
                        <td><span id="minute-minutes${mCounter.index}"><c:out value="${minute.minutes.id}" /></span></td>
                        <td><span id="minute-degreeprograms${mCounter.index}">
                                <c:forEach var="minute_dp" items="${minute.degreePrograms}" >
                                    <c:out value="${minute_dp.degreeProgramId},"/>
                                </c:forEach>
                            </span></td>
                        <td><input name="ViewMinute" type="button" value="View/Modify Minute" onclick="showInfo(${mCounter.index})" /></td> 
                    </tr>
                </c:forEach>
            </table>
        </form>
        <div id="updatePopup" style="visibility:hidden">
            <form id="Change" action="controller" method="post" enctype="multipart/form-data">
                <input type="hidden" name="_method" value="put" />
                <input id="index-update" type="hidden" name="index" value="" />
                Date - 
                Year: <input id="year-update" type="text" name="year" value="" />
                Month: <input id="month-update" type="text" name="month" value="" />
                Day: <input id="day-update" type="text" name="day" value="" /><br />
                Group:  <input id="group-update" type="text" name="group" value="" /><br />
                Minutes: <input id="minutes-update" type="file" name="minute"/><br />
                Degree Programs: <br />
                <input id="dp-id-list-update" type="hidden" name="dpIdList" value="" />
                <c:forEach var="dp" items="${dpList.list}" varStatus="counter">
                    <input 
                        onclick="updateDpIdList(${counter.index})"
                        type="checkbox" 
                        class="dp-update"
                        id="${dp.degreeProgramId}"
                        value="${dp.degreeProgramId}"/>${dp.degreeProgramId}<br />
                </c:forEach>
                <input id="SubmitButton" type="submit" value="Modify Minute" />
            </form>
        </div>
    </body>
</html>
