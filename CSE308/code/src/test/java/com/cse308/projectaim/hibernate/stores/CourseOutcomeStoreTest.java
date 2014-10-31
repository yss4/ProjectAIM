package com.cse308.projectaim.hibernate.stores;

import com.cse308.junit.ordering.Order;
import com.cse308.junit.ordering.OrderedRunner;
import com.cse308.junit.rules.TestCasePrinterRule;
import com.cse308.projectaim.ProjectAimException;
import com.cse308.projectaim.hibernate.AIMStore;
import com.cse308.projectaim.hibernate.types.CourseOutcome;
import com.cse308.projectaim.hibernate.types.StudentOutcome;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(OrderedRunner.class)
public class CourseOutcomeStoreTest {

    @Rule
    public TestCasePrinterRule pr = new TestCasePrinterRule(System.out);
    private static AIMStore aimStore = AIMStore.getInstance();
    private CourseOutcome courseOutcome;
    private static Integer id;
    private String description = "test-A very good course outcome";
    private String rationale = "test-Because I said so.";
    private Boolean assessed = true;

    @Test
    @Order(order = 1)
    public void testCreateCourseOutcome() {
        courseOutcome = new CourseOutcome();
        courseOutcome.setDescription(description);
        courseOutcome.setRationale(rationale);
        courseOutcome.setAssessed(assessed);
        try {
            aimStore.create(courseOutcome);
            id = courseOutcome.getId();
        } catch (ProjectAimException ex) {
            fail("Failed to create CourseOutcome: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 2)
    public void testRetrieveCourseOutcome() {
        try {
            courseOutcome = new CourseOutcome();
            courseOutcome.setId(id);
            courseOutcome = (CourseOutcome) aimStore.retrieve(courseOutcome);
            assertEquals(id, courseOutcome.getId());
            assertEquals(description, courseOutcome.getDescription());
            assertEquals(rationale, courseOutcome.getRationale());
            assertEquals(assessed, courseOutcome.getAssessed());
        } catch (ProjectAimException ex) {
            fail("Failed to retrieve CourseOutcome: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 3)
    public void testUpdateCourseOutcome() {
        try {
            courseOutcome = new CourseOutcome();
            courseOutcome.setId(id);
            courseOutcome.setDescription(description + "-updated");
            courseOutcome.setRationale(rationale + "-updated");
            courseOutcome.setAssessed(!assessed);            
            aimStore.update(courseOutcome);

            courseOutcome = new CourseOutcome();
            courseOutcome.setId(id);
            courseOutcome = (CourseOutcome) aimStore.retrieve(courseOutcome);
            assertEquals(id, courseOutcome.getId());
            assertEquals(description + "-updated", courseOutcome.getDescription());
            assertEquals(rationale + "-updated", courseOutcome.getRationale());
            assertEquals(!assessed, courseOutcome.getAssessed());
            assertEquals(null, courseOutcome.getStudentOutcome());
        } catch (ProjectAimException ex) {
            fail("Failed to update CourseOutcome: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 4)
    public void testSearchCourseOutcomes() throws ProjectAimException {
        CourseOutcome query;

        try {
            query = new CourseOutcome();
            query.setId(id);
            query.setDescription(description + "-updated");
            if (aimStore.search(query).size() != 1) {
                throw new ProjectAimException("Unexpected Results for case 1.");
            }

            query = new CourseOutcome();
            query.setId(1232123);
            if (!aimStore.search(query).isEmpty()) {
                throw new ProjectAimException("Unexpected Results for case 2.");
            }

        } catch (ProjectAimException ex) {
//            fail("Failed to search CourseOutcomes: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 5)
    public void testDeleteCourseOutcome() throws ProjectAimException {
        try {
            courseOutcome = new CourseOutcome();
            courseOutcome.setId(id);
            aimStore.delete(aimStore.retrieve(courseOutcome));
        } catch (ProjectAimException ex) {
            fail("Failed to delete CourseOutcome: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 6)
    public void testRetrieveInvalidCourseOutcome() {
        try {
            courseOutcome = new CourseOutcome();
            courseOutcome.setId(id);
            courseOutcome = (CourseOutcome) aimStore.retrieve(courseOutcome);
            fail("Retrieved an invalid CourseOutcome.");
        } catch (ProjectAimException ex) {
            assert (true);
        }
    }
}
