<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.cse308.projectaim.beans.DegreeProgramBean"%>
<%@page import="com.cse308.projectaim.beans.ResultBean"%>
<jsp:useBean id="degreeProgramBean" class="com.cse308.projectaim.beans.DegreeProgramBean" scope="session" />
<jsp:useBean id="resultBean" class="com.cse308.projectaim.beans.ResultBean" scope="session" />
<!--jsp:useBean id="peoList" class="com.cse308.projectaim.beans.AIMEntityListBean" scope="session" /-->
<!--jsp:useBean id="soList" class="com.cse308.projectaim.beans.AIMEntityListBean" scope="session" /--> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    resultBean.setRequestName("Create Degree Program");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href='http://fonts.googleapis.com/css?family=Prosto+One' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Viga' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="../css/DegreeProgramCreate.css" />
        <title>Create Degree Program</title>
        <div style="position:relative; text-align:right;" ><a id="MainPageLink" href="../test.jsp" >Go to menu page</a></div>
    </head>
    <body>
        <div id="banner">Create Degree Program</div>

        <form id="TextForm" action="controller" method="post">
            <input type="hidden" name="_method" value="post" />
            Identifier: <input id="Identifier" type="text" name="identifier" value="" /><br />
            Description:  <input id="Description" type="text" name="description" value="" /><br />
            Department: <input id="Department" type="text" name="department" value="" /><br />
            <!--PEOs:
            <ul>
                <c:forEach var="peo" items="${peoList.list}">
                    <li>
                        <option>${peo.id}</option>
                    </li>
                </c:forEach>
            </ul>
            Student Outcomes:
            <ul>
                <c:forEach var="so" items="${soList.list}">
                    <li>
                        <option>${so.studentOutcomeId}</option>
                    </li>
                </c:forEach>
            </ul>-->
            <input id="CreateDegreeProgramButton" type="submit" value="Create Degree Program" />
        </form>

        <!-- Steeve wrote
        Should we have add User to degreeprogram in this page ? -->
            
        <%-- Jace, do you want to have this part in "Create Degree Program", or 
             wait until they modify it to add PEOs and Student Outcomes? 
        
                <form id="PEOForm" action="controller" method="post">
                                <input type="hidden" name="_method" value="post" />
                     PEOs:  <input type="text" name="PEOs" value="${degreeProgramBean.PEO}" /><br />
                     <input id="PEOButton" type="submit" value="Add PEOs" />
                </form>
                     
                <form id="StudentOutcomeForm" action="controller" method="post">
                                <input type="hidden" name="_method" value="post" />
                     Student Outcomes:  <input type="text" name="StudentOutcomes" value="${degreeProgramBean.studentOutcome}" /><br />
                     <input id="StudentOutcomeButton" type="submit" value="Add Student Outcomes" />
                </form>
        
        --%>
    </body>
</html>
