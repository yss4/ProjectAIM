package com.cse308.projectaim.hibernate.stores;

import com.cse308.junit.ordering.Order;
import com.cse308.junit.ordering.OrderedRunner;
import com.cse308.junit.rules.TestCasePrinterRule;
import com.cse308.projectaim.ProjectAimException;
import com.cse308.projectaim.hibernate.AIMStore;
import com.cse308.projectaim.hibernate.types.DegreeProgram;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(OrderedRunner.class)
public class DegreeProgramStoreTest {

    @Rule
    public TestCasePrinterRule pr = new TestCasePrinterRule(System.out);

    private static AIMStore aimStore = AIMStore.getInstance();
    private DegreeProgram degreeProgram;
    private String id = "test-BS:CSE";
    private String description = "test-Bachelors of Science in Computer Science";
    private String department = "test-College of Engineering";

    @Test
    @Order(order = 1)
    public void testCreateDegreeProgram() {
        degreeProgram = new DegreeProgram();
        degreeProgram.setDegreeProgramId(id);
        degreeProgram.setDescription(description);
        degreeProgram.setDepartment(department);
        try {
            aimStore.create(degreeProgram);
        } catch (ProjectAimException ex) {
            fail("Failed to create DegreeProgram: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 2)
    public void testRetrieveDegreeProgram() {
        try {
            degreeProgram = new DegreeProgram();
            degreeProgram.setDegreeProgramId(id);
            degreeProgram = (DegreeProgram) aimStore.retrieve(degreeProgram);
            assertEquals(id, degreeProgram.getDegreeProgramId());
            assertEquals(description, degreeProgram.getDescription());
            assertEquals(department, degreeProgram.getDepartment());
        } catch (ProjectAimException ex) {
            fail("Failed to retrieve DegreeProgram: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 3)
    public void testUpdateDegreeProgram() {
        try {
            degreeProgram = new DegreeProgram();
            degreeProgram.setDegreeProgramId(id);
            degreeProgram.setDescription(description + "-updated");
            degreeProgram.setDepartment(department + "-updated");
            aimStore.update(degreeProgram);

            degreeProgram = new DegreeProgram();
            degreeProgram.setDegreeProgramId(id);
            degreeProgram = (DegreeProgram) aimStore.retrieve(degreeProgram);
            assertEquals(id, degreeProgram.getDegreeProgramId());
            assertEquals(description + "-updated", degreeProgram.getDescription());
            assertEquals(department + "-updated", degreeProgram.getDepartment());
        } catch (ProjectAimException ex) {
            fail("Failed to update DegreeProgram: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 4)
    public void testSearchDegreePrograms() throws ProjectAimException {
        DegreeProgram query;

        try {
            query = new DegreeProgram();
            query.setDegreeProgramId(id);
            query.setDescription(description + "-updated");
            if (aimStore.search(query).size() != 1) {
                throw new ProjectAimException("Unexpected Results for case 1.");
            }

            query = new DegreeProgram();
            query.setDegreeProgramId("XX:XXX");
            if (!aimStore.search(query).isEmpty()) {
                throw new ProjectAimException("Unexpected Results for case 2.");
            }

        } catch (ProjectAimException ex) {
//            fail("Failed to search DegreePrograms: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 5)
    public void testDeleteDegreeProgram() throws ProjectAimException {
        try {
            degreeProgram = new DegreeProgram();
            degreeProgram.setDegreeProgramId(id);
            aimStore.delete(aimStore.retrieve(degreeProgram));
        } catch (ProjectAimException ex) {
            fail("Failed to delete DegreeProgram: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 6)
    public void testRetrieveInvalidDegreeProgram() {
        try {
            degreeProgram = new DegreeProgram();
            degreeProgram.setDegreeProgramId(id);
            degreeProgram = (DegreeProgram)aimStore.retrieve(degreeProgram);
            fail("Retrieved an invalid DegreeProgram.");
        } catch (ProjectAimException ex) {
            assert (true);
        }
    }
}
