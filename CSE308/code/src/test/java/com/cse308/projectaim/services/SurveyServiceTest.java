package com.cse308.projectaim.services;

import com.cse308.junit.ordering.Order;
import com.cse308.junit.ordering.OrderedRunner;
import com.cse308.junit.rules.TestCasePrinterRule;
import com.cse308.projectaim.ProjectAimException;
import com.cse308.projectaim.beans.SurveyBean;
import com.cse308.projectaim.hibernate.AIMEntity;
import com.cse308.projectaim.hibernate.AIMStore;
import com.cse308.projectaim.hibernate.types.AIMFileWrapper;
import com.cse308.projectaim.hibernate.types.DegreeProgram;
import com.cse308.projectaim.hibernate.types.Semester;
import com.cse308.projectaim.hibernate.types.SemesterPK;
import com.cse308.projectaim.hibernate.types.Survey;
import com.cse308.projectaim.hibernate.types.User;
import java.util.List;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.runner.RunWith;

@RunWith(OrderedRunner.class)
public class SurveyServiceTest {

    @Rule
    public TestCasePrinterRule pr = new TestCasePrinterRule(System.out);
    private static AIMStore aimStore = AIMStore.getInstance();
    private SurveyService surveyService = new SurveyService();
    private static int id;
    private String surveyGroup = "test-alumni";
    private static String initiator = "test-department";
    private static AIMFileWrapper results;
    private static Semester semester;
    private static DegreeProgram degreeProgram;

    @BeforeClass
    public static void setUpClass() {
        results = new AIMFileWrapper();

        semester = new Semester();
        SemesterPK semesterPK = new SemesterPK();
        semesterPK.setTerm(1);
        semesterPK.setYear(2011);
        semester.setSemester(semesterPK);

        degreeProgram = new DegreeProgram();
        degreeProgram.setDegreeProgramId("BS:CS");
        try {
            semester = (Semester) aimStore.retrieve(semester);
            degreeProgram = (DegreeProgram) aimStore.retrieve(degreeProgram);
        } catch (ProjectAimException ex) {
            fail("Could not initialize SurveyService tests: " + ex.getMessage());
        }
    }

    @AfterClass
    public static void tearDownClass() {
        Survey survey = new Survey();
        survey.setId(id);
		survey.setSemester(semester);
		survey.getDegreePrograms().add(degreeProgram);
		survey.setResults(results);

        try {
            aimStore.delete(survey);
        } catch (ProjectAimException ex) {
            fail("Failed to cleanup after SurveyService tests: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 1)
    public void testCreate() {
        SurveyBean surveyBean = new SurveyBean();
        surveyBean.setSemester(semester);
        surveyBean.getDegreePrograms().add(degreeProgram);
        surveyBean.setSurveyGroup(surveyGroup);
        surveyBean.setInitiator(initiator);
        surveyBean.setResults(results);
		surveyBean.setAttainmentLevels(null);

        boolean result = surveyService.create(surveyBean);
        id = surveyBean.getId();
        assertEquals(true, result);
    }

    @Test
    @Order(order = 2)
    public void testGetAll() {
        List<AIMEntity> list = surveyService.getAll(new Survey());
        id = ((Survey) list.get(0)).getId();
        assertTrue(1 <= list.size());
    }

    @Test
    @Order(order = 3)
    public void testUpdate() {
        Survey survey = new Survey();
        survey.setId(id);
        survey.setSemester(semester);
        survey.getDegreePrograms().remove(degreeProgram);
        survey.setSurveyGroup(surveyGroup + "-updated");
        survey.setInitiator(initiator + "-updated");
        survey.setResults(results);

        boolean result = surveyService.update(survey);
        assertEquals("Failed to update Survey.", true, result);

        survey = new Survey();
        survey.setId(id);
        try {
            survey = (Survey) aimStore.retrieve(survey);
        } catch (ProjectAimException ex) {
            fail("Failed to update Survey: " + ex.getMessage());
        }
        assertEquals(id, survey.getId());
        assertEquals(semester, survey.getSemester());
        assertEquals(true, survey.getDegreePrograms().isEmpty());
        assertEquals(surveyGroup + "-updated", survey.getSurveyGroup());
        assertEquals(initiator + "-updated", survey.getInitiator());
        assertEquals(results, survey.getResults());
    }
}
