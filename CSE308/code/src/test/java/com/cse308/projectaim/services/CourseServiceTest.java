package com.cse308.projectaim.services;

import com.cse308.junit.ordering.Order;
import com.cse308.junit.ordering.OrderedRunner;
import com.cse308.junit.rules.TestCasePrinterRule;
import com.cse308.projectaim.ProjectAimException;
import com.cse308.projectaim.beans.CourseBean;
import com.cse308.projectaim.hibernate.AIMEntity;
import com.cse308.projectaim.hibernate.AIMStore;
import com.cse308.projectaim.hibernate.types.Course;
import java.util.List;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.runner.RunWith;

@RunWith(OrderedRunner.class)
public class CourseServiceTest {

    @Rule
    public TestCasePrinterRule pr = new TestCasePrinterRule(System.out);
    
    private static AIMStore aimStore = AIMStore.getInstance();
    private CourseService courseService = new CourseService();
    private static String id = "test-AMS301";
    private String name = "test-Finite Mathematical Structure";

    @AfterClass
    public static void tearDownClass() {
        Logger.getRootLogger().info("[DegreeProgramServiceTest.tearDown]");
        Course course = new Course();
        course.setId(id);

        try {
            aimStore.delete(course);
        } catch (ProjectAimException ex) {
            fail("Failed to cleanup after CourseService tests: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 1)
    public void testCreate() {
        CourseBean courseBean = new CourseBean();
        courseBean.setId(id);
        courseBean.setName(name);

        boolean result = courseService.create(courseBean);
        assertEquals(true, result);
    }

    @Test
    @Order(order = 2)
    public void testGetAll() {
        List<AIMEntity> list = courseService.getAll(new Course());
        assertTrue(1 <= list.size());
    }
    
    @Test
    @Order(order = 3)
    public void testUpdate() {
        Course course = new Course();
        course.setId(id);
        course.setName(name + "-updated");
        
        boolean result = courseService.update(course);
        assertEquals("Failed to update Course.", true, result);
        
        course = new Course();
        course.setId(id);
        try {
            course = (Course) aimStore.retrieve(course);
        } catch (ProjectAimException ex) {
            fail("Failed to update Course: " + ex.getMessage());
        }
        assertEquals(id, course.getId());
        assertEquals(name + "-updated", course.getName());
    }
}
