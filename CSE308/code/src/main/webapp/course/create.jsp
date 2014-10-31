<%@page import="com.cse308.projectaim.beans.CourseBean"%>
<%@page import="com.cse308.projectaim.beans.ResultBean"%>
<jsp:useBean id="courseBean" class="com.cse308.projectaim.beans.CourseBean" scope="session" />
<jsp:useBean id="resultBean" class="com.cse308.projectaim.beans.ResultBean" scope="session" />
<jsp:useBean id="userList" class="com.cse308.projectaim.beans.AIMEntityListBean" scope="session" /> 
<jsp:useBean id="dpList" class="com.cse308.projectaim.beans.AIMEntityListBean" scope="session" /> 
<jsp:useBean id="cOutcomeList" class="com.cse308.projectaim.beans.AIMEntityListBean" scope="session" /> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
        resultBean.setRequestName("Create Course");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link href='http://fonts.googleapis.com/css?family=Prosto+One' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Viga' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="../css/CreateCourse.css" />
     
        
        <script type="text/javascript" src="../js/util.js"></script>
        <title>Create Course</title>
        <div style="position: relative; text-align: right;"><a id="MainPageLink" href="../test.jsp" >Go to menu page</a></div>
        <script type="text/javascript">
            var courseDpIdList;
            var dpListForUpdate;
            var courseCoIdList;
            var coListForUpdate;
            var courseAccIdList;
            var accListForUpdate;

            courseDpIdList = new Array();
            courseCoIdList = new Array();
            courseAccIdList = new Array();
            dpListForUpdate = document.getElementsByClassName("dp-update");
            coListForUpdate = document.getElementsByClassName("co-update");
            accListForUpdate = document.getElementsByClassName("acc-update");
            
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
            
            function updateAccIdList(idx) {
                var selectedId = accListForUpdate[idx].id;
                courseAccIdList = updateIdList(selectedId, courseAccIdList);
                console.log(courseAccIdList);
                document.getElementById("acc-id-list-update").value = courseAccIdList;
            }
        </script>        
    </head>
    <body>
      <div id="banner">Create Course</div>

        <form action="controller" method="post">
            <input type="hidden" name="_method" value="post" />
            Identifier: <input id="Identifier" type="text" name="identifier" value="" /><br />
            Name:  <input id="Name" type="text" name="name" value="" /><br />
            </br>
            Primary Course Coordinator:                                                 
            <select name="primarycoursecoordinator">                                    
                <c:forEach var="pcc" items="${userList.list}">
                    <option>${pcc.username}</option>
                </c:forEach>
            </select><br />
            
            <div id="label1">Degree</div> <div id="label12">Programs:</div> <br />
            <input id="dp-id-list-update" type="hidden" name="dpIdList" value="" />
       <div id="scroll1">
            <c:forEach var="dp" items="${dpList.list}" varStatus="counter">
                <input 
                    onclick="updateDpIdList(${counter.index})"
                    type="checkbox" 
                    class="dp-update"
                    id="${dp.degreeProgramId}"
                    value="${dp.degreeProgramId}"/>${dp.degreeProgramId}<br />
            </c:forEach>
       </div>
            <div id="label2"> Course</div> <div id="label22">Outcomes:</div> <br />
            <input id="co-id-list-update" type="hidden" name="coIdList" value="" />
       <div id="scroll2">
            <c:forEach var="co" items="${cOutcomeList.list}" varStatus="counter">
                <input 
                    onclick="updateCoIdList(${counter.index})"
                    type="checkbox" 
                    class="co-update"
                    id="${co.id}"
                    value="${co.id}"/>${co.id}<br />
            </c:forEach>
       </div>
           
            <div id="label3">   Alternate Course</div> <div id="label32">Coordinator:</div><br/>
            <input id="acc-id-list-update" type="hidden" name="accIdList" value="" />
        <div id="scroll3">
            <c:forEach var="acc" items="${userList.list}" varStatus="counter">
                <input 
                    onclick="updateAccIdList(${counter.index})"
                    type="checkbox" 
                    class="acc-update"
                    id="${acc.username}"
                    value="${acc.username}"/>${acc.username}<br />
            </c:forEach>
        </div>
            <input id="SubmitButton" type="submit" value="Create Course" />
        </form>
    </body>
</html>
