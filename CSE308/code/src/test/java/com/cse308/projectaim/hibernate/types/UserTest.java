package com.cse308.projectaim.hibernate.types;

import com.cse308.junit.ordering.Order;
import com.cse308.junit.ordering.OrderedRunner;
import com.cse308.junit.rules.TestCasePrinterRule;
import java.util.Date;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.runner.RunWith;

@RunWith(OrderedRunner.class)
public class UserTest {

    @Rule
    public TestCasePrinterRule pr = new TestCasePrinterRule(System.out);

    private static User userOne;
    private static User userTwo;
    private String username = "user-cse308-aim";
    private String password = "XXXp4ssw0rdXXX";
    private String eMailAddress = "bozo@ringling.com";
    private static Date start, end;

    @BeforeClass
    public static void setUp() {
        userOne = new User();
    }

    @AfterClass
    public static void tearDown() {
        userOne = null;
        userTwo = null;
        start = null;
        end = null;
    }

    @Test
    @Order(order = 1)
    public void testUsername() {
        String expected, actual;
        expected = username;
        userOne.setUsername(expected);
        actual = userOne.getUsername();
        assertEquals(expected, actual);
    }

    @Test
    @Order(order = 2)
    public void testPassword() {
        String expected, actual;
        expected = password;
        userOne.setPassword(expected);
        actual = userOne.getPassword();
        assertEquals(expected, actual);
    }

    @Test
    @Order(order = 3)
    public void testEMailAddress() {
        String expected, actual;
        expected = eMailAddress;
        userOne.setEmailAddress(expected);
        actual = userOne.getEmailAddress();
        assertEquals(expected, actual);
    }

    @Test
    @Order(order = 4)
    public void testCICStatus() {
        Boolean expected, actual;
        expected = true;
        userOne.setCicMemberStatus(expected);
        actual = userOne.getCicMemberStatus();
        assertEquals(expected, actual);
    }

    @Test
    @Order(order = 5)
    public void testEvaluatorStatus() {
        Boolean expected, actual;
        expected = true;
        userOne.setEvaluatorStatus(expected);
        actual = userOne.getEvaluatorStatus();
        assertEquals(expected, actual);
    }

    @Test
    @Order(order = 6)
    public void testEvaluationStartDate() {
        Date actual;
        start = new Date(System.currentTimeMillis());
        userOne.setEvaluationStartDate(start);
        actual = userOne.getEvaluationStartDate();
        assertEquals(start, actual);
    }

    @Test
    @Order(order = 7)
    public void testEvaluationEndDate() {
        Date actual;
        end = new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000);
        userOne.setEvaluationEndDate(end);
        actual = userOne.getEvaluationEndDate();
        assertEquals(end, actual);
    }

    @Test
    @Order(order = 8)
    public void testEquals() {
        assertTrue(!userOne.equals(userTwo));
        assertTrue(!userOne.equals(new Course()));

        userTwo = new User();
        assertTrue(!userOne.equals(userTwo));

        userTwo.setUsername(username);
        assertTrue(!userOne.equals(userTwo));

        userTwo.setPassword(password);
        assertTrue(!userOne.equals(userTwo));

        userTwo.setEmailAddress(eMailAddress);
        assertTrue(!userOne.equals(userTwo));

        userTwo.setUsername(username);
        assertTrue(!userOne.equals(userTwo));

        userTwo.setCicMemberStatus(true);
        assertTrue(!userOne.equals(userTwo));

        userTwo.setEvaluatorStatus(true);
        assertTrue(!userOne.equals(userTwo));

        userTwo.setEvaluationStartDate(start);
        assertTrue(!userOne.equals(userTwo));

        userTwo.setEvaluationEndDate(end);
        assertTrue(userOne.equals(userTwo));
    }

    @Test
    @Order(order = 12)
    public void testHash() {
        assertEquals(userOne.hashCode(), userTwo.hashCode());
    }

    @Test
    @Order(order = 13)
    public void testToString() {
        assertEquals(userOne.toString(), "User");
    }
}