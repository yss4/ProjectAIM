package com.cse308.projectaim.servlets;

import com.cse308.projectaim.beans.AIMEntityListBean;
import com.cse308.projectaim.beans.SurveyBean;
import com.cse308.projectaim.hibernate.types.AIMFileWrapper;
import com.cse308.projectaim.hibernate.types.DegreeProgram;
import com.cse308.projectaim.hibernate.types.PEO;
import com.cse308.projectaim.hibernate.types.PEOAttainmentLevel;
import com.cse308.projectaim.hibernate.types.Semester;
import com.cse308.projectaim.hibernate.types.SemesterPK;
import com.cse308.projectaim.hibernate.types.Survey;
import com.cse308.projectaim.services.DegreeProgramService;
import com.cse308.projectaim.services.PeoAttainmentLevelService;
import com.cse308.projectaim.services.PeoService;
import com.cse308.projectaim.services.SemesterService;
import com.cse308.projectaim.services.SurveyService;
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
public class SurveyServlet extends HttpServlet {

    private Logger logger = Logger.getLogger(getClass());
    private SurveyService surveyService = new SurveyService();
    private SemesterService semesterService = new SemesterService();
    private ServletUtil su = new ServletUtil();
    private DegreeProgramService dps = new DegreeProgramService();
	private PeoService peoService = new PeoService();
	private PeoAttainmentLevelService levelService = new PeoAttainmentLevelService();

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

        logger.info("Controller get a request creating new Survey.");
        HttpSession hs = request.getSession();
        SurveyBean surveyBean = (SurveyBean) hs.getAttribute("surveyBean");

        surveyBean.setSurveyGroup(request.getParameter("surveygroup"));
        surveyBean.setInitiator(request.getParameter("initiator"));

        String year = request.getParameter("semester").split("/")[0];
        String term = request.getParameter("semester").split("/")[1];
        SemesterPK semesterPK = new SemesterPK(Integer.valueOf(year), Integer.valueOf(term));
        Semester semester = new Semester(semesterPK);
        surveyBean.setSemester((Semester) semesterService.retrieve(semester));

        String dpIdListAsString = request.getParameter("dpIdList");
        String[] dpIdArray = new String[0];
        if (!dpIdListAsString.equals("")) {
            dpIdArray = dpIdListAsString.split(",");
        }
        Set<DegreeProgram> degreePrograms = new HashSet<DegreeProgram>();
        for (String dpId : dpIdArray) {
            DegreeProgram dp = new DegreeProgram(dpId);
            dp = (DegreeProgram) dps.retrieve(dp);
            degreePrograms.add(dp);
        }
        surveyBean.setDegreePrograms(degreePrograms);
        
        AIMFileWrapper result = new AIMFileWrapper();
        Part filePart = request.getPart("result");
        if (filePart.getSize() != 0) {
            result.setFileName(su.getFileName(filePart));
            byte[] bytes = su.readFully(filePart.getInputStream());
            result.setBytes(bytes);
        }
        surveyBean.setResults(result);

		String peoIdListAsString = request.getParameter("peoIdList");
		String levelListAsString = request.getParameter("levelIdList");
		Set<PEOAttainmentLevel> levelSet = 
				getNewLevelSet(peoIdListAsString, levelListAsString);
		surveyBean.setAttainmentLevels(levelSet);
		
        boolean success = surveyService.create(surveyBean);

        String returnPage = su.getAll(hs, surveyService, new Survey(), "surveyList");
        RequestDispatcher rd = request.getRequestDispatcher(returnPage);
        rd.forward(request, response);
    }

	private Set<PEOAttainmentLevel> getNewLevelSet(String peoIdListAsString, String levelListAsString) {
		String[] peoIdArray = new String[0];
		if(!peoIdListAsString.equals("")) {
			peoIdArray = peoIdListAsString.split(",");
		}
		String[] levelArray = new String[0];
		if(!levelListAsString.equals("")) {
			levelArray = levelListAsString.split(",");
		}
		Set<PEOAttainmentLevel> levelSet = new HashSet<PEOAttainmentLevel>();
		for(int i = 0; i < peoIdArray.length; ++i) {
			PEO peo = new PEO();
			peo.setId(peoIdArray[i]);
			peo = (PEO) peoService.retrieve(peo);
			PEOAttainmentLevel level = new PEOAttainmentLevel();
			level.setPeo(peo);
			level.setLevel(Double.valueOf(levelArray[i]));
			levelService.create(level);
			levelSet.add(level);
		}
		return levelSet;
	}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Controller get a request retreiving Surveys.");

        HttpSession hs = request.getSession();
        String returnPage = su.getAll(hs, surveyService, new Survey(), "surveyList");
        RequestDispatcher rd = request.getRequestDispatcher(returnPage);
        rd.forward(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Controller get a request updating Survey.");

        HttpSession hs = request.getSession();
        int idx = Integer.parseInt(request.getParameter("index"));
        AIMEntityListBean surveyList = (AIMEntityListBean) hs.getAttribute("surveyList");
        Survey survey = (Survey) surveyList.getList().get(idx);

        survey.setSurveyGroup(request.getParameter("surveygroup"));
        survey.setInitiator(request.getParameter("initiator"));

        String year = request.getParameter("semester").split("/")[0];
        String term = request.getParameter("semester").split("/")[1];
        SemesterPK semesterPK = new SemesterPK(Integer.valueOf(year), Integer.valueOf(term));
        Semester semester = new Semester(semesterPK);
        survey.setSemester((Semester) semesterService.retrieve(semester));

        String dpIdListAsString = request.getParameter("dpIdList");
        String[] dpIdArray = new String[0];
        if (!dpIdListAsString.equals("")) {
            dpIdArray = dpIdListAsString.split(",");
        }
        Set<DegreeProgram> degreePrograms = new HashSet<DegreeProgram>();
        for (String dpId : dpIdArray) {
            DegreeProgram dp = new DegreeProgram(dpId);
            dp = (DegreeProgram) dps.retrieve(dp);
            degreePrograms.add(dp);
        }
        survey.setDegreePrograms(degreePrograms);

        AIMFileWrapper result = survey.getResults();
        Part filePart = request.getPart("result");
        if (filePart.getSize() != 0) {
            result.setFileName(su.getFileName(filePart));
            byte[] bytes = su.readFully(filePart.getInputStream());
            result.setBytes(bytes);
        }
        survey.setResults(result);

		String peoIdListAsString = request.getParameter("peoIdList");
		String levelListAsString = request.getParameter("levelIdList");
		Set<PEOAttainmentLevel> levelSet = 
				getNewLevelSet(peoIdListAsString, levelListAsString);
		survey.setAttainmentLevels(levelSet);

        boolean success = surveyService.update(survey);

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
