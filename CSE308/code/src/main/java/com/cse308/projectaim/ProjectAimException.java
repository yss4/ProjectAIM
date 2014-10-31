package com.cse308.projectaim;

public class ProjectAimException extends Exception {
    public ProjectAimException(String message) {
        super(message);
    }

    public ProjectAimException(String message, Exception e) {
        super(message, e);
    }
}
