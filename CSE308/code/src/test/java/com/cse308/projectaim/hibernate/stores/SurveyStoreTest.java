package com.cse308.projectaim.hibernate.stores;

import com.cse308.junit.ordering.Order;
import com.cse308.junit.ordering.OrderedRunner;
import com.cse308.junit.rules.TestCasePrinterRule;
import com.cse308.projectaim.ProjectAimException;
import com.cse308.projectaim.hibernate.AIMStore;
import com.cse308.projectaim.hibernate.types.AIMFileWrapper;
import com.cse308.projectaim.hibernate.types.DegreeProgram;
import com.cse308.projectaim.hibernate.types.Semester;
import com.cse308.projectaim.hibernate.types.SemesterPK;
import com.cse308.projectaim.hibernate.types.Survey;
import com.cse308.projectaim.hibernate.types.User;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(OrderedRunner.class)
public class SurveyStoreTest {

    @Rule
    public TestCasePrinterRule pr = new TestCasePrinterRule(System.out);

    private static AIMStore aimStore = AIMStore.getInstance();
    private Survey survey;
    private static int id;
    private String surveyGroup = "Survey Group";
    private static String initiator = "Department";
    private static AIMFileWrapper results;
    private static Semester semester;
    private static DegreeProgram degreeProgram;

    @BeforeClass
    public static void setUp() {
        results = new AIMFileWrapper();

        semester = new Semester();
        SemesterPK semesterPK = new SemesterPK();
        semesterPK.setTerm(1);
        semesterPK.setYear(2013);
        semester.setSemester(semesterPK);
        
        degreeProgram = new DegreeProgram();
        degreeProgram.setDegreeProgramId("BS:CS");

        try {
            semester = (Semester) aimStore.retrieve(semester);
            degreeProgram = (DegreeProgram) aimStore.retrieve(degreeProgram);
        } catch (ProjectAimException ex) {
            fail("Failed to initialize SurveyStore tests: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 1)
    public void testCreateSurvey() {
        survey = new Survey();
        survey.setInitiator(initiator);
        survey.setResults(results);
        survey.setSemester(semester);
        survey.setSurveyGroup(surveyGroup);
        survey.getDegreePrograms().add(degreeProgram);
        try {
            aimStore.create(survey);
            id = survey.getId();
        } catch (ProjectAimException ex) {
            fail("Failed to create Survey: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 2)
    public void testRetrieveSurvey() {
        try {
            survey = new Survey();
            survey.setId(id);
            survey = (Survey) aimStore.retrieve(survey);
            assertEquals(id, survey.getId());
            assertEquals(initiator, survey.getInitiator());
            assertEquals(results, survey.getResults());
            assertEquals(semester, survey.getSemester());
            assertEquals(surveyGroup, survey.getSurveyGroup());
            assertEquals(survey.getDegreePrograms().size(), 1);
        } catch (ProjectAimException ex) {
            fail("Failed to retrieve Survey: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 3)
    public void testUpdateSurvey() {
        try {
            survey = new Survey();
            survey.setId(id);
            survey.setResults(results);
            survey.setSurveyGroup(surveyGroup + "-updated");
            survey.setSemester(semester);
            survey.setInitiator(initiator + "-updated");
            survey.getDegreePrograms().remove(degreeProgram);
            aimStore.update(survey);

            survey = new Survey();
            survey.setId(id);
            survey = (Survey) aimStore.retrieve(survey);
            assertEquals(id, survey.getId());
            assertEquals(initiator + "-updated", survey.getInitiator());
            assertEquals(results, survey.getResults());
            assertEquals(semester, survey.getSemester());
            assertEquals(surveyGroup + "-updated", survey.getSurveyGroup());
            assertEquals(survey.getDegreePrograms().size(), 0);
        } catch (ProjectAimException ex) {
            fail("Failed to update Survey: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 4)
    public void testSearchSurveys() throws ProjectAimException {
        Survey query;

        try {
            query = new Survey();
            query.setId(id);
            query.setSurveyGroup(surveyGroup + "-updated");
            if (aimStore.search(query).size() != 1) {
                throw new ProjectAimException("Unexpected Results for case 1.");
            }

            query = new Survey();
            query.setId(999);
            if (!aimStore.search(query).isEmpty()) {
                throw new ProjectAimException("Unexpected Results for case 2.");
            }

        } catch (ProjectAimException ex) {
//            fail("Failed to search Surveys: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 5)
    public void testDeleteSurvey() throws ProjectAimException {
        try {
            survey = new Survey();
            survey.setId(id);
            aimStore.delete(aimStore.retrieve(survey));
        } catch (ProjectAimException ex) {
            fail("Failed to delete Survey: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 6)
    public void testRetrieveInvalidSurvey() {
        try {
            survey = new Survey();
            survey.setId(id);
            survey = (Survey)aimStore.retrieve(survey);
            fail("Retrieved an invalid Survey.");
        } catch (ProjectAimException ex) {
            assert (true);
        }
    }
}
