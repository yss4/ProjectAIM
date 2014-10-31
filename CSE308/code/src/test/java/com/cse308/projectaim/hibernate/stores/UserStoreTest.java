package com.cse308.projectaim.hibernate.stores;

import com.cse308.junit.ordering.Order;
import com.cse308.junit.ordering.OrderedRunner;
import com.cse308.junit.rules.TestCasePrinterRule;
import com.cse308.projectaim.ProjectAimException;
import com.cse308.projectaim.hibernate.AIMEntity;
import com.cse308.projectaim.hibernate.AIMStore;
import com.cse308.projectaim.hibernate.types.User;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(OrderedRunner.class)
public class UserStoreTest {

    @Rule
    public TestCasePrinterRule pr = new TestCasePrinterRule(System.out);
    private static AIMStore aimStore = AIMStore.getInstance();
    private User user;
    private String userName = "test-JACES";
    private String password = "test-password123";
    private String emailAddress = "test-jace@aol.com";
    private Boolean cicStatus = true;
    private Boolean evaluatorStatus = false;
    private static Date startDate = new Date(System.currentTimeMillis());
    private static Date endDate = new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000);

    @Test
    @Order(order = 1)
    public void testCreateValidUser() {
        user = new User();
        user.setUsername(userName);
        user.setPassword(password);
        user.setEmailAddress(emailAddress);
        user.setCicMemberStatus(cicStatus);
        user.setEvaluatorStatus(evaluatorStatus);
        user.setEvaluationStartDate(startDate);
        user.setEvaluationEndDate(endDate);
        try {
            aimStore.create(user);
        } catch (ProjectAimException ex) {
            fail("Failed to create User: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 2)
    public void testCreateNullUser() {
        try {
            user = null;
            aimStore.create(user);
            fail("Created a NULL user.");
        } catch (ProjectAimException ex) {
            assert (true);
        }
    }

    @Test
    @Order(order = 3)
    public void testCreateEmptyUser() {
        try {
            user = new User();
            aimStore.create(user);
            fail("Created an empty user.");
        } catch (ProjectAimException ex) {
            assert (true);
        }
    }

    @Test
    @Order(order = 4)
    public void testRetrieveValidUser() {
        try {
            user = new User();
            user.setUsername(userName);
            user = (User) aimStore.retrieve(user);
            assertEquals(userName, user.getUsername());
            assertEquals(password, user.getPassword());
            assertEquals(emailAddress, user.getEmailAddress());
            assertEquals(cicStatus, user.getCicMemberStatus());
            assertEquals(evaluatorStatus, user.getEvaluatorStatus());
            assertEquals(startDate.toString(), user.getEvaluationStartDate().toString());
            assertEquals(endDate.toString(), user.getEvaluationEndDate().toString());
        } catch (ProjectAimException ex) {
            fail("Failed to retrieve User: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 5)
    public void testRetrieveInvalidUser() {
        try {
            user = new User();
            user.setUsername(userName + "-invalidate");
            user = (User) aimStore.retrieve(user);
            fail("Retrieved an invalid user.");
        } catch (ProjectAimException ex) {
            assert (true);
        }
    }

    @Test
    @Order(order = 6)
    public void testRetrieveNullUser() {
        try {
            user = null;
            user = (User) aimStore.retrieve(user);
            fail("Retrieved an null user.");
        } catch (ProjectAimException ex) {
            assert (true);
        }
    }

    @Test
    @Order(order = 7)
    public void testRetrieveEmptyUser() {
        try {
            user = new User();
            user = (User) aimStore.retrieve(user);
            fail("Retrieved an empty user.");
        } catch (ProjectAimException ex) {
            assert (true);
        }
    }

    @Test
    @Order(order = 8)
    public void testUpdateValidUser() {
        try {
            user = new User();
            user.setUsername(userName);
            user.setPassword(password + "-updated");
            user.setEmailAddress(emailAddress + "-updated");
            user.setCicMemberStatus(!cicStatus);
            user.setEvaluatorStatus(!evaluatorStatus);
            user.setEvaluationStartDate(null);
            user.setEvaluationEndDate(null);
            aimStore.update(user);
            user = new User();
            user.setUsername(userName);
            user = (User) aimStore.retrieve(user);
            assertEquals(userName, user.getUsername());
            assertEquals(password + "-updated", user.getPassword());
            assertEquals(emailAddress + "-updated", user.getEmailAddress());
            assertEquals(!cicStatus, user.getCicMemberStatus());
            assertEquals(!evaluatorStatus, user.getEvaluatorStatus());
            assertEquals(null, user.getEvaluationStartDate());
            assertEquals(null, user.getEvaluationEndDate());
        } catch (ProjectAimException ex) {
            fail("Failed to update User: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 9)
    public void testUpdateInvalidUser() {
        try {
            user = new User();
            user.setUsername(userName + "-invalid");
            aimStore.update(user);
            fail("Updated an invalid user.");
        } catch (ProjectAimException ex) {
            assert (true);
        }
    }

    @Test
    @Order(order = 10)
    public void testUpdateNullUser() {
        try {
            user = null;
            aimStore.update(user);
            fail("Updated an null user.");
        } catch (ProjectAimException ex) {
            assert (true);
        }
    }

    @Test
    @Order(order = 11)
    public void testUpdateEmptyUser() {
        try {
            user = new User();
            aimStore.update(user);
            fail("Updated an empty user.");
        } catch (ProjectAimException ex) {
            assert (true);
        }
    }

    @Test
    @Order(order = 12)
    public void testSearchValidUser() throws ProjectAimException {
        User query;
        try {
            query = new User();
            query.setUsername(userName);
            query.setEmailAddress(emailAddress + "-updated");
            query.setPassword(password + "-updated");
            query.setCicMemberStatus(!cicStatus);
            query.setEvaluatorStatus(!evaluatorStatus);
            if (aimStore.search(query).size() != 1) {
                throw new ProjectAimException("Unexpected Results for case 1.");
            }
            query = new User();
            query.setEmailAddress("george.washington@whitehouse.gov");
            if (!aimStore.search(query).isEmpty()) {
                throw new ProjectAimException("Unexpected Results for case 2.");
            }
        } catch (ProjectAimException ex) {
            fail("Failed to search Users: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 13)
    public void testSearchNullUser() {
        try {
            user = null;
            List<AIMEntity> results = aimStore.search(user);
            fail("Searched for an NULL user.");
        } catch (ProjectAimException ex) {
            assert (true);
        }
    }

    @Test
    @Order(order = 14)
    public void testSearchAllUser() throws ProjectAimException {
        user = new User();
        List<AIMEntity> results = aimStore.search(user);
        assertTrue(!results.isEmpty());
    }

    @Test
    @Order(order = 15)
    public void testDeleteValidUser() throws ProjectAimException {
        try {
            user = new User();
            user.setUsername(userName);
            aimStore.delete(aimStore.retrieve(user));
        } catch (ProjectAimException ex) {
            fail("Failed to delete User: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 16)
    public void testDeleteInvalidUser() throws ProjectAimException {
        try {
            user = new User();
            user.setUsername(userName + "-invalid");
            aimStore.delete(aimStore.retrieve(user));
            fail("Deleted an invalid user.");
        } catch (ProjectAimException ex) {
            assert (true);
        }
    }

    @Test
    @Order(order = 17)
    public void testDeleteNullUser() {
        try {
            user = null;
            aimStore.delete(user);
            fail("Deleted an null user.");
        } catch (ProjectAimException ex) {
            assert (true);
        }
    }

    @Test
    @Order(order = 18)
    public void testDeleteEmptyUser() {
        try {
            user = new User();
            aimStore.delete(user);
            fail("Deleted an empty user.");
        } catch (ProjectAimException ex) {
            assert (true);
        }
    }
}
