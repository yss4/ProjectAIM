package com.cse308.projectaim.servlets;

import com.cse308.projectaim.beans.AssignmentBean;
import com.cse308.projectaim.hibernate.types.AIMFileWrapper;
import com.cse308.projectaim.hibernate.types.Assignment;
import com.cse308.projectaim.hibernate.types.StudentSample;
import com.cse308.projectaim.services.AssignmentService;
import java.io.IOException;
import java.util.HashSet;
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
public class AssignmentServlet extends HttpServlet {

    private Logger logger = Logger.getLogger(getClass());
    private AssignmentService as = new AssignmentService();
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
                
        logger.info("Controller get a request creating new Assignment.");
        HttpSession hs = request.getSession();
        AssignmentBean assignmentBean = (AssignmentBean) hs.getAttribute("assignmentBean");
        
        logger.info(request.getParameter("assignmentname"));
        assignmentBean.setName(request.getParameter("assignmentname"));
        
        AIMFileWrapper description = new AIMFileWrapper();
        Part descriptionFilePart = request.getPart("description");
        if (descriptionFilePart.getSize() != 0) {
            description.setFileName(su.getFileName(descriptionFilePart));
            byte[] bytes = su.readFully(descriptionFilePart.getInputStream());
            description.setBytes(bytes);
            assignmentBean.setDescription(description);
        }
        
        Set<StudentSample> studentSamples = new HashSet<StudentSample>();
        
        StudentSample studentSample_good = new StudentSample();
        studentSample_good.setQuality(1);
        studentSample_good.setContent(su.buildFileWrapper(request.getPart("studentsample_g")));
        studentSamples.add(studentSample_good);
        
        StudentSample studentSample_average = new StudentSample();
        studentSample_average.setQuality(2);
        studentSample_average.setContent(su.buildFileWrapper(request.getPart("studentsample_a")));
        studentSamples.add(studentSample_average);
        
        StudentSample studentSample_poor = new StudentSample();
        studentSample_poor.setQuality(3);
        studentSample_poor.setContent(su.buildFileWrapper(request.getPart("studentsample_p")));
        studentSamples.add(studentSample_poor);
        
        assignmentBean.setSamples(studentSamples);
        
        boolean success = as.create(assignmentBean);

        String thisStringUseless = su.getAll(hs, as, new Assignment(), "assignmentList");
        
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
        logger.info("Controller get a request retreiving Assignments.");

        HttpSession hs = request.getSession();
        String returnPage = su.getAll(hs, as, new Assignment(), "assignmentList");
        RequestDispatcher rd = request.getRequestDispatcher(returnPage);
        rd.forward(request, response);
    }
     
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
