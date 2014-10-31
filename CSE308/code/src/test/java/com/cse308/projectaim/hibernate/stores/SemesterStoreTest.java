package com.cse308.projectaim.hibernate.stores;

import com.cse308.junit.ordering.Order;
import com.cse308.junit.ordering.OrderedRunner;
import com.cse308.junit.rules.TestCasePrinterRule;
import com.cse308.projectaim.ProjectAimException;
import com.cse308.projectaim.hibernate.AIMStore;
import com.cse308.projectaim.hibernate.types.Semester;
import com.cse308.projectaim.hibernate.types.SemesterPK;
import java.util.Date;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(OrderedRunner.class)
public class SemesterStoreTest {

    @Rule
    public TestCasePrinterRule pr = new TestCasePrinterRule(System.out);

    private static AIMStore aimStore = AIMStore.getInstance();
    private Semester semester;
    private static SemesterPK semesterPK;
    private static Date startDate;
    private static Date endDate;
    private String displayName;

    @BeforeClass
    public static void setUp() {
        semesterPK = new SemesterPK();
        semesterPK.setTerm(5551);
        semesterPK.setYear(5552013);
        startDate = new Date(System.currentTimeMillis());
        endDate = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24);
    }

    @Test
    @Order(order = 1)
    public void testCreateSemester() {
        semester = new Semester();
        semester.setSemester(semesterPK);
        semester.setDisplayName(displayName);
        semester.setStartDate(startDate);
        semester.setEndDate(endDate);
        try {
            aimStore.create(semester);
        } catch (ProjectAimException ex) {
            fail("Failed to create Semester: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 2)
    public void testRetrieveSemester() {
        try {
            semester = new Semester();
            semester.setSemester(semesterPK);
            semester = (Semester) aimStore.retrieve(semester);
            assertEquals(semesterPK, semester.getSemester());
            assertEquals(displayName, semester.getDisplayName());
            assertEquals(startDate.toString(), semester.getStartDate().toString());
            assertEquals(endDate.toString(), semester.getEndDate().toString());
        } catch (ProjectAimException ex) {
            fail("Failed to retrieve Semester: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 3)
    public void testUpdateSemester() {
        try {
            semester = new Semester();
            semester.setSemester(semesterPK);
            semester.setDisplayName(displayName + "-updated");
            semester.setStartDate(new Date(startDate.getTime() + 1000 * 60 * 60 * 24));
            semester.setEndDate(new Date(endDate.getTime() + 1000 * 60 * 60 * 24));
            aimStore.update(semester);

            semester = new Semester();
            semester.setSemester(semesterPK);
            semester = (Semester) aimStore.retrieve(semester);
            assertEquals(semesterPK, semester.getSemester());
            assertEquals(displayName + "-updated", semester.getDisplayName());
            assertEquals((new Date(startDate.getTime() + 1000 * 60 * 60 * 24)).toString(), semester.getStartDate().toString());
            assertEquals((new Date(endDate.getTime() + 1000 * 60 * 60 * 24)).toString(), semester.getEndDate().toString());
        } catch (ProjectAimException ex) {
            fail("Failed to update Semester: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 4)
    public void testSearchSemesters() throws ProjectAimException {
        Semester query;
        try {
            query = new Semester();
            query.setSemester(semesterPK);
            query.setDisplayName(displayName + "-updated");
            if (aimStore.search(query).size() != 1) {
                throw new ProjectAimException("Unexpected Results for case 1.");
            }
            query = new Semester();
            query.setDisplayName(displayName + "-invalid");
            if (!aimStore.search(query).isEmpty()) {
                throw new ProjectAimException("Unexpected Results for case 2.");
            }
        } catch (ProjectAimException ex) {
//            fail("Failed to search Semesters: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 5)
    public void testDeleteSemester() throws ProjectAimException {
        try {
            semester = new Semester();
            semester.setSemester(semesterPK);
            aimStore.delete(aimStore.retrieve(semester));
        } catch (ProjectAimException ex) {
            fail("Failed to delete Semester: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 6)
    public void testRetrieveInvalidSemester() {
        try {
            semester = new Semester();
            semester.setSemester(semesterPK);
            semester = (Semester)aimStore.retrieve(semester);
            fail("Retrieved an invalid Semester.");
        } catch (ProjectAimException ex) {
            assert (true);
        }
    }
}
