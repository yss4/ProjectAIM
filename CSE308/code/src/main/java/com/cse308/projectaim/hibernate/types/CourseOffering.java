package com.cse308.projectaim.hibernate.types;

import com.cse308.projectaim.beans.CourseOfferingBean;
import com.cse308.projectaim.hibernate.AIMEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity(name = "AIM_ENTITY_COURSE_OFFERING")
public class CourseOffering implements AIMEntity, Serializable {
    /***************************************************************************
     * Information maintained about each CourseOffering includes:              *
     * ************************************************************************/
    /* Section: Section Number                                                */
    private int sectionNumber;

    /* Semester: Semester in which this Course Offering was held              */
    private Semester semester;

    /* Course: Course of which this is an offering                            */
    private Course course;

    /* Instructor: Instructor of this Course Offering                         */
    private User instructor;

    /* Syllabus: File containing the syllabus                                 */
    private AIMFileWrapper syllabus;

    /* Lecture Schedule: File containing the schedule of lecture topics       */
    private AIMFileWrapper lectureSchedule;
    
    /* End-of-Semester Report: File containing the instructor's eos report    */
    private AIMFileWrapper endOfSemesterReport;
    
    /* Course Coordinator Report: File containing the coordinator's comments  */
    private AIMFileWrapper courseCoordinatorReport;
    
    /* CIC report: File containing the CIC's comments on the course offering  */
    private AIMFileWrapper cicReport;
    
    /* Lecture Notes: Files containing the lecture notes                      */
    private List<AIMFileWrapper> lectureNotes;

    /* Assignments: Assignments given to students in this class               */
    private List<Assignment> assignments;

    /* Course Outcome Survey Results                                          */
    private double courseOutcomeSurveyResults;
    
    /* Course Outcome Attainment Target for each course outcome               */
    private Set<AttainmentLevel> courseOutcomeAttainmentTargets;

    /* Course Outcome Direct Assessments                                      */
    private Set<CourseOutcomeDirectAssessment> courseOutcomeDirectAssessments;

    public CourseOffering() {
        lectureNotes = new ArrayList<AIMFileWrapper>();
        assignments = new ArrayList<Assignment>();
        courseOutcomeAttainmentTargets = new HashSet<AttainmentLevel>();
        courseOutcomeDirectAssessments = new HashSet<CourseOutcomeDirectAssessment>();
    }

    public CourseOffering(CourseOfferingBean courseOfferingBean) {
        sectionNumber = courseOfferingBean.getSectionNumber();
        semester = courseOfferingBean.getSemester();
        course = courseOfferingBean.getCourse();
        instructor = courseOfferingBean.getInstructor();
        syllabus = courseOfferingBean.getSyllabus();
        lectureSchedule = courseOfferingBean.getLectureSchedule();
        endOfSemesterReport = courseOfferingBean.getEndOfSemesterReport();
        courseCoordinatorReport = courseOfferingBean.getCourseCoordinatorReport();
        cicReport = courseOfferingBean.getCicReport();
        lectureNotes = courseOfferingBean.getLectureNotes();
        assignments = courseOfferingBean.getAssignments();
        courseOutcomeDirectAssessments = courseOfferingBean.getCourseOutcomeDirectAssessments();
        courseOutcomeAttainmentTargets = courseOfferingBean.getCourseOutcomeAttainmentTargets();
    }

    @Id
    @GeneratedValue
    @Column(name = "COURSE_OFFERING_SECTION_NUMBER")
    public int getSectionNumber() {
        return sectionNumber;
    }

    public void setSectionNumber(int sectionNumber) {
        this.sectionNumber = sectionNumber;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "JOIN_COURSE_OFFERINGS_SEMESTERS",
            joinColumns = {
        @JoinColumn(name = "COURSE_OFFERING_SECTION_NUMBER")},
            inverseJoinColumns = {
        @JoinColumn(name = "SEMESTER_YEAR"),
        @JoinColumn(name = "SEMESTER_TERM")
    })
    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "JOIN_COURSE_OFFERINGS_COURSES",
            joinColumns = {
        @JoinColumn(name = "COURSE_OFFERING_SECTION_NUMBER")},
            inverseJoinColumns = {
        @JoinColumn(name = "COURSE_ID")})
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "JOIN_COURSE_OFFERINGS_INSTRUCTORS",
            joinColumns = {
        @JoinColumn(name = "COURSE_OFFERING_SECTION_NUMBER")},
            inverseJoinColumns = {
        @JoinColumn(name = "USER_USERNAME")})
    public User getInstructor() {
        return instructor;
    }

    public void setInstructor(User instructor) {
        this.instructor = instructor;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "JOIN_COURSE_OFFERINGS_SYLLABUSES",
            joinColumns = {
        @JoinColumn(name = "COURSE_OFFERING_SECTION_NUMBER")},
            inverseJoinColumns = {
        @JoinColumn(name = "FILE_ID")})
    @Cascade(CascadeType.SAVE_UPDATE)
    public AIMFileWrapper getSyllabus() {
        return syllabus;
    }

    public void setSyllabus(AIMFileWrapper syllabus) {
        this.syllabus = syllabus;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "JOIN_COURSE_OFFERINGS_LECTURE_SCHEDULES",
            joinColumns = {
        @JoinColumn(name = "COURSE_OFFERING_SECTION_NUMBER")},
            inverseJoinColumns = {
        @JoinColumn(name = "FILE_ID")})
    @Cascade(CascadeType.SAVE_UPDATE)
    public AIMFileWrapper getLectureSchedule() {
        return lectureSchedule;
    }

    public void setLectureSchedule(AIMFileWrapper lectureSchedule) {
        this.lectureSchedule = lectureSchedule;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "JOIN_COURSE_OFFERINGS_END_OF_SEMESTER_REPORTS",
            joinColumns = {
        @JoinColumn(name = "COURSE_OFFERING_SECTION_NUMBER")},
            inverseJoinColumns = {
        @JoinColumn(name = "FILE_ID")})
    @Cascade(CascadeType.SAVE_UPDATE)
    public AIMFileWrapper getEndOfSemesterReport() {
        return endOfSemesterReport;
    }

    public void setEndOfSemesterReport(AIMFileWrapper endOfSemesterReport) {
        this.endOfSemesterReport = endOfSemesterReport;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "JOIN_COURSE_OFFERINGS_COURSE_COORDINATOR_REPORTS",
            joinColumns = {
        @JoinColumn(name = "COURSE_OFFERING_SECTION_NUMBER")},
            inverseJoinColumns = {
        @JoinColumn(name = "FILE_ID")})
    @Cascade(CascadeType.SAVE_UPDATE)
    public AIMFileWrapper getCourseCoordinatorReport() {
        return courseCoordinatorReport;
    }

    public void setCourseCoordinatorReport(AIMFileWrapper courseCoordinatorReport) {
        this.courseCoordinatorReport = courseCoordinatorReport;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "JOIN_COURSE_OFFERING_CIC_REPORTS",
            joinColumns = {
        @JoinColumn(name = "COURSE_OFFERING_SECTION_NUMBER")},
            inverseJoinColumns = {
        @JoinColumn(name = "FILE_ID")})
    @Cascade(CascadeType.SAVE_UPDATE)
    public AIMFileWrapper getCicReport() {
        return cicReport;
    }

    public void setCicReport(AIMFileWrapper cicReport) {
        this.cicReport = cicReport;
    }

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "JOIN_COURSE_OFFERINGS_LECTURE_NOTES",
            joinColumns = {
        @JoinColumn(name = "COURSE_OFFERING_SECTION_NUMBER")},
            inverseJoinColumns = {
        @JoinColumn(name = "FILE_ID")})
    @Fetch(value = FetchMode.SUBSELECT)
    @Cascade(CascadeType.SAVE_UPDATE)
    public List<AIMFileWrapper> getLectureNotes() {
        return lectureNotes;
    }

    public void setLectureNotes(List<AIMFileWrapper> lectureNotes) {
        this.lectureNotes = lectureNotes;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "JOIN_COURSE_OFFERINGS_ASSIGNMENTS",
            joinColumns = {
        @JoinColumn(name = "COURSE_OFFERING_SECTION_NUMBER")},
            inverseJoinColumns = {
        @JoinColumn(name = "ASSIGNMENT_NAME")})
    @Fetch(value = FetchMode.SUBSELECT)
    @Cascade(CascadeType.SAVE_UPDATE)
    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "JOIN_COURSE_OFFERINGS_CODAS",
            joinColumns = {
        @JoinColumn(name = "COURSE_OFFERING_SECTION_NUMBER")},
            inverseJoinColumns = {
        @JoinColumn(name = "CODA_ID")})
    @Fetch(value = FetchMode.SUBSELECT)
    public Set<CourseOutcomeDirectAssessment> getCourseOutcomeDirectAssessments() {
        return courseOutcomeDirectAssessments;
    }

    public void setCourseOutcomeDirectAssessments(Set<CourseOutcomeDirectAssessment> codas) {
        this.courseOutcomeDirectAssessments = codas;
    }

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "JOIN_ATTAINMENT_TARGETS_COURSE_OFFERINGS",
            joinColumns = {
        @JoinColumn(name = "COURSE_OFFERING_SECTION_NUMBER")},
            inverseJoinColumns = {
        @JoinColumn(name = "ATTAINMENT_TARGET_ID")})
    @Cascade(CascadeType.SAVE_UPDATE)
    public Set<AttainmentLevel> getCourseOutcomeAttainmentTargets() {
        return courseOutcomeAttainmentTargets;
    }

    public void setCourseOutcomeAttainmentTargets(Set<AttainmentLevel> courseOutcomeAttainmentTargets) {
        this.courseOutcomeAttainmentTargets = courseOutcomeAttainmentTargets;
    }

    public double getCourseOutcomeSurveyResults() {
        return courseOutcomeSurveyResults;
    }

    public void setCourseOutcomeSurveyResults(double courseOutcomeSurveyResults) {
        this.courseOutcomeSurveyResults = courseOutcomeSurveyResults;
    }

    @Override
    public String toString() {
        return "CourseOffering";
    }

    @Override
    public Serializable primaryKey() {
        return sectionNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof CourseOffering)) {
            return false;
        }

        CourseOffering that = (CourseOffering) obj;
        if (!(this.sectionNumber == that.sectionNumber)) {
            return false;
        }

        if (this.course == null
                && !(this.course == that.course)
                && !(this.course.equals(that.course))) {
            return false;
        }

        return true;
    }
}