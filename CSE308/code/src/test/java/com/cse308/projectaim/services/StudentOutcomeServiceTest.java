package com.cse308.projectaim.services;

import com.cse308.junit.ordering.Order;
import com.cse308.junit.ordering.OrderedRunner;
import com.cse308.junit.rules.TestCasePrinterRule;
import com.cse308.projectaim.ProjectAimException;
import com.cse308.projectaim.beans.StudentOutcomeBean;
import com.cse308.projectaim.hibernate.AIMEntity;
import com.cse308.projectaim.hibernate.AIMStore;
import com.cse308.projectaim.hibernate.types.CourseOutcome;
import com.cse308.projectaim.hibernate.types.StudentOutcome;
import java.util.HashSet;
import java.util.List;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.runner.RunWith;

@RunWith(OrderedRunner.class)
public class StudentOutcomeServiceTest {

    @Rule
    public TestCasePrinterRule pr = new TestCasePrinterRule(System.out);
    private static AIMStore aimStore = AIMStore.getInstance();
    private StudentOutcomeService soService = new StudentOutcomeService();
    private static String id = "test-SO923ABC";
    private String description = "test-Student Outcome #923ABC";
    private String shortName = "test-923ABC";
    private Integer sequence = 4444;

    @AfterClass
    public static void tearDownClass() {
        StudentOutcome studentOutcome = new StudentOutcome();
        studentOutcome.setStudentOutcomeId(id);
        try {
            aimStore.delete(studentOutcome);
        } catch (ProjectAimException ex) {
            fail("Failed to cleanup after StudentOutcomeService tests: " + ex.getMessage());
        }
 
       try {
            aimStore.retrieve(studentOutcome);
            fail("Was unable to delete Student Outcome");
        } catch (ProjectAimException ex) {
            assert(true);
        }
         
    }

    @Test
    @Order(order = 1)
    public void testCreate() {
        StudentOutcomeBean soBean = new StudentOutcomeBean();
        soBean.setStudentOutcomeId(id);
        soBean.setSequence(sequence);
        soBean.setDescription(description);
        soBean.setShortName(shortName);

        boolean result = soService.create(soBean);
        assertEquals(true, result);
    }

    @Test
    @Order(order = 2)
    public void testGetAll() {
        List<AIMEntity> list = soService.getAll(new StudentOutcome());
        assertTrue(1 <= list.size());
    }

    @Test
    @Order(order = 3)
    public void testUpdate() {
        StudentOutcome studentoutcome = new StudentOutcome();
        studentoutcome.setStudentOutcomeId(id);
        studentoutcome.setSequence(sequence + 2);
        studentoutcome.setDescription(description + "-updated");
        studentoutcome.setShortName(shortName + "-updated");
        studentoutcome.setDegreeProgram(null);
        studentoutcome.setCourseOutcomes(new HashSet<CourseOutcome>());

        boolean result = soService.update(studentoutcome);
        assertEquals("Failed to update StudentOutcome.", true, result);

        studentoutcome = new StudentOutcome();
        studentoutcome.setStudentOutcomeId(id);
        try {
            studentoutcome = (StudentOutcome) aimStore.retrieve(studentoutcome);
        } catch (ProjectAimException ex) {
            fail("Failed to update StudentOutcome: " + ex.getMessage());
        }
        assertEquals(id, studentoutcome.getStudentOutcomeId());
        assertTrue(sequence + 2 == studentoutcome.getSequence());
        assertEquals(description + "-updated", studentoutcome.getDescription());
        assertEquals(shortName + "-updated", studentoutcome.getShortName());
        assertEquals(null, studentoutcome.getDegreeProgram());
        assertEquals(new HashSet<StudentOutcome>(), studentoutcome.getCourseOutcomes());
    }
}
