package com.cse308.projectaim.services;

import com.cse308.junit.ordering.Order;
import com.cse308.junit.ordering.OrderedRunner;
import com.cse308.junit.rules.TestCasePrinterRule;
import com.cse308.projectaim.ProjectAimException;
import com.cse308.projectaim.beans.CourseOutcomeDirectAssessmentBean;
import com.cse308.projectaim.hibernate.AIMEntity;
import com.cse308.projectaim.hibernate.AIMStore;
import com.cse308.projectaim.hibernate.types.CourseOutcome;
import com.cse308.projectaim.hibernate.types.CourseOutcomeDirectAssessment;
import java.util.List;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(OrderedRunner.class)
public class CourseOutcomeDirectAssessmentServiceTest {

    @Rule
    public TestCasePrinterRule pr = new TestCasePrinterRule(System.out);
    private static AIMStore aimStore = AIMStore.getInstance();
    private CourseOutcomeDirectAssessmentService codaService = new CourseOutcomeDirectAssessmentService();
    private static String id = "test-coda-9999";
    private String instrument = "test-This is the tool we used to assess.";
    private String rationale = "test-This is the rationale of my decision.";
    private double attainmentLevel = 5.0;
    private int thresholdScore = 4;

    @AfterClass
    public static void tearDownClass() {
        Logger.getRootLogger().info("[CourseOutcomeDirectAssessmentServiceTest.tearDown]");
        CourseOutcomeDirectAssessment coda = new CourseOutcomeDirectAssessment();
        coda.setId(id);
        try {
            aimStore.delete(coda);
        } catch (ProjectAimException ex) {
            fail("Failed to cleanup after CourseOutcomeDirectAssessmentService tests: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 1)
    public void testCreate() {
        CourseOutcomeDirectAssessmentBean codaBean = new CourseOutcomeDirectAssessmentBean();
        codaBean.setId(id);
        codaBean.setAssessmentInstrument(instrument);
        codaBean.setRationale(rationale);
        codaBean.setThresholdScore(thresholdScore);

        boolean result = codaService.create(codaBean);
        assertEquals(true, result);
    }

    @Test
    @Order(order = 2)
    public void testGetAll() {
        List<AIMEntity> list = codaService.getAll(new CourseOutcomeDirectAssessment());
        assertTrue(1 <= list.size());
    }

    @Test
    @Order(order = 3)
    public void testUpdate() {
        CourseOutcomeDirectAssessment coda = new CourseOutcomeDirectAssessment();
        coda.setId(id);
        coda.setAssessmentInstrument(instrument + "-updated");
        coda.setRationale(rationale + "-updated");
        coda.setThresholdScore(thresholdScore + 2);
        coda.setCourseOutcome(null);

        boolean result = codaService.update(coda);
        assertEquals("Failed to update CourseOutcomeDirectAssessment.", true, result);

        coda = new CourseOutcomeDirectAssessment();
        coda.setId(id);
        try {
            coda = (CourseOutcomeDirectAssessment) aimStore.retrieve(coda);
        } catch (ProjectAimException ex) {
            fail("Failed to update CourseOutcomeDirectAssessment: " + ex.getMessage());
        }
        assertEquals(id, coda.getId());
        assertEquals(instrument + "-updated", coda.getAssessmentInstrument());
        assertEquals(rationale + "-updated", coda.getRationale());
        assertTrue(thresholdScore + 2 == coda.getThresholdScore());
        assertEquals(null, coda.getCourseOutcome());
    }
}
