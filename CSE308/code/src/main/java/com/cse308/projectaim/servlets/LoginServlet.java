package com.cse308.projectaim.servlets;

import com.cse308.projectaim.beans.UserAccountBean;
import com.cse308.projectaim.beans.UserState;
import com.cse308.projectaim.hibernate.types.Assignment;
import com.cse308.projectaim.hibernate.types.Course;
import com.cse308.projectaim.hibernate.types.CourseOffering;
import com.cse308.projectaim.hibernate.types.CourseOutcome;
import com.cse308.projectaim.hibernate.types.CourseOutcomeDirectAssessment;
import com.cse308.projectaim.hibernate.types.DegreeProgram;
import com.cse308.projectaim.hibernate.types.Minutes;
import com.cse308.projectaim.hibernate.types.PEO;
import com.cse308.projectaim.hibernate.types.Semester;
import com.cse308.projectaim.hibernate.types.StudentOutcome;
import com.cse308.projectaim.hibernate.types.Survey;
import com.cse308.projectaim.hibernate.types.User;
import com.cse308.projectaim.services.AssignmentService;
import com.cse308.projectaim.services.CourseOfferingService;
import com.cse308.projectaim.services.CourseOutcomeDirectAssessmentService;
import com.cse308.projectaim.services.CourseOutcomeService;
import com.cse308.projectaim.services.CourseService;
import com.cse308.projectaim.services.DegreeProgramService;
import com.cse308.projectaim.services.MinuteService;
import com.cse308.projectaim.services.PeoService;
import com.cse308.projectaim.services.SemesterService;
import com.cse308.projectaim.services.StudentOutcomeService;
import com.cse308.projectaim.services.SurveyService;
import com.cse308.projectaim.services.UserAccountService;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This is a servlet for login system.
 *
 * @author Jace
 */
public class LoginServlet extends HttpServlet {

    private ServletUtil su = new ServletUtil();
    private DegreeProgramService dps = new DegreeProgramService();
    private PeoService ps = new PeoService();
    private StudentOutcomeService sos = new StudentOutcomeService();
    private CourseOutcomeService cos = new CourseOutcomeService();
    private CourseOutcomeDirectAssessmentService codas = new CourseOutcomeDirectAssessmentService();
    private CourseService cs = new CourseService();
    private UserAccountService uas = new UserAccountService();
    private SemesterService ss = new SemesterService();
    private SurveyService surveyService = new SurveyService();
    private MinuteService ms = new MinuteService();
    private CourseOfferingService cOfferingService = new CourseOfferingService();
    private AssignmentService as = new AssignmentService();
    
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession hs = request.getSession();
        UserAccountBean userAccountBean = (UserAccountBean) hs.getAttribute("userAccountBean");

        
        userAccountBean.setUsername(request.getParameter("username"));
        userAccountBean.setPassword(request.getParameter("password"));

        UserAccountService uas = new UserAccountService();
        User dbUser = uas.login(userAccountBean);

        if (null == dbUser) {
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            userAccountBean.setLoginStatus(UserState.LOGIN_FAIL);
            rd.forward(request, response);
        } else {
            String dpuseless = su.getAll(hs, dps, new DegreeProgram(), "dpList");
            String peouseless = su.getAll(hs, ps, new PEO(), "peoList");
            String souseless = su.getAll(hs, sos, new StudentOutcome(), "soList");
            String couseless = su.getAll(hs, cos, new CourseOutcome(), "cOutcomeList");
            String codauseless = su.getAll(hs, codas, new CourseOutcomeDirectAssessment(), "codaList");
            String courseuseless = su.getAll(hs, cs, new Course(), "courseList");
            String useruseless = su.getAll(hs, uas, new User(), "userList");
            String semesteruseless = su.getAll(hs, ss, new Semester(), "semesterList");
            String surveyuseless = su.getAll(hs, surveyService, new Survey(), "surveyList");
            String minuteuseless = su.getAll(hs, ms, new Minutes(), "minuteList");
            String courseofferinguseless = su.getAll(hs, cOfferingService, new CourseOffering(), "cOfferingList");
            String assignmentuseless = su.getAll(hs, as, new Assignment(), "assignmentList");
            //RequestDispatcher rd = request.getRequestDispatcher("role.jsp");
            RequestDispatcher rd = request.getRequestDispatcher("test.jsp");
            userAccountBean.setLoginStatus(UserState.LOGIN_SUCCESS);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
