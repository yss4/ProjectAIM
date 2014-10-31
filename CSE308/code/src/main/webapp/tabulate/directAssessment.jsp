<%@page import="com.cse308.projectaim.hibernate.types.CourseOffering"%>
<%@page import="com.cse308.projectaim.hibernate.types.CourseOutcomeDirectAssessment"%>
<%@page import="com.cse308.projectaim.hibernate.types.StudentOutcome"%>
<%@page import="java.util.HashSet"%>
<%@page import="com.cse308.projectaim.hibernate.types.PEOAttainmentLevel"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.cse308.projectaim.hibernate.types.AttainmentLevel"%>
<%@page import="java.util.Map"%>
<%@page import="com.cse308.projectaim.hibernate.types.Semester"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Set"%>
<%@page import="com.cse308.projectaim.hibernate.types.PEO"%>
<%@page import="com.cse308.projectaim.hibernate.types.SemesterPK"%>
<%@page import="java.util.List"%>
<%@page import="com.cse308.projectaim.hibernate.AIMEntity"%>
<%@page import="com.cse308.projectaim.hibernate.types.DegreeProgram"%>
<%@page import="com.cse308.projectaim.beans.AIMEntityListBean"%>
<jsp:useBean id="cOfferingList" class="com.cse308.projectaim.beans.AIMEntityListBean" scope="session"/>
<jsp:useBean id="dpList" class="com.cse308.projectaim.beans.AIMEntityListBean" scope="session" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tabulate Direct Assessment of Student Outcome Attainment</title>
    </head>
    <body>
        <h1>Tabulate Direct Assessment of Student Outcome Attainment</h1>
        <%
            // input parameters
            String inputDp = request.getParameter("dpId");
            int startYear = Integer.valueOf(request.getParameter("startYear"));
            int startTerm = 3;
            int endYear = Integer.valueOf(request.getParameter("endYear"));
            int endTerm = 2;

            if (startYear >= endYear) {
                out.println("WRONG ACADEMIC YEAR: <br />");
                out.println(startYear + " Fall  ~  ");
                out.println(endYear + " Summer");
                return;
            }

            DegreeProgram targetDP = new DegreeProgram();
            List<AIMEntity> degreeProgramList = dpList.getList();
            for (int i = 0; i < degreeProgramList.size(); ++i) {
                targetDP = (DegreeProgram) degreeProgramList.get(i);
                if (targetDP.getDegreeProgramId().equals(inputDp)) {
                    break;
                }
            }

            // make columns
            Set<StudentOutcome> setStudentOutcome = targetDP.getStudentOutcomes();
            Iterator iterator = (Iterator) setStudentOutcome.iterator();
            List<StudentOutcome> studentOutcomeList = new ArrayList<StudentOutcome>();
            while (iterator.hasNext()) {
                studentOutcomeList.add((StudentOutcome) iterator.next());
            }

            // make map
            Map<Integer, Set<CourseOutcomeDirectAssessment>> yearMap = new HashMap<Integer, Set<CourseOutcomeDirectAssessment>>();
            for (int i = startYear; i < endYear; ++i) {
                yearMap.put(new Integer(i), new HashSet<CourseOutcomeDirectAssessment>());
            }
            //out.print(yearMap.entrySet()+"<br />");

            // filter surveys using creteria
            List<CourseOffering> targetCourseOfferings = new ArrayList<CourseOffering>();
            List<AIMEntity> courseOfferings = cOfferingList.getList();
            for (int i = 0; i < courseOfferings.size(); ++i) {
                CourseOffering courseOffering = (CourseOffering) courseOfferings.get(i);
                SemesterPK semesterPK = courseOffering.getSemester().getSemester();
                Integer targetYear = semesterPK.getYear();
                if (semesterPK.getTerm() == 1 || semesterPK.getTerm() == 2) {
                    targetYear = semesterPK.getYear() - 1;
                }
                //out.println("["+courseOffering.getSectionNumber()+"]");

                if (!yearMap.containsKey(targetYear)) {
                    //out.println(targetYear + " is wrong year.<br />");
                    continue;
                }

                String targetDPId = targetDP.getDegreeProgramId();
                Set<DegreeProgram> courseOfferingDPs = courseOffering.getCourse().getDegreePrograms(); // right?
                iterator = (Iterator) courseOfferingDPs.iterator();
                boolean matchedDP = false;
                while (iterator.hasNext()) {
                    DegreeProgram dp = (DegreeProgram) iterator.next();
                    if (targetDPId.equals(dp.getDegreeProgramId())) {
                        matchedDP = true;
                        break;
                    } else {
                        //out.println(dp.getDegreeProgramId() + ", ");
                    }
                }

                if (!matchedDP) {
                    //out.println("Miss - no matched degree program.<br />");
                    continue;
                }
                //out.println("Hit - at least one is matched.<br />");

                // done for filtering
                targetCourseOfferings.add(courseOffering);
                Set<CourseOutcomeDirectAssessment> groupMap = yearMap.get(targetYear);
                groupMap.addAll(courseOffering.getCourseOutcomeDirectAssessments());  // right?    // Steeve Done until here.
            }

            // show surveys
            //out.print("=== target surveys ===<br />");
            for (int i = 0; i < targetSurveys.size(); ++i) {
                SemesterPK semesterPK = targetSurveys.get(i).getSemester().getSemester();
                //out.print("[" + targetSurveys.get(i).getId() + "]");
                //out.print(semesterPK.getYear() + ":" + semesterPK.getTerm() + "<br />");
            }
            //out.print(yearMap.entrySet()+"<br />");
%>
        <h2>Target Degree Program: <%=targetDP.getDegreeProgramId()%></h2>
        <h2>From <%=startYear%>/<%=startTerm%> To <%=endYear%>/<%=endTerm%></h2>

        <h3>Average</h3>
        <table>
            <tr>
                <th></th>
                <%
                    for (int i = 0; i < peoList.size(); ++i) {
                        out.println("<th>" + peoList.get(i).getId() + "</th>");
                    }
                %>
            </tr>
            <%
                for (int i = startYear; i < endYear; ++i) {
                    // initialize columnMap for calculating
                    Map<String, ArrayList<Double>> columnMap = new HashMap<String, ArrayList<Double>>();
                    for (int j = 0; j < peoList.size(); ++j) {
                        columnMap.put(peoList.get(j).getId(), new ArrayList<Double>());
                    }
                    //out.println(columnMap.entrySet());

                    out.println("<tr>");

                    // gather value 
                    Map<String, Set> groupMap = yearMap.get(i);
                    Set<PEOAttainmentLevel> alumniSets = groupMap.get("alumni");
                    Set<PEOAttainmentLevel> employerSets = groupMap.get("employers");
                    Set<PEOAttainmentLevel> totalSets = new HashSet<PEOAttainmentLevel>();
                    totalSets.addAll(alumniSets);
                    totalSets.addAll(employerSets);
                    iterator = (Iterator) totalSets.iterator();
                    while (iterator.hasNext()) {
                        PEOAttainmentLevel level = (PEOAttainmentLevel) iterator.next();
                        String key = level.getPeo().getId();
                        if (columnMap.containsKey(key)) {
                            columnMap.get(key).add(level.getLevel());
                        }
                    }

                    out.println("<td>" + i + " ~ " + (i + 1) + "</td>");

                    for (int j = 0; j < peoList.size(); ++j) {
                        String key = peoList.get(j).getId();
                        List<Double> values = columnMap.get(key);
                        Double total = new Double(0.0);
                        for (int k = 0; k < values.size(); ++k) {
                            total += values.get(k);
                        }
                        //out.println("<td>" + total + " / " + values.size() + "</td>");
                        Double result = new Double(0);
                        if (values.size() > 0) {
                            result = total / new Double(values.size());
                        }
                        out.println("<td><center>" + result + "</center></td>");
                    }
                    out.println("</tr>");
                }
            %>
        </table>

        <h3>Alumni</h3>
        <%
            String groupName = "alumni";
        %>

        <table>
            <tr>
                <th></th>
                <%
                    for (int i = 0; i < peoList.size(); ++i) {
                        out.println("<th>" + peoList.get(i).getId() + "</th>");
                    }
                %>
            </tr>
            <%
                for (int i = startYear; i < endYear; ++i) {
                    // initialize columnMap for calculating
                    Map<String, ArrayList<Double>> columnMap = new HashMap<String, ArrayList<Double>>();
                    for (int j = 0; j < peoList.size(); ++j) {
                        columnMap.put(peoList.get(j).getId(), new ArrayList<Double>());
                    }
                    //out.println(columnMap.entrySet());

                    out.println("<tr>");

                    // gather value 
                    Map<String, Set> groupMap = yearMap.get(i);
                    Set<PEOAttainmentLevel> targetSets = groupMap.get(groupName);
                    iterator = (Iterator) targetSets.iterator();
                    while (iterator.hasNext()) {
                        PEOAttainmentLevel level = (PEOAttainmentLevel) iterator.next();
                        String key = level.getPeo().getId();
                        if (columnMap.containsKey(key)) {
                            columnMap.get(key).add(level.getLevel());
                        }
                    }

                    out.println("<td>" + i + " ~ " + (i + 1) + "</td>");

                    for (int j = 0; j < peoList.size(); ++j) {
                        String key = peoList.get(j).getId();
                        List<Double> values = columnMap.get(key);
                        Double total = new Double(0.0);
                        for (int k = 0; k < values.size(); ++k) {
                            total += values.get(k);
                        }
                        //out.println("<td>" + total + " / " + values.size() + "</td>");
                        Double result = new Double(0);
                        if (values.size() > 0) {
                            result = total / new Double(values.size());
                        }
                        out.println("<td><center>" + result + "</center></td>");
                    }
                    out.println("</tr>");
                }
            %>
        </table>

        <h3>Employers</h3>
        <%
            groupName = "employers";
        %>

        <table>
            <tr>
                <th></th>
                <%
                    for (int i = 0; i < peoList.size(); ++i) {
                        out.println("<th>" + peoList.get(i).getId() + "</th>");
                    }
                %>
            </tr>
            <%
                for (int i = startYear; i < endYear; ++i) {
                    // initialize columnMap for calculating
                    Map<String, ArrayList<Double>> columnMap = new HashMap<String, ArrayList<Double>>();
                    for (int j = 0; j < peoList.size(); ++j) {
                        columnMap.put(peoList.get(j).getId(), new ArrayList<Double>());
                    }
                    //out.println(columnMap.entrySet());

                    out.println("<tr>");

                    // gather value 
                    Map<String, Set> groupMap = yearMap.get(i);
                    Set<PEOAttainmentLevel> targetSets = groupMap.get(groupName);
                    iterator = (Iterator) targetSets.iterator();
                    while (iterator.hasNext()) {
                        PEOAttainmentLevel level = (PEOAttainmentLevel) iterator.next();
                        String key = level.getPeo().getId();
                        if (columnMap.containsKey(key)) {
                            columnMap.get(key).add(level.getLevel());
                        }
                    }

                    out.println("<td>" + i + " ~ " + (i + 1) + "</td>");

                    for (int j = 0; j < peoList.size(); ++j) {
                        String key = peoList.get(j).getId();
                        List<Double> values = columnMap.get(key);
                        Double total = new Double(0.0);
                        for (int k = 0; k < values.size(); ++k) {
                            total += values.get(k);
                        }
                        //out.println("<td>" + total + " / " + values.size() + "</td>");
                        Double result = new Double(0);
                        if (values.size() > 0) {
                            result = total / new Double(values.size());
                        }
                        out.println("<td><center>" + result + "</center></td>");
                    }
                    out.println("</tr>");
                }
            %>
        </table>

    </body>
</html>
