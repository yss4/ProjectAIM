package com.cse308.projectaim.services;

import com.cse308.projectaim.ProjectAimException;
import com.cse308.projectaim.beans.UserAccountBean;
import com.cse308.projectaim.hibernate.AIMStore;
import com.cse308.projectaim.hibernate.types.User;
import java.security.NoSuchAlgorithmException;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserAccountServiceTest {

    private Logger logger = Logger.getLogger(getClass());
    private static final String USER_NAME = "uasTest";
    private static final String NEW_USER_NAME = "newUserName";
    private static final String WRONG_USER_NAME = "wrong";
    private static final String PASSWORD = "uasTest";
    private static final String WRONG_PASSWORD = "wrong";

    @BeforeClass
    public static void setUpClass() throws NoSuchAlgorithmException {
		UserAccountService uas =  new UserAccountService();

        User user = new User();
        user.setUsername(USER_NAME);
        user.setPassword(uas.encodePassword(PASSWORD));
        user.setEmailAddress("uasTest@aol.com");
        user.setCicMemberStatus(true);
        user.setEvaluatorStatus(true);

        AIMStore aimStore = AIMStore.getInstance();
        try {
            aimStore.create(user);
        } catch (ProjectAimException ex) {
        }
    }

    @AfterClass
    public static void tearDownClass() {
        AIMStore aimStore = AIMStore.getInstance();
        try {
            User user = new User();
            user.setUsername(USER_NAME);
            aimStore.delete(aimStore.retrieve(user));
            user = new User();
            user.setUsername(NEW_USER_NAME);
            aimStore.delete(aimStore.retrieve(user));
        } catch (ProjectAimException ex) {
        }
    }

    @Test
    public void testSignupUser() throws NoSuchAlgorithmException {
        logger.info("Running testSignupUser()");
        UserAccountService instance = new UserAccountService();
        boolean result = instance.signupUser(makeUserAccountBean(NEW_USER_NAME, PASSWORD));
        assertEquals("This user is already existing user.", true, result);
    }

    @Test
    public void testSignupWithDuplicatedUserName() throws NoSuchAlgorithmException {
        logger.info("Running testSignupWithDuplicatedUserName()");
        UserAccountService instance = new UserAccountService();
        boolean result = instance.signupUser(makeUserAccountBean(USER_NAME, PASSWORD));
        assertEquals("This user is new user.", false, result);
    }

    @Test
    public void testLogin() throws NoSuchAlgorithmException {
        logger.info("Running testLogin()");
        UserAccountService instance = new UserAccountService();
        User dbUser = instance.login(makeUserAccountBean(USER_NAME, PASSWORD));
        assertEquals("User name is different.", USER_NAME, dbUser.getUsername());
        assertEquals("Password is different", instance.encodePassword(PASSWORD), dbUser.getPassword());
    }

    @Test
    public void testLoginWithWrongInformation() throws NoSuchAlgorithmException {
        logger.info("Running testLoginWithWrongInformation()");
        UserAccountService instance = new UserAccountService();
        User wrongUser = instance.login(makeUserAccountBean(USER_NAME, WRONG_PASSWORD));
        assertEquals("Password is right.", null, wrongUser);
        wrongUser = instance.login(makeUserAccountBean(WRONG_USER_NAME, PASSWORD));
        assertEquals("User name is right.", null, wrongUser);
    }

    private UserAccountBean makeUserAccountBean(String username, String password) {
        UserAccountBean uab = new UserAccountBean();
        uab.setUsername(username);
        uab.setPassword(password);
        return uab;
    }
}
