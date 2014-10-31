<%@page import="com.cse308.projectaim.hibernate.types.StudentOutcome"%>
<%@page import="java.util.HashSet"%>
<%@page import="com.cse308.projectaim.hibernate.types.DegreeProgram"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.lang.Integer"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.cse308.projectaim.hibernate.AIMEntity"%>
<%@page import="com.cse308.projectaim.hibernate.types.Course"%>
<%@page import="com.cse308.projectaim.hibernate.types.CourseOutcome"%>
<%@page import="com.cse308.projectaim.beans.AIMEntityListBean"%>
<jsp:useBean id="dpList" class="com.cse308.projectaim.beans.AIMEntityListBean" scope="session" /> 
<jsp:useBean id="semesterList" class="com.cse308.projectaim.beans.AIMEntityListBean" scope="session" />
<jsp:useBean id="courseList" class="com.cse308.projectaim.beans.AIMEntityListBean" scope="session" />
<jsp:useBean id="soList" class="com.cse308.projectaim.beans.AIMEntityListBean" scope="session" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tabulate Student Outcomes Assessed In Courses Page</title>
    </head>
    <body>
        <h1>Tabulate Student Outcomes Assessed In Courses Page</h1>
        <%
			String inputDp = request.getParameter("dpId");

			DegreeProgram degreeProgram = new DegreeProgram();
			List<AIMEntity> degreeProgramList = dpList.getList();
			for (int i = 0; i < degreeProgramList.size(); ++i) {
				degreeProgram = (DegreeProgram) degreeProgramList.get(i);
				if (degreeProgram.getDegreeProgramId().equals(inputDp)) {
					break;
				}
			}
		%>
		<h2>Target Degree Program: <%=degreeProgram.getDegreeProgramId()%></h2>
		<%
			HashMap<Integer, ArrayList<String>> courseOutcomeMap = new HashMap<Integer, ArrayList<String>>();

			Course course = null;
			List<AIMEntity> cList = courseList.getList();
			Set<Course> courseByCourseOutcomeSet = new HashSet<Course>();
			for (int i = 0; i < cList.size(); ++i) {
				course = (Course) cList.get(i);
				Set<CourseOutcome> courseOutcomes = course.getCourseOutcomes();
				Iterator<CourseOutcome> courseOutcomesIterator = courseOutcomes.iterator();

				if (!course.getDegreePrograms().contains(degreeProgram)) {
					continue;
				}

				while (courseOutcomesIterator.hasNext()) {
					CourseOutcome courseOutcome = courseOutcomesIterator.next();

					// important
					if(!courseOutcome.getAssessed()) {
						continue;
					}

					Integer courseOutcomeId = courseOutcome.getId();

					if (courseOutcomeMap.containsKey(courseOutcomeId)) {
						courseOutcomeMap.get(courseOutcomeId).add(course.getId());
					} else {
						ArrayList<String> list = new ArrayList<String>();
						list.add(course.getId());
						courseOutcomeMap.put(courseOutcomeId, list);
						courseByCourseOutcomeSet.add(course);
					}
				}
			}
			out.println("course outcome map: " + courseOutcomeMap.entrySet() + "<br />");
			Object[] courses = courseByCourseOutcomeSet.toArray();
			/*
			 for(int i = 0; i < courses.length; ++i) {
			 out.println(((Course) courses[i]).getId() + "<br />");
			 }
			 */

			List<AIMEntity> studentOutcomeList = soList.getList();
			List<StudentOutcome> displayedStudentOutcome = new ArrayList<StudentOutcome>();
			for (int i = 0; i < studentOutcomeList.size(); ++i) {
				StudentOutcome studentOutcome = (StudentOutcome) studentOutcomeList.get(i);
				String dpId = studentOutcome.getDegreeProgram().getDegreeProgramId();
				if (dpId.equals(degreeProgram.getDegreeProgramId())) {
					displayedStudentOutcome.add(studentOutcome);
				}

			}
        %>

		<table>
			<tr>
				<th></th>
				<%
					for (int i = 0; i < displayedStudentOutcome.size(); ++i) {
						out.println("<th>" + displayedStudentOutcome.get(i).getStudentOutcomeId() + "</th>");
					}
				%>
			</tr>
			<%
				HashMap<Integer, ArrayList<String>> studentOutcomeValueMap = new HashMap<Integer, ArrayList<String>>();

				for (int i = 0; i < courses.length; ++i) {
					course = (Course) courses[i];
					out.println("<tr>");
					out.println("<td>" + course.getId() + "</td>");

					boolean match = false;
					for (int j = 0; j < displayedStudentOutcome.size(); ++j) {
						StudentOutcome column = displayedStudentOutcome.get(j);

						Iterator<CourseOutcome> iterator = course.getCourseOutcomes().iterator();
						while (iterator.hasNext()) {
							CourseOutcome target = iterator.next();
							
							// important
							if(!target.getAssessed()) {
								continue;
							}

							if (column.getCourseOutcomes().contains(target)) {
								match = true;

								if (studentOutcomeValueMap.containsKey(target.getId())) {
									studentOutcomeValueMap.get(target.getId()).add(column.getStudentOutcomeId());
								} else {
									ArrayList<String> list = new ArrayList<String>();
									list.add(column.getStudentOutcomeId());
									studentOutcomeValueMap.put(target.getId(), list);
								}

								break;
							}
						}
						if (match) {
							out.println("<td><center>O</center></td>");
							match = false;
						} else {
							out.println("<td></td>");
						}
					}
					out.println("</tr>");
				}
				out.println("student outcome value map: " + studentOutcomeValueMap.entrySet() + "<br />");
			%>
		</table>
    </body>
</html>
