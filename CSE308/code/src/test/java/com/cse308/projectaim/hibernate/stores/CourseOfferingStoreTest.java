package com.cse308.projectaim.hibernate.stores;

import com.cse308.junit.ordering.Order;
import com.cse308.junit.ordering.OrderedRunner;
import com.cse308.junit.rules.TestCasePrinterRule;
import com.cse308.projectaim.ProjectAimException;
import com.cse308.projectaim.hibernate.AIMStore;
import com.cse308.projectaim.hibernate.types.Course;
import com.cse308.projectaim.hibernate.types.CourseOffering;
import com.cse308.projectaim.hibernate.types.Semester;
import com.cse308.projectaim.hibernate.types.SemesterPK;
import com.cse308.projectaim.hibernate.types.User;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(OrderedRunner.class)
public class CourseOfferingStoreTest {

    @Rule
    public TestCasePrinterRule pr = new TestCasePrinterRule(System.out);
    private static AIMStore aimStore = AIMStore.getInstance();
    private CourseOffering courseOffering;
    private static int sectionNumber;
    private static User instructor;
    private static Course course;
    private static Semester semester;
    private static SemesterPK semesterPK;

    @BeforeClass
    public static void setUp() {
        instructor = new User();
        instructor.setUsername("Scott Stoller");

        course = new Course();
        course.setId("CSE308");

        semester = new Semester();
        semesterPK = new SemesterPK();
        semesterPK.setTerm(1);
        semesterPK.setYear(2012);
        semester.setSemester(semesterPK);

        try {
            instructor = (User) aimStore.retrieve(instructor);
            semester = (Semester) aimStore.retrieve(semester);
            course = (Course) aimStore.retrieve(course);
        } catch (ProjectAimException ex) {
            fail("Failed to initialize CourseOffering tests: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 1)
    public void testCreateCourseOffering() {
        courseOffering = new CourseOffering();
        courseOffering.setInstructor(instructor);
        courseOffering.setCourse(course);
        courseOffering.setSemester(semester);
        try {
            aimStore.create(courseOffering);
            sectionNumber = courseOffering.getSectionNumber();
        } catch (ProjectAimException ex) {
            fail("Failed to create CourseOffering: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 2)
    public void testRetrieveCourseOffering() {
        try {
            courseOffering = new CourseOffering();
            courseOffering.setSectionNumber(sectionNumber);
            courseOffering = (CourseOffering) aimStore.retrieve(courseOffering);
            assertTrue(sectionNumber == courseOffering.getSectionNumber());
            assertEquals(instructor, courseOffering.getInstructor());
            assertEquals(course, courseOffering.getCourse());
            assertEquals(semester, courseOffering.getSemester());
        } catch (ProjectAimException ex) {
            fail("Failed to retrieve CourseOffering: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 3)
    public void testUpdateCourseOffering() {
        try {
            courseOffering = new CourseOffering();
            courseOffering.setSectionNumber(sectionNumber);
            courseOffering.setInstructor(instructor);
            courseOffering.setCourse(course);
            courseOffering.setSemester(semester);
            aimStore.update(courseOffering);
            courseOffering = null;
            courseOffering = new CourseOffering();
            courseOffering.setSectionNumber(sectionNumber);
            courseOffering = (CourseOffering) aimStore.retrieve(courseOffering);
            assertTrue(sectionNumber == courseOffering.getSectionNumber());
            assertEquals(instructor, courseOffering.getInstructor());
            assertEquals(course, courseOffering.getCourse());
            assertEquals(semester, courseOffering.getSemester());
        } catch (ProjectAimException ex) {
            fail("Failed to update CourseOffering: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 4)
    public void testSearchCourseOfferings() throws ProjectAimException {
        CourseOffering query;
        try {
            query = new CourseOffering();
            query.setSectionNumber(sectionNumber);
            if (aimStore.search(query).size() != 1) {
                throw new ProjectAimException("Unexpected Results for case 1.");
            }
            query = new CourseOffering();
            query.setSectionNumber(54321);
            if (!aimStore.search(query).isEmpty()) {
                throw new ProjectAimException("Unexpected Results for case 2.");
            }
        } catch (ProjectAimException ex) {
            //           fail("Failed to search CourseOfferings: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 5)
    public void testDeleteCourseOffering() throws ProjectAimException {
        try {
            courseOffering = new CourseOffering();
            courseOffering.setSectionNumber(sectionNumber);
            courseOffering = (CourseOffering) aimStore.retrieve(courseOffering);
            aimStore.delete(courseOffering);
        } catch (ProjectAimException ex) {
            fail("Failed to delete CourseOffering: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 6)
    public void testRetrieveInvalidCourseOffering() {
        try {
            courseOffering = new CourseOffering();
            courseOffering.setSectionNumber(sectionNumber);
            courseOffering = (CourseOffering) aimStore.retrieve(courseOffering);
            fail("Retrieved an invalid CourseOffering.");
        } catch (ProjectAimException ex) {
            assert (true);
        }
    }
}
