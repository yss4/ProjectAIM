<%@page import="com.cse308.projectaim.hibernate.types.StudentSample"%>
<%@page import="java.util.Set"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.cse308.projectaim.hibernate.AIMEntity"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.cse308.projectaim.hibernate.types.Assignment"%>
<%@page import="java.util.List"%>
<%@page import="com.cse308.projectaim.beans.AIMEntityListBean"%>
<jsp:useBean id="assignmentList" class="com.cse308.projectaim.beans.AIMEntityListBean" scope="session" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href='http://fonts.googleapis.com/css?family=Prosto+One' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Viga' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="../css/AssignmentView.css" />
    
        
        <title>View Assignments</title>
    </head>
    <body>
		 <div id="banner">View Assignments</div>
             <div id="main">
		<%
		String name = request.getParameter("name");

		Assignment assignment = null;
		List<AIMEntity> list = assignmentList.getList();
		for(int i = 0; i < list.size(); ++i) {
			assignment = (Assignment) list.get(i);
			if(assignment.getName().equals(name)) {
				break;
			}
		}
		%>

		<h2><%=assignment.getName()%></h2>
		Description: 
		<a target=_blank href="../file/controller?fileId=<%=assignment.getDescription().getId()%>">
			<%=assignment.getDescription().getFileName()%>
		</a><br />
		<%
			StudentSample goodSample = new StudentSample();
			StudentSample avrSample = new StudentSample();
			StudentSample poorSample = new StudentSample();

			Set<StudentSample> samples = assignment.getSamples();
			for(StudentSample sample: samples) {
				if(sample.getQuality() == 1) {
					goodSample = sample;
				} else if(sample.getQuality() == 2) {
					avrSample = sample;
				} else if(sample.getQuality() == 3) {
					poorSample = sample;
				}
			}
		%>
		Student Sample (Quality: Good): 
		<a target=_blank href="../file/controller?fileId=<%=goodSample.getContent().getId()%>">
			<%=goodSample.getContent().getFileName()%>
		</a><br />
		Student Sample (Quality: Average): 
		<a target=_blank href="../file/controller?fileId=<%=avrSample.getContent().getId()%>">
			<%=avrSample.getContent().getFileName()%>
		</a><br />
		Student Sample (Quality: Poor): 
		<a target=_blank href="../file/controller?fileId=<%=poorSample.getContent().getId()%>">
			<%=poorSample.getContent().getFileName()%>
		</a><br />
             </div>
    </body>
</html>
