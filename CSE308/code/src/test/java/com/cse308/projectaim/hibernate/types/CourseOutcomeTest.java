package com.cse308.projectaim.hibernate.types;

import com.cse308.junit.ordering.Order;
import com.cse308.junit.ordering.OrderedRunner;
import com.cse308.junit.rules.TestCasePrinterRule;
import com.cse308.projectaim.beans.CourseOutcomeBean;
import java.util.HashSet;
import java.util.Set;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.runner.RunWith;

@RunWith(OrderedRunner.class)
public class CourseOutcomeTest {

    @Rule
    public TestCasePrinterRule pr = new TestCasePrinterRule(System.out);
    private static CourseOutcome courseOutcomeOne;
    private static CourseOutcome courseOutcomeTwo;
    private static Integer id = 123;
    private static String description = "Description of test CourseOutcome";
    private static String rationale = "Because of the testing";
    private static Boolean assessed = true;
    private static StudentOutcome studentOutcome;
    private static Course course;

    @BeforeClass
    public static void setUp() {
        courseOutcomeOne = new CourseOutcome();
    }

    @AfterClass
    public static void tearDown() {
        courseOutcomeOne = null;
        courseOutcomeTwo = null;
        id = null;
        description = null;
        rationale = null;
        assessed = null;
        studentOutcome = null;
        course = null;
    }

    @Test
    @Order(order = 1)
    public void testSequenceNumber() {
        Integer expected, actual;
        expected = id;
        courseOutcomeOne.setId(expected);
        actual = courseOutcomeOne.getId();
        assertEquals(expected, actual);
    }

    @Test
    @Order(order = 2)
    public void testDescription() {
        String expected, actual;
        expected = description;
        courseOutcomeOne.setDescription(expected);
        actual = courseOutcomeOne.getDescription();
        assertEquals(expected, actual);
    }

    @Test
    @Order(order = 3)
    public void testRationale() {
        String expected, actual;
        expected = rationale;
        courseOutcomeOne.setRationale(expected);
        actual = courseOutcomeOne.getRationale();
        assertEquals(expected, actual);
    }

    @Test
    @Order(order = 4)
    public void testAssessed() {
        Boolean expected, actual;
        expected = true;
        courseOutcomeOne.setAssessed(assessed);
        actual = courseOutcomeOne.getAssessed();
        assertEquals(expected, actual);
    }

    @Test
    @Order(order = 6)
    public void testStudentOutcome() {
        StudentOutcome actual;
        studentOutcome = new StudentOutcome();
        studentOutcome.setStudentOutcomeId("SO:1212");
        courseOutcomeOne.setStudentOutcome(studentOutcome);
        actual = courseOutcomeOne.getStudentOutcome();
        assertEquals(studentOutcome, actual);
    }

    @Test
    @Order(order = 7)
    public void testEquals() {
        assertTrue(!courseOutcomeOne.equals(courseOutcomeTwo));
        assertTrue(!courseOutcomeOne.equals(studentOutcome));

        courseOutcomeTwo = new CourseOutcome();
        assertTrue(!courseOutcomeOne.equals(courseOutcomeTwo));

        courseOutcomeTwo.setId(id);
        assertTrue(!courseOutcomeOne.equals(courseOutcomeTwo));

        courseOutcomeTwo.setDescription(description);
        assertTrue(!courseOutcomeOne.equals(courseOutcomeTwo));

        /*
         courseOutcomeTwo.setStudentOutcome(studentOutcome);
         assertTrue(!courseOutcomeOne.equals(courseOutcomeTwo));
         */

        courseOutcomeTwo.setRationale(rationale);
        assertTrue(!courseOutcomeOne.equals(courseOutcomeTwo));

        courseOutcomeTwo.setAssessed(true);
        assertTrue(courseOutcomeOne.equals(courseOutcomeTwo));
    }

    @Test
    @Order(order = 8)
    public void testHash() {
        assertEquals(courseOutcomeOne.hashCode(), courseOutcomeTwo.hashCode());
    }

    @Test
    @Order(order = 9)
    public void testToString() {
        assertEquals(courseOutcomeOne.toString(), "CourseOutcome");
    }

    @Test
    @Order(order = 10)
    public void testCourseOutcomeBean() {
        CourseOutcomeBean courseOutcomeBean = new CourseOutcomeBean();
        courseOutcomeBean.setId(id);
        courseOutcomeBean.setDescription(description);
        courseOutcomeBean.setRationale(rationale);
        courseOutcomeBean.setAssessed(assessed);
        //courseOutcomeBean.setStudentOutcome(studentOutcome);
        courseOutcomeBean.setStudentOutcome(null);
        courseOutcomeTwo = new CourseOutcome(courseOutcomeBean);
        assertEquals(id, courseOutcomeTwo.getId());
        assertEquals(description, courseOutcomeTwo.getDescription());
        assertEquals(rationale, courseOutcomeTwo.getRationale());
        assertEquals(assessed, courseOutcomeTwo.getAssessed());
        //assertEquals(studentOutcome, courseOutcomeTwo.getStudentOutcome());
        assertEquals(null, courseOutcomeTwo.getStudentOutcome());
    }
}