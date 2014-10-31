<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.cse308.projectaim.beans.UserAccountBean"%>
<jsp:useBean id="resultBean" class="com.cse308.projectaim.beans.ResultBean" scope="session" />
<jsp:useBean id="userAccountBean" class="com.cse308.projectaim.beans.UserAccountBean" scope="session" />
<jsp:useBean id="dpList" class="com.cse308.projectaim.beans.AIMEntityListBean" scope="session"/>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    resultBean.setRequestName("Create User");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href='http://fonts.googleapis.com/css?family=Prosto+One' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Viga' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="../css/CreateAccount.css" />
        <script type="text/javascript" src="../js/util.js"></script>
        <title>CSE 308</title>
        <div style="position:relative; text-align:right;" ><a id="MainPageLink" href="../test.jsp" >Go to menu page</a></div>
        <script type="text/javascript">
            var userDpIdList;
            var dpListForUpdate;
            
            userDpIdList = new Array();
            dpListForUpdate = document.getElementsByClassName("dp-update");

            function updateDpIdList(idx) {
                var selectedId = dpListForUpdate[idx].id;
                userDpIdList = updateIdList(selectedId, userDpIdList);
                console.log(userDpIdList);   
                document.getElementById("dp-id-list-update").value = userDpIdList;
            }
        </script>
    </head>
    <body>
        <%@include file="../incs/header.html"%>
        <div id="banner">Create New User</div>

        <form action="controller" method="post">
            <input type="hidden" name="_method" value="post" />
            User name: <input id="UsernameField" type="text" name="username" value="" /><br />
            Password:  <input id="PasswordField" type="password" name="password" value="" /><br />
            Email Address: <input id="EmailField" type="text" name="email" value="" /><br />
            CIC Member: <input id="CIC_Member_Box" type="checkbox" name="cicmember" value="true" /><br />
            Evaluator: <input id="EvaluatorBox" type="checkbox" name="evaluator" value="true" /><br />
            <!--Start Date: <input type="text" name="evaluatorstartdate" value=""/><br /> -->
            Degree Programs: <br />
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
            <input id="SubmitButton" type="submit" value="Create Account" />
        </form>
    </body>
</html>
