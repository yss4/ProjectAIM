package com.cse308.projectaim.services;

import com.cse308.projectaim.ProjectAimException;
import com.cse308.projectaim.beans.UserAccountBean;
import com.cse308.projectaim.hibernate.types.User;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserAccountService extends AIMService {

    /**
     * It registers a user into DB. The false as return means that there is a
     * duplicated user name in DB.
     *
     * @param loginBean information which is based on user's input to sign up
     * @return success of signup
     */
    public boolean signupUser(UserAccountBean signupBean) throws NoSuchAlgorithmException {
        User newUser = convertToUser(signupBean);
        logger.info("Make new User with information in signupBean");
        User dbUser = null;

        try {
            logger.info("Try to retrieve User object having same userName in new User from DB");
            dbUser = (User) aimStore.retrieve(newUser);
        } catch (ProjectAimException ex) {
            logger.debug("Not existing user in DB");
        }
        if (isValidToCreate(dbUser)) {
            try {
                logger.info("If same userName doesn't exist in DB");
				newUser.setPassword(encodePassword(newUser.getPassword()));
                aimStore.create(newUser);
                logger.info("Create new User successfully in DB");
                return true;
            } catch (ProjectAimException ex) {
                logger.debug(ex.getMessage());
            }
        }
        logger.info("Fail to create new User in DB");
        return false;
    }

	public String encodePassword(String plainPassword) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(plainPassword.getBytes(), 0, plainPassword.length());
		String encryptedPassword = new BigInteger(1, md.digest()).toString(16);
		logger.info("Transformation Password: " + plainPassword + " -> " + encryptedPassword);
		return encryptedPassword;
	}

    /**
     * check user to insert user's information into DB
     *
     * @param user The user to insert
     * @return validation to insert
     */
    private boolean isValidToCreate(User user) {
        return null == user;
    }

    /**
     * It converts UserAccountBean to User.
     *
     * @param userAccountBean from User
     * @return User object that has information from a user.
     */
    private User convertToUser(UserAccountBean uab) {
        User result = new User();
        result.setUsername(uab.getUsername());
        result.setPassword(uab.getPassword());
        result.setEmailAddress(uab.getEmailAddress());
        result.setCicMemberStatus(uab.getCicMemberStatus());
        result.setEvaluatorStatus(uab.getEvaluatorStatus());
        result.setDegreePrograms(uab.getDegreePrograms());
        return result;
    }

    /**
     * After login, it retrieves user's information. The null as return means
     * there is wrong with user password.
     *
     * @param loginBean The user who wants to login.
     * @return user information
     */
    public User login(UserAccountBean loginBean) throws NoSuchAlgorithmException {
        logger.info("Make new User with information in loginBean");
        User user = convertToUser(loginBean);
        User result = null;
        try {
            logger.info("Try to retrieve User object having same userName in new User from DB");
            User dbUser = (User) aimStore.retrieve(user);
            logger.info("Determine whether returned User obect's password retrieved from DB is valid");
            if (validPassword(user, dbUser)) {
                logger.info("Login Successfully");
                result = dbUser;
            } else {
                logger.info("Not valid password");
            }
        } catch (ProjectAimException ex) {
            logger.info("[UserAccountService.login()] Not existing user in DB");
        }
        return result;
    }

    /**
     * check whether user password is right or not.
     *
     * @param user information from user
     * @param dbUser information from DB
     * @return The true means password is the same as in DB.
     */
    private boolean validPassword(User user, User dbUser) throws NoSuchAlgorithmException {
		String inputPassword = user.getPassword();
		inputPassword = encodePassword(inputPassword);

		String dbUserPassword = dbUser.getPassword();

		logger.info("[Authentication]input password: " + inputPassword);
		logger.info("[Authentication]db user password: " + dbUserPassword);
		logger.info("[Authentication]Is it match? " + inputPassword.equals(dbUserPassword));

		return dbUserPassword.equals(inputPassword);
    }
}
