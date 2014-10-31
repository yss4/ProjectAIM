package com.cse308.projectaim.servlets;

import com.cse308.projectaim.beans.AIMEntityListBean;
import com.cse308.projectaim.beans.MinuteBean;
import com.cse308.projectaim.hibernate.types.AIMFileWrapper;
import com.cse308.projectaim.hibernate.types.DegreeProgram;
import com.cse308.projectaim.hibernate.types.Minutes;
import com.cse308.projectaim.services.DegreeProgramService;
import com.cse308.projectaim.services.MinuteService;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.logging.Level;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.log4j.Logger;

@MultipartConfig(maxFileSize = 1000000)
public class MinuteServlet extends HttpServlet {

    private Logger logger = Logger.getLogger(getClass());
    private MinuteService ms = new MinuteService();
    private DegreeProgramService dps = new DegreeProgramService();
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

        logger.info("Controller get a request creating new Minute.");
        HttpSession hs = request.getSession();
        MinuteBean minuteBean = (MinuteBean) hs.getAttribute("minuteBean");
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
        String year = request.getParameter("year");
        String month = request.getParameter("month");
        String day = request.getParameter("day");
        String dateString = year.concat("-").concat(month).concat("-").concat(day);
        try {
            Date date = sdf.parse(dateString);
            minuteBean.setDate(date);
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(MinuteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String groupName = request.getParameter("group");
        minuteBean.setGroup(groupName);
        
        AIMFileWrapper result = new AIMFileWrapper();
        Part filePart = request.getPart("minute");
        if (filePart.getSize() != 0) {
            result.setFileName(su.getFileName(filePart));
            byte[] bytes = su.readFully(filePart.getInputStream());
            result.setBytes(bytes);
        }
        minuteBean.setMinutes(result);

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
        minuteBean.setDegreePrograms(dpSet);    
        
        boolean success = ms.create(minuteBean);

        String returnPage = su.getAll(hs, ms, new Minutes(), "minuteList");
        RequestDispatcher rd = request.getRequestDispatcher(returnPage);
        rd.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Controller get a request retreiving Minutes.");

        HttpSession hs = request.getSession();
        String returnPage = su.getAll(hs, ms, new Minutes(), "minuteList");
        RequestDispatcher rd = request.getRequestDispatcher(returnPage);
        rd.forward(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Controller get a request updating Minute.");

        HttpSession hs = request.getSession();
        int idx = Integer.parseInt(request.getParameter("index"));
        AIMEntityListBean minuteList = (AIMEntityListBean) hs.getAttribute("minuteList");
        Minutes minute = (Minutes) minuteList.getList().get(idx);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
        String year = request.getParameter("year");
        String month = request.getParameter("month");
        String day = request.getParameter("day");
        String dateString = year.concat("-").concat(month).concat("-").concat(day);
        try {
            Date date = sdf.parse(dateString);
            minute.setDate(date);
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(MinuteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String groupName = request.getParameter("group");
        minute.setGroup(groupName);
        
        AIMFileWrapper result = new AIMFileWrapper();
        Part filePart = request.getPart("minute");
        if (filePart.getSize() != 0) {
            result.setFileName(su.getFileName(filePart));
            byte[] bytes = su.readFully(filePart.getInputStream());
            result.setBytes(bytes);
        }
        minute.setMinutes(result);

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
        minute.setDegreePrograms(dpSet);    

        /*
        AttainmentLevel level = new AttainmentLevel();
        level.setLevel(Double.parseDouble(request.getParameter("targetattainmentlevel")));
        peo.getTargetAttainmentLevels().add(level);
        */
        
        boolean success = ms.update(minute);

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
