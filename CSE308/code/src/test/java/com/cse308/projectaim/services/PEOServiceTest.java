package com.cse308.projectaim.services;

import com.cse308.junit.ordering.Order;
import com.cse308.junit.ordering.OrderedRunner;
import com.cse308.junit.rules.TestCasePrinterRule;
import com.cse308.projectaim.ProjectAimException;
import com.cse308.projectaim.beans.PeoBean;
import com.cse308.projectaim.hibernate.AIMEntity;
import com.cse308.projectaim.hibernate.AIMStore;
import com.cse308.projectaim.hibernate.types.PEO;
import java.util.List;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.runner.RunWith;

@RunWith(OrderedRunner.class)
public class PEOServiceTest {

    @Rule
    public TestCasePrinterRule pr = new TestCasePrinterRule(System.out);
    private static AIMStore aimStore = AIMStore.getInstance();
    private PeoService peoService = new PeoService();
    private static String id = "test-PEO-999-213123";
    private String shortName = "test-PEO-999";
    private String description = "test-999th PEO";
    private Integer sequence = 22232123;
    private double targetAttainmentLevel = 8888.0;

    @AfterClass
    public static void tearDownClass() {
        Logger.getRootLogger().info("[PeoServiceTest.tearDown]");
        PEO peo = new PEO();
        peo.setId(id);

        try {
            aimStore.delete(peo);
        } catch (ProjectAimException ex) {
            fail("Failed to cleanup after PEOService tests: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 1)
    public void testCreate() {
        PeoBean peoBean = new PeoBean();
        peoBean.setId(id);
        peoBean.setShortName(shortName);
        peoBean.setDescription(description);
        peoBean.setSequence(sequence);
        boolean result = peoService.create(peoBean);
        assertEquals(true, result);
    }

    @Test
    @Order(order = 2)
    public void testGetAll() {
        List<AIMEntity> list = peoService.getAll(new PEO());
        assertTrue(1 <= list.size());
    }

    @Test
    @Order(order = 3)
    public void testUpdate() {
        PEO peo = new PEO();
        peo.setId(id);
        peo.setShortName(shortName + "-updated");
        peo.setDescription(description + "-updated");
        peo.setSequence(sequence + 2);

        boolean result = peoService.update(peo);
        assertEquals("Failed to update PEO.", true, result);

        peo = new PEO();
        peo.setId(id);
        try {
            peo = (PEO) aimStore.retrieve(peo);
        } catch (ProjectAimException ex) {
            fail("Failed to update PEO: " + ex.getMessage());
        }
        assertEquals(id, peo.getId());
        assertEquals(shortName + "-updated", peo.getShortName());
        assertEquals(description + "-updated", peo.getDescription());
        assertTrue(sequence + 2 == peo.getSequence());
    }
}
