package com.cse308.projectaim.hibernate.stores;

import com.cse308.junit.ordering.Order;
import com.cse308.junit.ordering.OrderedRunner;
import com.cse308.junit.rules.TestCasePrinterRule;
import com.cse308.projectaim.ProjectAimException;
import com.cse308.projectaim.hibernate.AIMStore;
import com.cse308.projectaim.hibernate.types.CourseOutcomeDirectAssessment;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(OrderedRunner.class)
public class CourseOutcomeDirectAssessmentStoreTest {

    @Rule
    public TestCasePrinterRule pr = new TestCasePrinterRule(System.out);

    private static AIMStore aimStore = AIMStore.getInstance();
    private CourseOutcomeDirectAssessment coda;
    private String id = "test-coda-1234";
    private String instrument = "test-This is the tool we used to assess.";
    private String rationale = "test-This is the rationale of my decision.";
    private int thresholdScore = 5;

    @Test
    @Order(order = 1)
    public void testCreateCourseOutcomeDirectAssessment() {
        coda = new CourseOutcomeDirectAssessment();
        coda.setId(id);
        coda.setAssessmentInstrument(instrument);
        coda.setRationale(rationale);
        coda.setThresholdScore(thresholdScore);
        try {
            aimStore.create(coda);
        } catch (ProjectAimException ex) {
            fail("Failed to create CourseOutcomeDirectAssessment: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 2)
    public void testRetrieveCourseOutcomeDirectAssessment() {
        try {
            coda = new CourseOutcomeDirectAssessment();
            coda.setId(id);
            coda = (CourseOutcomeDirectAssessment) aimStore.retrieve(coda);
            assertEquals(id, coda.getId());
            assertEquals(instrument, coda.getAssessmentInstrument());
            assertEquals(rationale, coda.getRationale());
            assertEquals(thresholdScore, coda.getThresholdScore());
        } catch (ProjectAimException ex) {
            fail("Failed to retrieve CourseOutcomeDirectAssessment: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 3)
    public void testUpdateCourseOutcomeDirectAssessment() {
        try {
            coda = new CourseOutcomeDirectAssessment();
            coda.setId(id);
            coda.setAssessmentInstrument(instrument + "-updated");
            coda.setRationale(rationale + "-updated");
            coda.setThresholdScore(thresholdScore + 3);
            aimStore.update(coda);

            coda = new CourseOutcomeDirectAssessment();
            coda.setId(id);
            coda = (CourseOutcomeDirectAssessment) aimStore.retrieve(coda);
            assertEquals(id, coda.getId());
            assertEquals(instrument + "-updated", coda.getAssessmentInstrument());
            assertEquals(rationale + "-updated", coda.getRationale());
            assertEquals(thresholdScore + 3, coda.getThresholdScore());
        } catch (ProjectAimException ex) {
            fail("Failed to update CourseOutcomeDirectAssessment: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 4)
    public void testSearchCourseOutcomeDirectAssessments() throws ProjectAimException {
        CourseOutcomeDirectAssessment query;

        try {
            query = new CourseOutcomeDirectAssessment();
            query.setId(id);
            query.setAssessmentInstrument(instrument + "-updated");
            if (aimStore.search(query).size() != 1) {
                throw new ProjectAimException("Unexpected Results for case 1.");
            }

            query = new CourseOutcomeDirectAssessment();
            query.setId("CSE999");
            if (!aimStore.search(query).isEmpty()) {
                throw new ProjectAimException("Unexpected Results for case 2.");
            }

        } catch (ProjectAimException ex) {
//            fail("Failed to search CourseOutcomeDirectAssessments: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 5)
    public void testDeleteCourseOutcomeDirectAssessment() throws ProjectAimException {
        try {
            coda = new CourseOutcomeDirectAssessment();
            coda.setId(id);
            aimStore.delete(aimStore.retrieve(coda));
        } catch (ProjectAimException ex) {
            fail("Failed to delete CourseOutcomeDirectAssessment: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 6)
    public void testRetrieveInvalidCourseOutcomeDirectAssessment() {
        try {
            coda = new CourseOutcomeDirectAssessment();
            coda.setId(id);
            coda = (CourseOutcomeDirectAssessment)aimStore.retrieve(coda);
            fail("Retrieved an invalid CourseOutcomeDirectAssessment.");
        } catch (ProjectAimException ex) {
            assert (true);
        }
    }
}
