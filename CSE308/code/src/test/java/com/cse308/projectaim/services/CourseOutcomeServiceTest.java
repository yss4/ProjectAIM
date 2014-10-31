package com.cse308.projectaim.services;

import com.cse308.junit.ordering.Order;
import com.cse308.junit.ordering.OrderedRunner;
import com.cse308.junit.rules.TestCasePrinterRule;
import com.cse308.projectaim.ProjectAimException;
import com.cse308.projectaim.beans.CourseOutcomeBean;
import com.cse308.projectaim.hibernate.AIMEntity;
import com.cse308.projectaim.hibernate.AIMStore;
import com.cse308.projectaim.hibernate.types.CourseOutcome;
import com.cse308.projectaim.hibernate.types.StudentOutcome;
import java.util.HashSet;
import java.util.List;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.runner.RunWith;

@RunWith(OrderedRunner.class)
public class CourseOutcomeServiceTest {

    @Rule
    public TestCasePrinterRule pr = new TestCasePrinterRule(System.out);
    
    private static AIMStore aimStore = AIMStore.getInstance();
    private CourseOutcomeService coService = new CourseOutcomeService();
    private static Integer id;
    private String description = "test-A very good course outcome";
    private String rationale = "test-Because I said so.";
    private Boolean assessed = true;

    @AfterClass
    public static void tearDownClass() {
        Logger.getRootLogger().info("[CourseOutcomeServiceTest.tearDown]");
        CourseOutcome courseOutcome = new CourseOutcome();
        courseOutcome.setId(id);

        try {
            aimStore.delete(courseOutcome);
        } catch (ProjectAimException ex) {
            fail("Failed to cleanup after CourseOutcomeService tests: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 1)
    public void testCreate() {
        CourseOutcomeBean courseOutcomeBean = new CourseOutcomeBean();
        courseOutcomeBean.setId(id);
        courseOutcomeBean.setDescription(description);
        courseOutcomeBean.setRationale(rationale);
        courseOutcomeBean.setAssessed(assessed);

        boolean result = coService.create(courseOutcomeBean);
        assertEquals(true, result);
        id = courseOutcomeBean.getId();
    }

    @Test
    @Order(order = 2)
    public void testGetAll() {
        List<AIMEntity> list = coService.getAll(new CourseOutcome());
        assertTrue(1 <= list.size());
    }
    
    @Test
    @Order(order = 3)
    public void testUpdate() {
        CourseOutcome courseoutcome = new CourseOutcome();
        courseoutcome.setId(id);
        courseoutcome.setDescription(description + "-updated");
        courseoutcome.setRationale(rationale + "-updated");
        courseoutcome.setAssessed(!assessed);        
        boolean result = coService.update(courseoutcome);
        assertEquals("Failed to update CourseOutcome.", true, result);
        
        courseoutcome = new CourseOutcome();
        courseoutcome.setId(id);
        try {
            courseoutcome = (CourseOutcome) aimStore.retrieve(courseoutcome);
        } catch (ProjectAimException ex) {
            fail("Failed to update CourseOutcome: " + ex.getMessage());
        }
        assertEquals(id, courseoutcome.getId());
        assertEquals(description + "-updated", courseoutcome.getDescription());
        assertEquals(rationale + "-updated", courseoutcome.getRationale());
        assertEquals(!assessed, courseoutcome.getAssessed());
        assertEquals(null, courseoutcome.getStudentOutcome());
    }
}
