package com.cse308.projectaim.servlets;

import com.cse308.projectaim.beans.AIMEntityListBean;
import com.cse308.projectaim.beans.CourseOutcomeBean;
import com.cse308.projectaim.hibernate.types.CourseOutcome;
import com.cse308.projectaim.hibernate.types.StudentOutcome;
import com.cse308.projectaim.services.CourseOutcomeService;
import com.cse308.projectaim.services.StudentOutcomeService;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

public class CourseOutcomeServlet extends HttpServlet {

    private Logger logger = Logger.getLogger(getClass());
    private CourseOutcomeService cos = new CourseOutcomeService();
	private StudentOutcomeService sos = new StudentOutcomeService();
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

        logger.info("Controller get a request creating new Course Outcome.");
        HttpSession hs = request.getSession();
        CourseOutcomeBean courseOutcomeBean = (CourseOutcomeBean) hs.getAttribute("courseOutcomeBean");

        courseOutcomeBean.setDescription(request.getParameter("description"));
        courseOutcomeBean.setRationale(request.getParameter("rationale"));
        courseOutcomeBean.setAssessed(Boolean.valueOf(request.getParameter("assessed")));
        String studentOutcomeId = request.getParameter("studentoutcome");

        Set<StudentOutcome> studentOutcomes = new HashSet<StudentOutcome>();
        StudentOutcome studentOutcome = (StudentOutcome) cos.retrieve(new StudentOutcome(studentOutcomeId));
        courseOutcomeBean.setStudentOutcome(studentOutcome);

        boolean success = cos.create(courseOutcomeBean);

        String returnPage = su.getAll(hs, cos, new CourseOutcome(), "cOutcomeList");
        RequestDispatcher rd = request.getRequestDispatcher(returnPage);
        rd.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Controller get a request retreiving Course Outcomes.");

        HttpSession hs = request.getSession();
        ServletUtil su = new ServletUtil();
        String returnPage = su.getAll(hs, cos, new CourseOutcome(), "cOutcomeList");
        RequestDispatcher rd = request.getRequestDispatcher(returnPage);
        rd.forward(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Controller get a request updating Course Outcome.");

        HttpSession hs = request.getSession();
        int idx = Integer.parseInt(request.getParameter("index"));
        AIMEntityListBean coList = (AIMEntityListBean) hs.getAttribute("cOutcomeList");
        CourseOutcome courseOutcome = (CourseOutcome) coList.getList().get(idx);

		courseOutcome.setAssessed(Boolean.valueOf(request.getParameter("assessed")));
		courseOutcome.setDescription(request.getParameter("description"));
		courseOutcome.setRationale(request.getParameter("rationale"));
		
		String soId = request.getParameter("studentoutcome");
		StudentOutcome so = new StudentOutcome(soId);
		so = (StudentOutcome) sos.retrieve(so);
		courseOutcome.setStudentOutcome(so);

		boolean success = cos.update(courseOutcome);

        String nextPage = "./list.jsp";
        if (!success) {
            nextPage = "/common/requestFailure.jsp";
        }
        RequestDispatcher rd = request.getRequestDispatcher(nextPage);
        rd.forward(request, response);
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
