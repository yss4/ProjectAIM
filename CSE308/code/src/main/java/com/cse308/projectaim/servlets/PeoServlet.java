package com.cse308.projectaim.servlets;

import com.cse308.projectaim.beans.AIMEntityListBean;
import com.cse308.projectaim.beans.PeoBean;
import com.cse308.projectaim.hibernate.types.AttainmentLevel;
import com.cse308.projectaim.hibernate.types.PEO;
import com.cse308.projectaim.services.PeoService;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

public class PeoServlet extends HttpServlet {

    private Logger logger = Logger.getLogger(getClass());
    private PeoService ps = new PeoService();
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

        logger.info("Controller get a request creating new PEO.");
        HttpSession hs = request.getSession();
        PeoBean peoBean = (PeoBean) hs.getAttribute("peoBean");

        peoBean.setId(request.getParameter("identifier"));
        peoBean.setSequence(Integer.parseInt(request.getParameter("sequence")));
        peoBean.setShortName(request.getParameter("shortname"));
        peoBean.setDescription(request.getParameter("description"));

        boolean success = ps.create(peoBean);

        String returnPage = su.getAll(hs, ps, new PEO(), "peoList");
        RequestDispatcher rd = request.getRequestDispatcher(returnPage);
        rd.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Controller get a request retreiving PEOs.");

        HttpSession hs = request.getSession();
        String returnPage = su.getAll(hs, ps, new PEO(), "peoList");
        RequestDispatcher rd = request.getRequestDispatcher(returnPage);
        rd.forward(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Controller get a request updating PEO.");

        HttpSession hs = request.getSession();
        int idx = Integer.parseInt(request.getParameter("index"));
        AIMEntityListBean peoList = (AIMEntityListBean) hs.getAttribute("peoList");
        PEO peo = (PEO) peoList.getList().get(idx);

        peo.setSequence(Integer.parseInt(request.getParameter("sequence")));
        peo.setDescription(request.getParameter("description"));
        peo.setShortName(request.getParameter("shortname"));

        /*
        AttainmentLevel level = new AttainmentLevel();
        level.setLevel(Double.parseDouble(request.getParameter("targetattainmentlevel")));
        peo.getTargetAttainmentLevels().add(level);
        */
        
        boolean success = ps.update(peo);

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
