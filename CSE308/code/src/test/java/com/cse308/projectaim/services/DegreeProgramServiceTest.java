package com.cse308.projectaim.services;

import com.cse308.junit.ordering.Order;
import com.cse308.junit.ordering.OrderedRunner;
import com.cse308.junit.rules.TestCasePrinterRule;
import com.cse308.projectaim.ProjectAimException;
import com.cse308.projectaim.beans.DegreeProgramBean;
import com.cse308.projectaim.hibernate.AIMEntity;
import com.cse308.projectaim.hibernate.AIMStore;
import com.cse308.projectaim.hibernate.types.DegreeProgram;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(OrderedRunner.class)
public class DegreeProgramServiceTest {

    @Rule
    public TestCasePrinterRule pr = new TestCasePrinterRule(System.out);
    private static AIMStore aimStore = AIMStore.getInstance();
    private DegreeProgramService degreeProgramService = new DegreeProgramService();
    private DegreeProgramBean degreeProgramBean;
    private static String id = "BS:RLS";
    private String description = "Bachelors of Science in Religion";
    private String department = "College of Engineering";

    @Test
    @Order(order = 1)
    public void testCreate() {
        degreeProgramBean = new DegreeProgramBean();
        degreeProgramBean.setDegreeProgramId(id);
        degreeProgramBean.setDescription(description);
        degreeProgramBean.setDepartment(department);

        boolean result = degreeProgramService.create(degreeProgramBean);
        assertEquals(true, result);
    }

    @Test
    @Order(order = 2)
    public void testGetAll() {
        List<AIMEntity> list = degreeProgramService.getAll(new DegreeProgram());
        assertTrue(1 <= list.size());
    }

    @Test
    @Order(order = 3)
    public void testUpdate() {
        DegreeProgram degreeProgram = new DegreeProgram();
        degreeProgram.setDegreeProgramId(id);
        degreeProgram.setDescription(description + "-updated");
        degreeProgram.setDepartment(department + "-updated");

        boolean result = degreeProgramService.update(degreeProgram);
        assertEquals("Failed to update DegreeProgram.", true, result);

        degreeProgram = new DegreeProgram();
        degreeProgram.setDegreeProgramId(id);
        try {
            degreeProgram = (DegreeProgram) aimStore.retrieve(degreeProgram);
        } catch (ProjectAimException ex) {
            fail("Failed to update DegreeProgram: " + ex.getMessage());
        }
        assertEquals(id, degreeProgram.getDegreeProgramId());
        assertEquals(description + "-updated", degreeProgram.getDescription());
        assertEquals(department + "-updated", degreeProgram.getDepartment());
    }

    @Test
    @Order(order = 4)
    public void testDelete() {
        DegreeProgram dp = new DegreeProgram();
        dp.setDegreeProgramId(id);
        boolean result = degreeProgramService.delete(dp);
        try{
            aimStore.retrieve(dp);
            assertEquals("Failed to delete DegreeProgram.", true, result);
        } catch (ProjectAimException ex){
            assert(true);
        }
    }
}