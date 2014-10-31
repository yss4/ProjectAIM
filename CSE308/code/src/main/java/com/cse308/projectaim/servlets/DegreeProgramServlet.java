package com.cse308.projectaim.servlets;

import com.cse308.projectaim.beans.AIMEntityListBean;
import com.cse308.projectaim.beans.DegreeProgramBean;
import com.cse308.projectaim.hibernate.types.DegreeProgram;
import com.cse308.projectaim.hibernate.types.PEO;
import com.cse308.projectaim.services.DegreeProgramService;
import com.cse308.projectaim.services.PeoService;
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

public class DegreeProgramServlet extends HttpServlet {

    private Logger logger = Logger.getLogger(getClass());
    private DegreeProgramService dps = new DegreeProgramService();
	private ServletUtil su = new ServletUtil();
	private PeoService ps = new PeoService();

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

        logger.info("Controller get a request creating new Degree Program.");
        HttpSession hs = request.getSession();
        DegreeProgramBean degreeProgramBean = (DegreeProgramBean) hs.getAttribute("degreeProgramBean");

        degreeProgramBean.setDegreeProgramId(request.getParameter("identifier"));
        degreeProgramBean.setDescription(request.getParameter("description"));
        degreeProgramBean.setDepartment(request.getParameter("department"));

        boolean success = dps.create(degreeProgramBean);

        String returnPage = su.getAll(hs, dps, new DegreeProgram(), "dpList");
        RequestDispatcher rd = request.getRequestDispatcher(returnPage);
        rd.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Controller get a request retreiving Degree Programs.");

        HttpSession hs = request.getSession();
        String returnPage = su.getAll(hs, dps, new DegreeProgram(), "dpList");
        RequestDispatcher rd = request.getRequestDispatcher(returnPage);
        rd.forward(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Controller get a request updating Degree Program.");

        HttpSession hs = request.getSession();
        int idx = Integer.parseInt(request.getParameter("index"));
        AIMEntityListBean dpList = (AIMEntityListBean) hs.getAttribute("dpList");
        DegreeProgram degreeProgram = (DegreeProgram) dpList.getList().get(idx);

        degreeProgram.setDepartment(request.getParameter("department"));
        degreeProgram.setDescription(request.getParameter("description"));

		String peoIdListAsString = request.getParameter("peoIdList");
		String[] peoIdArray = new String[0];
		if(!peoIdListAsString.equals("")) {
			peoIdArray = peoIdListAsString.split(",");
		}
		Set<PEO> peoSet = new HashSet<PEO>();
		for(int i = 0; i < peoIdArray.length; ++i) {
			PEO peo = new PEO();
			peo.setId(peoIdArray[i]);
			peo = (PEO) ps.retrieve(peo);
			peoSet.add(peo);
		}
		degreeProgram.setPEOs(peoSet);
		
        boolean success = dps.update(degreeProgram);

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
        logger.info("Controller get a request deleting Degree Program.");

        HttpSession hs = request.getSession();
        int idx = Integer.parseInt(request.getParameter("index"));
        AIMEntityListBean dpList = (AIMEntityListBean) hs.getAttribute("dpList");
        DegreeProgram degreeProgram = (DegreeProgram) dpList.getList().get(idx);

        boolean success = dps.delete(degreeProgram);
        if (success) {
            dpList.getList().remove(idx);
        }

        String nextPage = "./list.jsp";
        if (!success) {
            nextPage = "/common/requestFailure.jsp";
        }
        RequestDispatcher rd = request.getRequestDispatcher(nextPage);
        rd.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Degree Program Servlet";
    }
}
