<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.cse308.projectaim.beans.ResultBean"%>
<jsp:useBean id="resultBean" class="com.cse308.projectaim.beans.ResultBean" scope="session" />
<jsp:useBean id="minuteBean" class="com.cse308.projectaim.beans.MinuteBean" scope="session" />
<jsp:useBean id="dpList" class="com.cse308.projectaim.beans.AIMEntityListBean" scope="session"/>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    resultBean.setRequestName("Create Minute");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="../js/util.js"></script>
        <title>Create Minute</title>
        <script type="text/javascript">
            var minuteDpIdList;
            var dpListForUpdate;

            minuteDpIdList = new Array();
            dpListForUpdate = document.getElementsByClassName("dp-update");
            
            function updateDpIdList(idx) {
                var selectedId = dpListForUpdate[idx].id;
                minuteDpIdList = updateIdList(selectedId, minuteDpIdList);
                console.log(minuteDpIdList);
                document.getElementById("dp-id-list-update").value = minuteDpIdList;
            }
        </script>        
    </head>
    <body>
        <div id="banner">Create Minute</div>
        <form id="TextForm" action="controller" method="post" enctype="multipart/form-data">
            <input type="hidden" name="_method" value="post" />
            Date - 
            Year: <input type="text" name="year" value="" />
            Month: <input type="text" name="month" value="" />
            Day: <input type="text" name="day" value="" /><br />
            Group:  <input type="text" name="group" value="" /><br />
            Minutes: <input type="file" name="minute"/><br />
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
            <input id="SubmitButton" type="submit" value="Create Minute" />
        </form>
    </body>
</html>
