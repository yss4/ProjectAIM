<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href='http://fonts.googleapis.com/css?family=Prosto+One' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Viga' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="./css/CICHomepage.css"> 
        <title>CIC Homepage</title>
    </head>
    <body>
        <div id="banner">CIC Member Home Page</div>
        <div style="position: relative; text-align: right;"><a id="SignOutLink" href="./logout.jsp">Sign Out</a></div>
<table id="CIC_Member_Welcome_Table">
    <tr>   
        <form action="user/create.jsp">
            <input class="gButton" type=submit value="Create User"/>
        </form>
        <form action="user/controller" method="post">
            <input type="hidden" name="_method" value="get">
            <input class="gButton" type="submit" value="View Users" />
        </form>

        <form action="degreeprogram/create.jsp">
            <input class="gButton" type=submit value="Create Degree Program"/>
        </form>
        <form action="degreeprogram/controller" method="post">
            <input type="hidden"  name="_method" value="get" />
            <input class="gButton" type="submit" value="View Degree Programs" />
        </form>
	</tr>
	<tr>
        <form action="peo/create.jsp">
            <input class="gButton" type=submit value="Create PEO"/>
        </form>
        <form action="peo/controller" method="post">
            <input type="hidden"  name="_method" value="get" />
            <input class="gButton" type="submit" value="View PEOs" />
        </form>
     
        <form action="studentoutcome/create.jsp">
            <input class="gButton" type=submit value="Create Student Outcome"/>
        </form>
        <form action="studentoutcome/controller" method="post">
            <input type="hidden" name="_method" value="get" />
            <input class="gButton" type="submit" value="View Student Outcomes" />
        </form>
	</tr>
	<tr>
        <form action="course/create.jsp">
            <input class="gButton" type=submit value="Create Course"/>
        </form>
        <form action="course/controller" method="post">
            <input type="hidden"  name="_method" value="get" />
            <input class="gButton" type="submit" value="View Courses" />
        </form>

        <form action="courseoffering/create.jsp">
            <input class="gButton" type=submit value="Create Course Offering"/>
        </form>
        <form action="courseoffering/controller" method="post">
            <input type="hidden"  name="_method" value="get" />
            <input class="gButton" type="submit" value="View Course Offerings" />
        </form>
	</tr>
	<tr>
        <form action="courseoutcome/create.jsp">
            <input class="gButton" type=submit value="Create Course Outcome"/>
        </form>
        <form action="courseoutcome/controller" method="post">
            <input type="hidden"  name="_method" value="get" />
            <input class="gButton" type="submit" value="View Course Outcomes" />
        </form>
   
        <form action="courseoutcomedirectassessment/create.jsp">
            <input class="gButton" type=submit value="Create Direct Assesment"/>
        </form>
	</tr>
	<tr>
        <form action="survey/create.jsp">
            <input class="gButton" type=submit value="Create Survey"/>
        </form>
        <form action="survey/controller" method="post">
            <input type="hidden"  name="_method" value="get" />
            <input class="gButton" type="submit" value="View Surveys" />
        </form>
	</tr>
	<tr>
        <form action="Minute/create.jsp">
            <input class="gButton" type=submit value="Create Minute"/>
        </form>
        <form action="Minute/controller" method="post">
            <input type="hidden"  name="_method" value="get" />
            <input class="gButton" type="submit" value="View Minutes" />
        </form>
	</tr>
	<tr>
        <form action="identify/view.jsp">
            <input class="gButton" type=submit value="Identify Information"/>
        </form>
	</tr>
	<tr>
        <form action="tabulate/tabulate.jsp">
            <input class="gButton" type=submit value="Tabulate"/>
        </form>
	</tr>
	<tr>
        <form action="assignment/create.jsp">
            <input class="gButton" type=submit value="Create Assignemt"/>
        </form>
	</tr>
</table>
    </body>
</html>
