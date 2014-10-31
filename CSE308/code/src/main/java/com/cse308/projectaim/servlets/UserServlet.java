package com.cse308.projectaim.servlets;

import com.cse308.projectaim.ProjectAimException;
import com.cse308.projectaim.beans.AIMEntityListBean;
import com.cse308.projectaim.beans.UserAccountBean;
import com.cse308.projectaim.hibernate.AIMStore;
import com.cse308.projectaim.hibernate.types.DegreeProgram;
import com.cse308.projectaim.hibernate.types.User;
import com.cse308.projectaim.services.DegreeProgramService;
import com.cse308.projectaim.services.UserAccountService;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

public class UserServlet extends HttpServlet {

    private Logger logger = Logger.getLogger(getClass());
    private UserAccountService uas = new UserAccountService();
    private ServletUtil su = new ServletUtil();
    private DegreeProgramService dps = new DegreeProgramService();

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

        HttpSession hs = request.getSession();
        UserAccountBean userAccountBean = (UserAccountBean) hs.getAttribute("userAccountBean");
        userAccountBean.setUsername(request.getParameter("username"));
        userAccountBean.setPassword(request.getParameter("password"));
        userAccountBean.setEmailAddress(request.getParameter("email"));
        userAccountBean.setCicMemberStatus(
                Boolean.valueOf(request.getParameter("cicmember")));
        userAccountBean.setEvaluatorStatus(
                Boolean.valueOf(request.getParameter("evaluator")));

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
        userAccountBean.setDegreePrograms(dpSet);
		try {
			boolean success = uas.signupUser(userAccountBean);
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}

        String returnPage = su.getAll(hs, uas, new User(), "userList");
        RequestDispatcher rd = request.getRequestDispatcher(returnPage);
        rd.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Controller get a request retreiving Users.");

        HttpSession hs = request.getSession();
        String returnPage = su.getAll(hs, uas, new User(), "userList");
        RequestDispatcher rd = request.getRequestDispatcher(returnPage);
        rd.forward(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Controller get a request updating User.");

        HttpSession hs = request.getSession();
        int idx = Integer.parseInt(request.getParameter("index"));
        AIMEntityListBean userList = (AIMEntityListBean) hs.getAttribute("userList");
        User user = (User) userList.getList().get(idx);

        user.setPassword(request.getParameter("password"));
		try {
			user.setPassword( uas.encodePassword(user.getPassword()) );
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}

        user.setEmailAddress(request.getParameter("email"));
        user.setCicMemberStatus(Boolean.valueOf(request.getParameter("cicmember")));
        user.setEvaluatorStatus(Boolean.valueOf(request.getParameter("evaluator")));

        String dpIdListAsString = request.getParameter("dpIdList");
        String[] dpIdArray = new String[0];
        if (!dpIdListAsString.equals("")) {
            dpIdArray = dpIdListAsString.split(",");
        }
        Set<DegreeProgram> dpSet = new HashSet<DegreeProgram>();
        for (String id : dpIdArray) {
            DegreeProgram dp = new DegreeProgram();
            dp.setDegreeProgramId(id);
			dp = (DegreeProgram) dps.retrieve(dp);
			dpSet.add(dp);
        }
		user.setDegreePrograms(dpSet);

        boolean success = uas.update(user);

        String nextPage = "./list.jsp";
        if (!success) {
            nextPage = "/common/requestFailure.jsp";
        }
        RequestDispatcher rd = request.getRequestDispatcher(nextPage);
        rd.forward(request, response);
    }
}
