<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.cse308.projectaim.hibernate.types.PEO"%>
<%@page import="com.cse308.projectaim.beans.AIMEntityListBean"%>
<jsp:useBean id="peoList" class="com.cse308.projectaim.beans.AIMEntityListBean" scope="session" />
<jsp:useBean id="dpList" class="com.cse308.projectaim.beans.AIMEntityListBean" scope="session"/>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href='http://fonts.googleapis.com/css?family=Prosto+One' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Viga' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="../css/PEOModify.css" />
        <title>PEO List</title>
        <div style="position:relative; text-align:right;" ><a id="MainPageLink" href="../test.jsp" >Go to menu page</a></div>
    </head>
    <body>
        <div id="banner">Modify Program Educational Objectives (PEOs)</div><br />
        <div id="Title">PEOs:</div><br /><br /> 
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
                    <!--<th>Target Attainment Level</th>-->
                </tr>
                <c:forEach var="peo" items="${peoList.list}" varStatus="peoCounter">
                    <tr>
                        <td><span id="peoCounter"><c:out value="${peoCounter.count}"/></span></td>
                        <td><span id="peo-id${peoCounter.count}"><c:out value="${peo.id}" /></span></td>
                        <td><span id="peo-sequence${peoCounter.count}"><c:out value="${peo.sequence}" /></span></td>
                        <td><span id="peo-shortname${peoCounter.count}"><c:out value="${peo.shortName}" /></span></td>
                        <td><span id="peo-description${peoCounter.count}"><c:out value="${peo.description}" /></span></td>
                        <td><input name="ViewPEO" type="button" value="View/Modify PEO" onclick="showInfo(${peoCounter.count})" /></td> 
                    </tr>
                </c:forEach>
            </table>
        </form> 
     </div>
        <script type="text/javascript">
            function showInfo(idx) {
                var index = idx-1;
                var peoId = document.getElementById("peo-id"+idx).innerHTML;
                var peoSequence = document.getElementById("peo-sequence"+idx).innerHTML;
                var peoShortname = document.getElementById("peo-shortname"+idx).innerHTML;
                var peoDescription = document.getElementById("peo-description"+idx).innerHTML;
                
                document.getElementById("index-update").value = index;
                document.getElementById("Id-update").value = peoId;
                document.getElementById("Sequence-update").value = peoSequence;
                document.getElementById("ShortName-update").value = peoShortname;
                document.getElementById("Description-update").value = peoDescription;
                
                var popup = document.getElementById("updatePopup");
                popup.style.visibility = "visible";
            }
        </script>
        
        <div id="updatePopup" style="visibility:hidden">
            <form id="Change" action="controller" method="post">
                <input type="hidden" name="_method" value="put" />
                <input id="index-update" type="hidden" name="index" value="" />
                Identifier: <input id="Id-update" type="text" name="identifier" value="" /><br />
                Sequence:  <input id="Sequence-update" type="text" name="sequence" value="" /><br />
                ShortName: <input id="ShortName-update" type="text" name="shortname" value=""/><br />
                Description: <input id="Description-update" type="text" name="description" value="" /><br />
                <input id="ModifyButton" type="submit" value="Modify PEO" />
            </form>
        </div>        
    </body>
</html>
