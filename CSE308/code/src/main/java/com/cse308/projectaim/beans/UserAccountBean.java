package com.cse308.projectaim.beans;

import com.cse308.projectaim.hibernate.types.User;

public class UserAccountBean extends User {

	private int loginStatus = UserState.LOGIN_BEFORE;
    
    public UserAccountBean() { }

    public int getLoginStatus() { return loginStatus; }
    public void setLoginStatus(int loginStatus) { this.loginStatus = loginStatus; }
}
