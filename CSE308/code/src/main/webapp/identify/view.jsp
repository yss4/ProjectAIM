<%@page import="com.cse308.projectaim.beans.AIMEntityListBean"%>
<jsp:useBean id="dpList" class="com.cse308.projectaim.beans.AIMEntityListBean" scope="session" /> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Identify missing Course Offering information Page</title>
    </head>
    <body>
        <h1>View Page</h1>
        <h2>1.Identify/Request missing Course Offering Information Provided by Instructors</h2>
        <form target=_blank action="./byInstructor.jsp" method="post">
            <input type="submit" value="View missing Information">
        </form> 
        <h2>2.Identify/Request missing Course Offering Information Provided by Course Coordinators</h2>
        <form target=_blank action="./byCourseCoordinator.jsp" method="post"> 
            <input type="submit" value="View missing Information">
        </form> 
        <h2>2.Identify/Request missing Course Offering Information Provided by CIC</h2>
        <form target=_blank action="./byCIC.jsp" method="post">
            <input type="submit" value="View missing Information">
        </form> 
    </body>
</html>
