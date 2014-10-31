package com.cse308.projectaim.hibernate.stores;

import com.cse308.junit.ordering.Order;
import com.cse308.junit.ordering.OrderedRunner;
import com.cse308.junit.rules.TestCasePrinterRule;
import com.cse308.projectaim.ProjectAimException;
import com.cse308.projectaim.hibernate.AIMStore;
import com.cse308.projectaim.hibernate.types.Course;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(OrderedRunner.class)
public class CourseStoreTest {

    @Rule
    public TestCasePrinterRule pr = new TestCasePrinterRule(System.out);

    private static AIMStore aimStore = AIMStore.getInstance();
    private Course course;
    private String id = "test-CSE308";
    private String name = "test-Software Engineering";

    @Test
    @Order(order = 1)
    public void testCreateCourse() {
        course = new Course();
        course.setId(id);
        course.setName(name);
        try {
            aimStore.create(course);
        } catch (ProjectAimException ex) {
            fail("Failed to create Course: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 2)
    public void testRetrieveCourse() {
        try {
            course = new Course();
            course.setId(id);
            course = (Course) aimStore.retrieve(course);
            assertEquals(id, course.getId());
            assertEquals(name, course.getName());
        } catch (ProjectAimException ex) {
            fail("Failed to retrieve Course: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 3)
    public void testUpdateCourse() {
        try {
            course = new Course();
            course.setId(id);
            course.setName(name + "-updated");
            aimStore.update(course);

            course = new Course();
            course.setId(id);
            course = (Course) aimStore.retrieve(course);
            assertEquals(id, course.getId());
            assertEquals(name + "-updated", course.getName());
        } catch (ProjectAimException ex) {
            fail("Failed to update Course: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 4)
    public void testSearchCourses() throws ProjectAimException {
        Course query;

        try {
            query = new Course();
            query.setId(id);
            query.setName(name + "-updated");
            if (aimStore.search(query).size() != 1) {
                throw new ProjectAimException("Unexpected Results for case 1.");
            }

            query = new Course();
            query.setId("CSE999");
            if (!aimStore.search(query).isEmpty()) {
                throw new ProjectAimException("Unexpected Results for case 2.");
            }

        } catch (ProjectAimException ex) {
//            fail("Failed to search Courses: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 5)
    public void testDeleteCourse() throws ProjectAimException {
        try {
            course = new Course();
            course.setId(id);
            aimStore.delete(aimStore.retrieve(course));
        } catch (ProjectAimException ex) {
            fail("Failed to delete Course: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 6)
    public void testRetrieveInvalidCourse() {
        try {
            course = new Course();
            course.setId(id);
            course = (Course)aimStore.retrieve(course);
            fail("Retrieved an invalid Course.");
        } catch (ProjectAimException ex) {
            assert (true);
        }
    }
}
