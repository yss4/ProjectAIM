package com.cse308.projectaim.servlets;

import com.cse308.projectaim.beans.AIMEntityListBean;
import com.cse308.projectaim.beans.CourseOfferingBean;
import com.cse308.projectaim.hibernate.types.AIMFileWrapper;
import com.cse308.projectaim.hibernate.types.Assignment;
import com.cse308.projectaim.hibernate.types.Course;
import com.cse308.projectaim.hibernate.types.CourseOffering;
import com.cse308.projectaim.hibernate.types.CourseOutcomeDirectAssessment;
import com.cse308.projectaim.hibernate.types.Semester;
import com.cse308.projectaim.hibernate.types.SemesterPK;
import com.cse308.projectaim.hibernate.types.User;
import com.cse308.projectaim.services.AssignmentService;
import com.cse308.projectaim.services.CourseOfferingService;
import com.cse308.projectaim.services.CourseOutcomeDirectAssessmentService;
import com.cse308.projectaim.services.CourseService;
import com.cse308.projectaim.services.SemesterService;
import com.cse308.projectaim.services.UserAccountService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.log4j.Logger;

@MultipartConfig(maxFileSize = 1000000)
public class CourseOfferingServlet extends HttpServlet {

	private Logger logger = Logger.getLogger(getClass());
	private CourseOfferingService cos = new CourseOfferingService();
	private CourseService cs = new CourseService();
	private SemesterService semesterService = new SemesterService();
	private UserAccountService uas = new UserAccountService();
	private ServletUtil su = new ServletUtil();
	private AssignmentService assignmentService = new AssignmentService();
	private CourseOutcomeDirectAssessmentService codaService
			= new CourseOutcomeDirectAssessmentService();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String requestType = request.getParameter("_method");

		if ("get".equals(requestType)) {
			doGet(request, response);
			return;
		} else if ("put".equals(requestType)) {
			doPut(request, response);
			return;
		} else if ("delete".equals(requestType)) {
			doDelete(request, response);
			return;
		}

		logger.info("Controller get a request creating new Course Offering.");
		HttpSession hs = request.getSession();
		CourseOfferingBean courseOfferingBean = (CourseOfferingBean) hs.getAttribute("courseOfferingBean");

		String year = request.getParameter("semester").split("/")[0];
		String term = request.getParameter("semester").split("/")[1];
		SemesterPK semesterPK = new SemesterPK(Integer.valueOf(year), Integer.valueOf(term));
		Semester semester = new Semester(semesterPK);
		courseOfferingBean.setSemester((Semester) semesterService.retrieve(semester));

		String courseName = request.getParameter("course");
		Course course = (Course) cos.retrieve(new Course(courseName));
		courseOfferingBean.setCourse(course);

		String instructorName = request.getParameter("instructor");
		User instructor = (User) uas.retrieve(new User(instructorName));
		courseOfferingBean.setInstructor(instructor);

		courseOfferingBean.setSyllabus(
				su.buildFileWrapper(request.getPart("syllabus")));
		courseOfferingBean.setLectureSchedule(
				su.buildFileWrapper(request.getPart("lectureschedule")));
		courseOfferingBean.setEndOfSemesterReport(
				su.buildFileWrapper(request.getPart("endofsemesterreport")));
		courseOfferingBean.setCourseCoordinatorReport(
				su.buildFileWrapper(request.getPart("coursecoordinatorreport")));
		courseOfferingBean.setCicReport(
				su.buildFileWrapper(request.getPart("cicreport")));

		List<Part> parts = (ArrayList<Part>) request.getParts();

		List<AIMFileWrapper> lectureNotes = new ArrayList<AIMFileWrapper>();
		for(int i = 0; i < parts.size(); ++i) {
			Part part = parts.get(i);
			if( "lecturenotes".equals(part.getName()) && part.getSize() != 0 ) {
				lectureNotes.add(su.buildFileWrapper(part));
				logger.info("lecturenote: " + su.getFileName(part));
			}
		}
		courseOfferingBean.setLectureNotes(lectureNotes);
		
		String assignmentIdListAsString = request.getParameter("assignmentIdList");
		String[] assignmentIdArray = new String[0];
		if(!assignmentIdListAsString.equals("")) {
			assignmentIdArray = assignmentIdListAsString.split(",");
		}
		List<Assignment> assignmentList = new ArrayList<Assignment>();
		for (int i = 0; i < assignmentIdArray.length; ++i) {
			Assignment assignment = new Assignment();
			assignment.setName(assignmentIdArray[i]);
			assignment = (Assignment) assignmentService.retrieve(assignment);
			assignmentList.add(assignment);
		}
		courseOfferingBean.setAssignments(assignmentList);
		
		String codaIdListAsString = request.getParameter("codaIdList");
		String[] codaIdArray = new String[0];
		if(!codaIdListAsString.equals("")) {
			codaIdArray = codaIdListAsString.split(",");
		}
		Set<CourseOutcomeDirectAssessment> codaList = new HashSet<CourseOutcomeDirectAssessment>();
		for (int i = 0; i < codaIdArray.length; ++i) {
			CourseOutcomeDirectAssessment coda = new CourseOutcomeDirectAssessment();
			coda.setId(codaIdArray[i]);
			coda = (CourseOutcomeDirectAssessment) codaService.retrieve(coda);
			codaList.add(coda);
		}
		courseOfferingBean.setCourseOutcomeDirectAssessments(codaList);

		boolean success = cos.create(courseOfferingBean);

		String returnPage = su.getAll(hs, cos, new CourseOffering(), "cOfferingList");
		RequestDispatcher rd = request.getRequestDispatcher(returnPage);
		rd.forward(request, response);
	}

	//For codas, need to use them maybe
        /*List<CourseOutcomeDirectAssessment> codas = new ArrayList<CourseOutcomeDirectAssessment>();
	 String[] tempCodas = request.getParameterValues("courseoutcomedirectassessments");
	 for (int i = 0; i < tempCodas.length; i++) {
	 CourseOutcomeDirectAssessment coda = (CourseOutcomeDirectAssessment) cos.retrieve(new CourseOutcomeDirectAssessment(tempCodas[i]));
	 codas.add(coda);
	 }
	 courseOfferingBean.setCodas(codas);*/
	/* Different from above codes for CAT and COSR, I think this way is right. Give me you guys opinion!
	 * 
	 List<Double> courseOutcomeAttainmentTargets = new ArrayList<Double>();
	 String[] tempCourseOutcomeAttainmentTargets = request.getParameterValues("courseattainmenttargets");
	 for (int i = 0; i < tempCourseOutcomeAttainmentTargets.length; i++) {
	 Double courseAttainmentTarget = Double.parseDouble(tempCourseOutcomeAttainmentTargets[i]);
	 courseOutcomeAttainmentTargets.add(courseAttainmentTarget);
	 }
	 courseOfferingBean.setCourseAttainmentTarget(courseOutcomeAttainmentTargets);
        
	 List<Double> courseOutcomeSurveyResults = new ArrayList<Double>();
	 String[] tempCourseOutcomeSurveyResults = request.getParameterValues("courseoutcomesurveyresults");
	 for (int i = 0; i < tempCourseOutcomeSurveyResults.length; i++) {
	 Double courseOutcomeSurveyResult = Double.parseDouble(tempCourseOutcomeSurveyResults[i]);
	 courseOutcomeSurveyResults.add(courseOutcomeSurveyResult);
	 }
	 courseOfferingBean.setCourseAttainmentTarget(courseOutcomeSurveyResults);
	 */
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("Controller get a request retreiving Course Offerings.");

		HttpSession hs = request.getSession();
		String returnPage = su.getAll(hs, cos, new CourseOffering(), "cOfferingList");
		RequestDispatcher rd = request.getRequestDispatcher(returnPage);
		rd.forward(request, response);
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("Controller get a request updating Course Offerings.");

		HttpSession hs = request.getSession();
		int idx = Integer.parseInt(request.getParameter("index"));
		AIMEntityListBean cOfferingList = (AIMEntityListBean) hs.getAttribute("cOfferingList");
		CourseOffering courseOffering = (CourseOffering) cOfferingList.getList().get(idx);

		String year = request.getParameter("semester").split("/")[0];
		String term = request.getParameter("semester").split("/")[1];
		SemesterPK semesterPK = new SemesterPK(Integer.valueOf(year), Integer.valueOf(term));
		Semester semester = new Semester(semesterPK);
		courseOffering.setSemester((Semester) semesterService.retrieve(semester));

		Course course = new Course(request.getParameter("course"));
		course = (Course) cs.retrieve(course);
		courseOffering.setCourse(course);

		User user = new User(request.getParameter("instructor"));
		user = (User) uas.retrieve(user);
		courseOffering.setInstructor(user);

		updateFile(courseOffering.getSyllabus(), request.getPart("syllabus"));
		updateFile(courseOffering.getLectureSchedule(), 
				request.getPart("lectureschedule"));
		updateFile(courseOffering.getEndOfSemesterReport(), 
				request.getPart("endofsemesterreport"));
		updateFile(courseOffering.getCourseCoordinatorReport(), 
				request.getPart("coursecoordinatorreport"));
		updateFile(courseOffering.getCicReport(), request.getPart("cicreport"));

		List<Part> parts = (ArrayList<Part>) request.getParts();

		List<AIMFileWrapper> newFiles = new ArrayList<AIMFileWrapper>();
		for(int i = 0; i < parts.size(); ++i) {
			Part part = parts.get(i);
			if( "lecturenotes".equals(part.getName()) && part.getSize() != 0 ) {
				newFiles.add(su.buildFileWrapper(part));
				logger.info("lecturenote: " + su.getFileName(part));
			}
		}
		if(newFiles.size() > 0) {
			courseOffering.getLectureNotes().addAll(newFiles);
		}

		String assignmentIdListAsString = request.getParameter("assignmentIdList");
		String[] assignmentIdArray = new String[0];
		if(!assignmentIdListAsString.equals("")) {
			assignmentIdArray = assignmentIdListAsString.split(",");
		}
		List<Assignment> assignmentList = new ArrayList<Assignment>();
		for (int i = 0; i < assignmentIdArray.length; ++i) {
			Assignment assignment = new Assignment();
			assignment.setName(assignmentIdArray[i]);
			assignment = (Assignment) assignmentService.retrieve(assignment);
			assignmentList.add(assignment);
		}
		courseOffering.setAssignments(assignmentList);

		String codaIdListAsString = request.getParameter("codaIdList");
		String[] codaIdArray = new String[0];
		if(!codaIdListAsString.equals("")) {
			codaIdArray = codaIdListAsString.split(",");
		}
		Set<CourseOutcomeDirectAssessment> codaList = new HashSet<CourseOutcomeDirectAssessment>();
		for (int i = 0; i < codaIdArray.length; ++i) {
			CourseOutcomeDirectAssessment coda = new CourseOutcomeDirectAssessment();
			coda.setId(codaIdArray[i]);
			coda = (CourseOutcomeDirectAssessment) codaService.retrieve(coda);
			codaList.add(coda);
		}
		courseOffering.setCourseOutcomeDirectAssessments(codaList);
		
		boolean success = cos.update(courseOffering);

		String nextPage = "./list.jsp";
		if (!success) {
			nextPage = "/common/requestFailure.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}

	private void updateFile(AIMFileWrapper original, Part part) throws IOException {
		if (part.getSize() != 0) {
			AIMFileWrapper newOne = su.buildFileWrapper(part);
			original.setFileName(newOne.getFileName());
			original.setBytes(newOne.getBytes());
		}
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("This is for delete requestuest.");
	}

	@Override
	public String getServletInfo() {
		return "CourseOffering Servlet";
	}
}
