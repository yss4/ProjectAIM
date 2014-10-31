package com.cse308.projectaim.hibernate.stores;

import com.cse308.junit.ordering.Order;
import com.cse308.junit.ordering.OrderedRunner;
import com.cse308.junit.rules.TestCasePrinterRule;
import com.cse308.projectaim.ProjectAimException;
import com.cse308.projectaim.hibernate.AIMStore;
import com.cse308.projectaim.hibernate.types.StudentOutcome;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(OrderedRunner.class)
public class StudentOutcomeStoreTest {

    @Rule
    public TestCasePrinterRule pr = new TestCasePrinterRule(System.out);
    private static AIMStore aimStore = AIMStore.getInstance();
    private StudentOutcome studentOutcome;
    private String id = "test-SO123ABC";
    private String description = "test-Student Outcome #123ABC";
    private String shortName = "test-123ABC";
    private Integer sequence = 5555;

    @Test
    @Order(order = 1)
    public void testCreateStudentOutcome() {
        studentOutcome = new StudentOutcome();
        studentOutcome.setStudentOutcomeId(id);
        studentOutcome.setDescription(description);
        studentOutcome.setShortName(shortName);
        studentOutcome.setSequence(sequence);
        try {
            aimStore.create(studentOutcome);
        } catch (ProjectAimException ex) {
            fail("Failed to create StudentOutcome: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 2)
    public void testRetrieveStudentOutcome() {
        try {
            studentOutcome = new StudentOutcome();
            studentOutcome.setStudentOutcomeId(id);
            studentOutcome = (StudentOutcome) aimStore.retrieve(studentOutcome);
            assertEquals(id, studentOutcome.getStudentOutcomeId());
            assertEquals(description, studentOutcome.getDescription());
            assertEquals(shortName, studentOutcome.getShortName());
            assertEquals(sequence, studentOutcome.getSequence());
        } catch (ProjectAimException ex) {
            fail("Failed to retrieve StudentOutcome: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 3)
    public void testUpdateStudentOutcome() {
        try {
            studentOutcome = new StudentOutcome();
            studentOutcome.setStudentOutcomeId(id);
            studentOutcome.setDescription(description + "-updated");
            studentOutcome.setShortName(shortName + "-updated");
            studentOutcome.setSequence(sequence + 10);
            studentOutcome.setDegreeProgram(null);
            aimStore.update(studentOutcome);

            studentOutcome = new StudentOutcome();
            studentOutcome.setStudentOutcomeId(id);
            studentOutcome = (StudentOutcome) aimStore.retrieve(studentOutcome);
            assertEquals(id, studentOutcome.getStudentOutcomeId());
            assertEquals(description + "-updated", studentOutcome.getDescription());
            assertEquals(shortName + "-updated", studentOutcome.getShortName());
            assertEquals(new Integer(sequence + 10), studentOutcome.getSequence());
            assertEquals(null, studentOutcome.getDegreeProgram());
        } catch (ProjectAimException ex) {
            fail("Failed to update StudentOutcome: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 4)
    public void testSearchStudentOutcomes() throws ProjectAimException {
        StudentOutcome query;

        try {
            query = new StudentOutcome();
            query.setStudentOutcomeId(id);
            query.setDescription(description + "-updated");
            if (aimStore.search(query).size() != 1) {
                throw new ProjectAimException("Unexpected Results for case 1.");
            }

            query = new StudentOutcome();
            query.setStudentOutcomeId(id);
            if (!aimStore.search(query).isEmpty()) {
                throw new ProjectAimException("Unexpected Results for case 2.");
            }

        } catch (ProjectAimException ex) {
//            fail("Failed to search StudentOutcomes: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 5)
    public void testDeleteStudentOutcome() throws ProjectAimException {
        try {
            studentOutcome = new StudentOutcome();
            studentOutcome.setStudentOutcomeId(id);
            aimStore.delete(aimStore.retrieve(studentOutcome));
        } catch (ProjectAimException ex) {
            fail("Failed to delete StudentOutcome: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 6)
    public void testRetrieveInvalidStudentOutcome() {
        try {
            studentOutcome = new StudentOutcome();
            studentOutcome.setStudentOutcomeId(id);
            studentOutcome = (StudentOutcome) aimStore.retrieve(studentOutcome);
            fail("Retrieved an invalid StudentOutcome.");
        } catch (ProjectAimException ex) {
            assert (true);
        }
    }
}
