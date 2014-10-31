package com.cse308.projectaim.services;

import com.cse308.junit.ordering.Order;
import com.cse308.junit.ordering.OrderedRunner;
import com.cse308.junit.rules.TestCasePrinterRule;
import com.cse308.projectaim.ProjectAimException;
import com.cse308.projectaim.beans.CourseOfferingBean;
import com.cse308.projectaim.hibernate.AIMEntity;
import com.cse308.projectaim.hibernate.AIMStore;
import com.cse308.projectaim.hibernate.types.Course;
import com.cse308.projectaim.hibernate.types.CourseOffering;
import com.cse308.projectaim.hibernate.types.Semester;
import com.cse308.projectaim.hibernate.types.SemesterPK;
import com.cse308.projectaim.hibernate.types.User;
import java.util.List;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.runner.RunWith;

@RunWith(OrderedRunner.class)
public class CourseOfferingServiceTest {

    @Rule
    public TestCasePrinterRule pr = new TestCasePrinterRule(System.out);
    private static AIMStore aimStore = AIMStore.getInstance();
    private CourseOfferingService courseOfferingService = new CourseOfferingService();
    private static int sectionNumber;
    private static User instructor;
    private static Course course;
    private static Semester semester;
    private static SemesterPK semesterPK;

    @BeforeClass
    public static void setUpClass() {
        instructor = new User();
        instructor.setUsername("Leo Bachmair");

        course = new Course();
        course.setId("CSE303");

        semester = new Semester();
        semesterPK = new SemesterPK();
        semesterPK.setTerm(1);
        semesterPK.setYear(2013);
        semester.setSemester(semesterPK);

        try {
            instructor = (User) aimStore.retrieve(instructor);
            course = (Course) aimStore.retrieve(course);
            semester = (Semester) aimStore.retrieve(semester);
        } catch (ProjectAimException ex) {
            fail("Failed to initialize CourseOffering tests: " + ex.getMessage());
        }
    }

    @AfterClass
    public static void tearDownClass() {
        CourseOffering courseOffering = new CourseOffering();
        courseOffering.setSectionNumber(sectionNumber);
        try {
            aimStore.delete(courseOffering);
        } catch (ProjectAimException ex) {
            fail("Failed to cleanup after CourseOfferingService tests: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 1)
    public void testCreate() {
        CourseOfferingBean courseOfferingBean = new CourseOfferingBean();
        courseOfferingBean.setInstructor(instructor);
        courseOfferingBean.setCourse(course);
        courseOfferingBean.setSemester(semester);

        boolean result = courseOfferingService.create(courseOfferingBean);
        assertEquals(true, result);
        sectionNumber=courseOfferingBean.getSectionNumber();
    }

    @Test
    @Order(order = 2)
    public void testGetAll() {
        List<AIMEntity> list = courseOfferingService.getAll(new CourseOffering());
        assertTrue(1 <= list.size());
    }

    @Test
    @Order(order = 3)
    public void testUpdate() {
        CourseOffering courseOffering = new CourseOffering();
        courseOffering.setSectionNumber(sectionNumber);
        courseOffering.setSemester(semester);
        courseOffering.setInstructor(instructor);
        courseOffering.setCourse(course);

        boolean result = courseOfferingService.update(courseOffering);
        assertEquals("Failed to update CourseOffering.", true, result);

        courseOffering = new CourseOffering();
        courseOffering.setSectionNumber(sectionNumber);
        try {
            courseOffering = (CourseOffering) aimStore.retrieve(courseOffering);
        } catch (ProjectAimException ex) {
            fail("Failed to update CourseOffering: " + ex.getMessage());
        }
        assertEquals(sectionNumber, courseOffering.getSectionNumber());
        assertEquals(semester, courseOffering.getSemester());
        assertEquals(instructor, courseOffering.getInstructor());
        assertEquals(course, courseOffering.getCourse());
    }
}
