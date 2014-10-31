package com.cse308.projectaim.constants;

public class Constants {
    /*
     * These constants are what should be used in all queries as opposed to
     * hardcoding the names of the tables in the database.
     */

    public static final String DB_TABLE_USER = "aimUser";
    public static final String DB_COL_USER_USERNAME = "userName";
    public static final String DB_COL_USER_EMAIL_ADDRESS = "emailAddress";
    public static final String DB_COL_USER_PASSWORD = "password";
    public static final String DB_COL_USER_CIC_STATUS = "cicMemberStatus";
    public static final String DB_COL_USER_EVALUATOR_STATUS = "evaluatorStatus";
    public static final String DB_COL_USER_EVALUATOR_START_DATE = "Evaluation_Start_Date";
    public static final String DB_COL_USER_EVALUATOR_END_DATE = "Evaluation_End_Date";
    public static final String DB_COL_USER_DEGREE_PROGRAMS = "Degree_Program";

    public static final String DB_TABLE_DEGREE_PROGRAM = "Degree_Program";
    public static final String DB_COL_DEGREE_PROGRAM_ID = "Degree_Program_ID";
    public static final String DB_COL_DEGREE_PROGRAM_DESCRIPTION = "Description";
    public static final String DB_COL_DEGREE_PROGRAM_DEPARTMENT = "Department";
    public static final String DB_COL_DEGREE_PROGRAM_PEOS = "PEOs";
    public static final String DB_COL_DEGREE_PROGRAM_STUDENT_OUTCOMES = "Student_Outcomes";
    public static final String DB_COL_DEGREE_PROGRAM_SURVEY = "Survey";
    public static final String DB_COL_DEGREE_PROGRAM_USERS = "Users";

    public static final String DB_TABLE_PEO = "PEO";
    public static final String DB_COL_PEO_ID = "PEO_ID";
    public static final String DB_COL_PEO_DISPLAY_NAME = "Short_Name";
    public static final String DB_COL_PEO_DESCRIPTION = "Description";
    public static final String DB_COL_PEO_SEQUENCE = "PEO_Sequence";
    public static final String DB_COL_PEO_DEGREE_PROGRAM = "Degree_Program";
    public static final String DB_COL_PEO_TARGET_ATTAINMENT_LEVEL = "Target_Attainment_Level";
    
    public static final String DB_TABLE_STUDENT_OUTCOME = "Student_Outcome";
    public static final String DB_COL_STUDENT_OUTCOME_ID = "Student_Outcome_ID";
    public static final String DB_COL_STUDENT_OUTCOME_DISPLAY_NAME = "Short_Name";
    public static final String DB_COL_STUDENT_OUTCOME_DESCRIPTION = "Description";
    public static final String DB_COL_STUDENT_OUTCOME_SEQUENCE = "Student_Outcome_Sequence";
    public static final String DB_COL_STUDENT_OUTCOME_DEGREE_PROGRAM = "Degree_Program";

    public static final String DB_TABLE_SEMESTER = "Semester";
    public static final String DB_COL_SEMESTER_DISPLAY_NAME = "Display_Name";
    public static final String DB_COL_SEMESTER_TERM = "Semester_Term";
    public static final String DB_COL_SEMESTER_YEAR = "Semester_Year";
    public static final String DB_COL_SEMESTER_START_DATE = "Start_Date";
    public static final String DB_COL_SEMESTER_END_DATE = "End_Date";

    public static final String DB_TABLE_COURSE = "Course";
    public static final String DB_COL_COURSE_ID = "Course_ID";
    public static final String DB_COL_COURSE_NAME = "Name";
    public static final String DB_COL_COURSE_DEGREE_PROGRAMS = "Degree_Programs";
    public static final String DB_COL_COURSE_COURSE_OUTCOMES = "Course_Outcomes";
    public static final String DB_COL_COURSE_PRIMARY_COORDINATOR = "Primary_Coordinator";
    public static final String DB_COL_COURSE_ALTERNATE_COORDINATORS = "Alternate_Coordinators";

    public static final String DB_TABLE_COURSE_OFFERING = "Course_Offering";
    public static final String DB_COL_COURSE_OFFERING_SECTION_NUMBER = "Section_Number";
    public static final String DB_COL_COURSE_OFFERING_SEMESTER = "Semester";
    public static final String DB_COL_COURSE_OFFERING_COURSE = "Course";
    public static final String DB_COL_COURSE_OFFERING_INSTRUCTOR = "Instructor";
    public static final String DB_COL_COURSE_OFFERING_SYLLABUS = "Syllabus";
    public static final String DB_COL_COURSE_OFFERING_LECTURE_SCHEDULE = "Lecture_Schedule";
    public static final String DB_COL_COURSE_OFFERING_END_OF_SEMESTER_REPORT = "End_Of_Semester_Report";
    public static final String DB_COL_COURSE_OFFERING_COURSE_COORDINATOR_REPORT = "Course_Coordinator_Report";
    public static final String DB_COL_COURSE_OFFERING_CIC_REPORT = "CIC_Report";
    public static final String DB_COL_COURSE_OFFERING_LECTURE_NOTES = "Lecture_Notes";
    public static final String DB_COL_COURSE_OFFERING_ASSIGNMENTS = "Assignments";
    public static final String DB_COL_COURSE_OFFERING_COURSE_ATTAINMENT_TARGET = "Course_Attainment_Target";
    public static final String DB_COL_COURSE_OFFERING_COURSE_OUTCOME_SURVEY_RESULTS = "Course_Outcome_Survey_Results";
    public static final String DB_COL_COURSE_OFFERING_COURSE_OUTCOME_DIRECT_ASSESSMENTS = "Course_Outcome_Direct_Assessments";
    
    public static final String DB_TABLE_COURSE_OUTCOME = "Course_Outcome";
    public static final String DB_COL_COURSE_OUTCOME_SEQUENCE_NUMBER = "Sequence_Number";
    public static final String DB_COL_COURSE_OUTCOME_DESCRIPTION = "Description";
    public static final String DB_COL_COURSE_OUTCOME_STUDENT_OUTCOME = "Student_Outcome";
    public static final String DB_COL_COURSE_OUTCOME_RATIONALE = "Rationale";
    public static final String DB_COL_COURSE_OUTCOME_ASSESSED = "Assessed";

    public static final String DB_TABLE_COURSE_OUTCOME_DIRECT_ASSESSMENT = "Course_Outcome_Direct_Assessment";
    public static final String DB_COL_COURSE_OUTCOME_DIRECT_ASSESSMENT_COURSE_OUTCOME = "Course_Outcome";
    public static final String DB_COL_COURSE_OUTCOME_DIRECT_ASSESSMENT_ASSESSMENT_INSTRUMENT = "Assessment_Instrument";
    public static final String DB_COL_COURSE_OUTCOME_DIRECT_ASSESSMENT_RATIONALE = "Rationale";
    public static final String DB_COL_COURSE_OUTCOME_DIRECT_ASSESSMENT_THRESHOLD_SCORE = "Threshold_Score";
    public static final String DB_COL_COURSE_OUTCOME_DIRECT_ASSESSMENT_ATTAINMENT_LEVEL = "Attainment_Level";

    public static final String DB_TABLE_SURVEY = "Survey";
    public static final String DB_COL_SURVEY_DEGREE_PROGRAMS = "Degree_Programs";
    public static final String DB_COL_SURVEY_SURVEY_GROUP = "Survey_Group";
    public static final String DB_COL_SURVEY_INITIATOR = "Intitiator";
    public static final String DB_COL_SURVEY_SEMESTER = "Semester";
    public static final String DB_COL_SURVEY_RESULTS = "Results";
    public static final String DB_COL_SURVEY_PEO_ATTAINMENT_LEVEL = "PEO_Attainment_Level";
}
