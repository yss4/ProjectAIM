package com.cse308.projectaim.servlets;

import com.cse308.projectaim.beans.AIMEntityListBean;
import com.cse308.projectaim.beans.StudentOutcomeBean;
import com.cse308.projectaim.hibernate.AIMEntity;
import com.cse308.projectaim.hibernate.types.CourseOutcome;
import com.cse308.projectaim.hibernate.types.DegreeProgram;
import com.cse308.projectaim.hibernate.types.StudentOutcome;
import com.cse308.projectaim.services.CourseOutcomeService;
import com.cse308.projectaim.services.StudentOutcomeService;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

public class StudentOutcomeServlet extends HttpServlet {

    private Logger logger = Logger.getLogger(getClass());
    private StudentOutcomeService sts = new StudentOutcomeService();
    private ServletUtil su = new ServletUtil();
    private CourseOutcomeService cos = new CourseOutcomeService();

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

        logger.info("Controller get a request creating new Student Outcome.");
        HttpSession hs = request.getSession();
        StudentOutcomeBean studentOutcomeBean = (StudentOutcomeBean) hs.getAttribute("studentOutcomeBean");

        studentOutcomeBean.setStudentOutcomeId(request.getParameter("identifier"));
        studentOutcomeBean.setSequence(Integer.parseInt(request.getParameter("sequence")));
        studentOutcomeBean.setShortName(request.getParameter("shortname"));
        studentOutcomeBean.setDescription(request.getParameter("description"));
        String degreeProgramId = request.getParameter("degreeprogram");
        DegreeProgram degreeProgram = (DegreeProgram) sts.retrieve(new DegreeProgram(degreeProgramId));
        studentOutcomeBean.setDegreeProgram(degreeProgram);

        boolean success = sts.create(studentOutcomeBean);

        String returnPage = su.getAll(hs, sts, new StudentOutcome(), "soList");
        RequestDispatcher rd = request.getRequestDispatcher(returnPage);
        rd.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Controller get a request retreiving Student Outcomes.");

        HttpSession hs = request.getSession();
        ServletUtil su = new ServletUtil();
        String returnPage = su.getAll(hs, sts, new StudentOutcome(), "soList");
        RequestDispatcher rd = request.getRequestDispatcher(returnPage);
        rd.forward(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Controller get a request updating Student Outcome.");

        HttpSession hs = request.getSession();
        int idx = Integer.parseInt(request.getParameter("index"));
        AIMEntityListBean soList = (AIMEntityListBean) hs.getAttribute("soList");
        StudentOutcome studentOutcome = (StudentOutcome) soList.getList().get(idx);

        studentOutcome.setSequence(Integer.parseInt(request.getParameter("sequence")));
        studentOutcome.setShortName(request.getParameter("shortname"));
        studentOutcome.setDescription(request.getParameter("description"));

        String degreeProgramId = request.getParameter("degreeprogram");
        DegreeProgram degreeProgram = (DegreeProgram) sts.retrieve(new DegreeProgram(degreeProgramId));
        studentOutcome.setDegreeProgram(degreeProgram);

        String coIdListAsString = request.getParameter("coIdList");
        String[] coIdArray = new String[0];
        if (!coIdListAsString.equals("")) {
            coIdArray = coIdListAsString.split(",");
        }
        Set<CourseOutcome> courseOutcomeSet = new HashSet<CourseOutcome>();
        for (String coId : coIdArray) {
            CourseOutcome co = new CourseOutcome();
            co.setId(Integer.valueOf(coId));
            co = (CourseOutcome) cos.retrieve(co);
            courseOutcomeSet.add(co);
        }
        studentOutcome.setCourseOutcomes(courseOutcomeSet);

        boolean success = sts.update(studentOutcome);

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
