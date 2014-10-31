package com.cse308.projectaim.servlets;

import com.cse308.projectaim.beans.AIMEntityListBean;
import com.cse308.projectaim.beans.CourseBean;
import com.cse308.projectaim.hibernate.types.Course;
import com.cse308.projectaim.hibernate.types.CourseOutcome;
import com.cse308.projectaim.hibernate.types.DegreeProgram;
import com.cse308.projectaim.hibernate.types.User;
import com.cse308.projectaim.services.CourseOutcomeService;
import com.cse308.projectaim.services.CourseService;
import com.cse308.projectaim.services.DegreeProgramService;
import com.cse308.projectaim.services.UserAccountService;
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

public class CourseServlet extends HttpServlet {

    private Logger logger = Logger.getLogger(getClass());
    private CourseService cs = new CourseService();
    private ServletUtil su = new ServletUtil();
    private UserAccountService uas = new UserAccountService();
    private DegreeProgramService dps = new DegreeProgramService();
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

        logger.info("Controller get a request creating new Course.");
        HttpSession hs = request.getSession();
        CourseBean courseBean = (CourseBean) hs.getAttribute("courseBean");

        courseBean.setId(request.getParameter("identifier"));
        courseBean.setName(request.getParameter("name"));

        String primaryCourseCoordinatorName = request.getParameter("primarycoursecoordinator");
        User primaryCourseCoordinator = (User) cs.retrieve(new User(primaryCourseCoordinatorName));
        courseBean.setPrimaryCourseCoordinator(primaryCourseCoordinator);

        String accIdListAsString = request.getParameter("accIdList");
        String[] accIdArray = new String[0];
        if (!accIdListAsString.equals("")) {
            accIdArray = accIdListAsString.split(",");
        }
        Set<User> userSet = new HashSet<User>();
        
        for (int i = 0; i < accIdArray.length; ++i) {
            User alternateCourseCoordinator;
            alternateCourseCoordinator = new User(accIdArray[i]);
            alternateCourseCoordinator = (User) uas.retrieve(alternateCourseCoordinator);
            userSet.add(alternateCourseCoordinator);
        }
        courseBean.setAlternateCourseCoordinator(userSet);

        String dpIdListAsString = request.getParameter("dpIdList");
        String[] dpIdArray = new String[0];
        if (!dpIdListAsString.equals("")) {
            dpIdArray = dpIdListAsString.split(",");
        }
        Set<DegreeProgram> dpSet = new HashSet<DegreeProgram>();
        for (int i = 0; i < dpIdArray.length; ++i) {
            DegreeProgram dp = new DegreeProgram(dpIdArray[i]);
            dp = (DegreeProgram) dps.retrieve(dp);
            dpSet.add(dp);
        }
        courseBean.setDegreePrograms(dpSet);

        String coIdListAsString = request.getParameter("coIdList");
        String[] coIdArray = new String[0];
        if (!coIdListAsString.equals("")) {
            coIdArray = coIdListAsString.split(",");
        }

        Set<CourseOutcome> coSet = new HashSet<CourseOutcome>();
        for (int i = 0; i < coIdArray.length; ++i) {
            CourseOutcome co = new CourseOutcome(Integer.valueOf(coIdArray[i]));
            co = (CourseOutcome) cos.retrieve(co);
            coSet.add(co);
        }
        courseBean.setCourseOutcomes(coSet);
        
        boolean success = cs.create(courseBean);
        String returnPage = su.getAll(hs, cs, new Course(), "courseList");
        RequestDispatcher rd = request.getRequestDispatcher(returnPage);
        rd.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Controller get a request retreiving Courses.");

        HttpSession hs = request.getSession();
        ServletUtil su = new ServletUtil();
        String returnPage = su.getAll(hs, cs, new Course(), "courseList");
        RequestDispatcher rd = request.getRequestDispatcher(returnPage);
        rd.forward(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Controller get a request updating Degree Program.");

        HttpSession hs = request.getSession();
        int idx = Integer.parseInt(request.getParameter("index"));
        AIMEntityListBean courseList = (AIMEntityListBean) hs.getAttribute("courseList");
        Course course = (Course) courseList.getList().get(idx);

        course.setId(request.getParameter("identifier"));
        course.setName(request.getParameter("name"));

        User user = new User(request.getParameter("primarycoursecoordinator"));
        user = (User) uas.retrieve(user);
        course.setPrimaryCourseCoordinator(user);

        String accIdListAsString = request.getParameter("accIdList");
        String[] accIdArray = new String[0];
        if (!accIdListAsString.equals("")) {
            accIdArray = accIdListAsString.split(",");
        }
        Set<User> userSet = new HashSet<User>();
        for (int i = 0; i < accIdArray.length; ++i) {
            user = new User(accIdArray[i]);
            user = (User) uas.retrieve(user);
            userSet.add(user);
        }
        course.setAlternateCourseCoordinator(userSet);

        String dpIdListAsString = request.getParameter("dpIdList");
        String[] dpIdArray = new String[0];
        if (!dpIdListAsString.equals("")) {
            dpIdArray = dpIdListAsString.split(",");
        }
        Set<DegreeProgram> dpSet = new HashSet<DegreeProgram>();
        for (int i = 0; i < dpIdArray.length; ++i) {
            DegreeProgram dp = new DegreeProgram(dpIdArray[i]);
            dp = (DegreeProgram) dps.retrieve(dp);
            dpSet.add(dp);
        }
        course.setDegreePrograms(dpSet);

        String coIdListAsString = request.getParameter("coIdList");
        String[] coIdArray = new String[0];
        if (!coIdListAsString.equals("")) {
            coIdArray = coIdListAsString.split(",");
        }

        Set<CourseOutcome> coSet = new HashSet<CourseOutcome>();
        for (int i = 0; i < coIdArray.length; ++i) {
            CourseOutcome co = new CourseOutcome(Integer.valueOf(coIdArray[i]));
            co = (CourseOutcome) cos.retrieve(co);
            coSet.add(co);
        }
        course.setCourseOutcomes(coSet);

        boolean success = cs.update(course);

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
