<%@page import="java.util.List"%>
<%@page import="com.cse308.projectaim.hibernate.AIMEntity"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.cse308.projectaim.hibernate.types.CourseOutcomeDirectAssessment"%>
<%@page import="com.cse308.projectaim.beans.AIMEntityListBean"%>
<jsp:useBean id="codaList" class="com.cse308.projectaim.beans.AIMEntityListBean" scope="session"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href='http://fonts.googleapis.com/css?family=Prosto+One' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Viga' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="../css/CODAView.css" />
    
        <title>View Course Outcome Direct Assessment</title>
    </head>
    <body>
			 <div id="banner">View Course Outcome Direct Assessment</div>
             <div id="main">	
        <%
			String id = request.getParameter("id");

			CourseOutcomeDirectAssessment coda = null;
			List<AIMEntity> list = codaList.getList();
			for(int i = 0; i < list.size(); ++i) {
				coda = (CourseOutcomeDirectAssessment) list.get(i);
				if( id.equals(coda.getId()) ) {
					break;
				}
			}		
		%>
		<h2><%=coda.getId()%></h2>
		Id: <span><%=coda.getId()%></span><br />
		Assessment Instrument: <span><%=coda.getAssessmentInstrument()%></span><br />
		Attainment Level: <span><%=coda.getAttainmentLevel()%></span><br />
		Rationale: <span><%=coda.getRationale()%></span><br />
		Threshold Score: <span><%=coda.getThresholdScore()%></span><br />
             </div>
       </body>
</html>