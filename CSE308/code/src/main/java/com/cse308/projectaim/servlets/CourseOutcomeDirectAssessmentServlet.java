package com.cse308.projectaim.servlets;

import com.cse308.projectaim.beans.CourseOutcomeDirectAssessmentBean;
import com.cse308.projectaim.hibernate.types.AttainmentLevel;
import com.cse308.projectaim.hibernate.types.CourseOutcome;
import com.cse308.projectaim.hibernate.types.CourseOutcomeDirectAssessment;
import com.cse308.projectaim.services.CourseOutcomeDirectAssessmentService;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

public class CourseOutcomeDirectAssessmentServlet extends HttpServlet {

    private Logger logger = Logger.getLogger(getClass());
    private CourseOutcomeDirectAssessmentService codas = new CourseOutcomeDirectAssessmentService();
    private ServletUtil su = new ServletUtil();

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

        logger.info("Controller get a request creating new Course Outcome Direct Assessment.");
        HttpSession hs = request.getSession();
        CourseOutcomeDirectAssessmentBean courseOutcomeDirectAssessmentBean = (CourseOutcomeDirectAssessmentBean) hs.getAttribute("courseOutcomeDirectAssessmentBean");

        courseOutcomeDirectAssessmentBean.setId(request.getParameter("id"));
        courseOutcomeDirectAssessmentBean.setAssessmentInstrument(request.getParameter("assessmentinstrument"));
        courseOutcomeDirectAssessmentBean.setRationale(request.getParameter("rationale"));
        courseOutcomeDirectAssessmentBean.setThresholdScore(Integer.parseInt(request.getParameter("thresholdscore")));
        
        Integer courseOutcomeNumber = Integer.parseInt(request.getParameter("courseoutcome"));
        CourseOutcome courseOutcome = (CourseOutcome) codas.retrieve(new CourseOutcome(courseOutcomeNumber));
        courseOutcomeDirectAssessmentBean.setCourseOutcome(courseOutcome);

        courseOutcomeDirectAssessmentBean.setAttainmentLevel(
                Double.parseDouble(request.getParameter("attainmentlevel")));

        boolean success = codas.create(courseOutcomeDirectAssessmentBean);
        
        su.getAll(hs, codas, new CourseOutcomeDirectAssessment(), "codaList");
        
        String nextPage = "/common/requestSuccess.jsp";
        if (!success) {
            nextPage = "/common/requestFailure.jsp";
        }

        RequestDispatcher rd = request.getRequestDispatcher(nextPage);
        rd.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Controller get a request retreiving Course Outcome Direct Assessments.");

        HttpSession hs = request.getSession();
        String returnPage = su.getAll(hs, codas, new CourseOutcomeDirectAssessment(), "codaList");
        RequestDispatcher rd = request.getRequestDispatcher(returnPage);
        rd.forward(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("This is for update requestuest.");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("This is for delete requestuest.");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
