package com.cse308.projectaim.services;

import com.cse308.projectaim.ProjectAimException;
import com.cse308.projectaim.beans.SurveyBean;
import com.cse308.projectaim.hibernate.types.Survey;

public class SurveyService extends AIMService {

    public boolean create(SurveyBean surveyBean) {
        try {
            Survey survey = new Survey(surveyBean);
            aimStore.create(survey);
            surveyBean.setId(survey.getId());
        } catch (ProjectAimException ex) {
            logger.error("Failed to create Survey: " + ex.getMessage());
            return false;
        }
        return true;
    }
}
