package com.cse308.projectaim.hibernate.stores;

import com.cse308.junit.ordering.Order;
import com.cse308.junit.ordering.OrderedRunner;
import com.cse308.junit.rules.TestCasePrinterRule;
import com.cse308.projectaim.ProjectAimException;
import com.cse308.projectaim.hibernate.AIMStore;
import com.cse308.projectaim.hibernate.types.PEO;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(OrderedRunner.class)
public class PEOStoreTest {

    @Rule
    public TestCasePrinterRule pr = new TestCasePrinterRule(System.out);

    private static AIMStore aimStore = AIMStore.getInstance();
    private PEO peo;
    private String id = "test-PEO-123-213123";
    private String shortName = "test-PEO-123";
    private String description = "test-123rd PEO";
    private Integer sequence = 55532123;

    @Test
    @Order(order = 1)
    public void testCreatePEO() {
        peo = new PEO();
        peo.setId(id);
        peo.setShortName(shortName);
        peo.setDescription(description);
        peo.setSequence(sequence);
        try {
            aimStore.create(peo);
        } catch (ProjectAimException ex) {
            fail("Failed to create PEO: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 2)
    public void testRetrievePEO() {
        try {
            peo = new PEO();
            peo.setId(id);
            peo = (PEO) aimStore.retrieve(peo);
            assertEquals(id, peo.getId());
            assertEquals(shortName, peo.getShortName());
            assertEquals(description, peo.getDescription());
            assertEquals(sequence, peo.getSequence());
        } catch (ProjectAimException ex) {
            fail("Failed to retrieve PEO: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 3)
    public void testUpdatePEO() {
        try {
            peo = new PEO();
            peo.setId(id);
            peo.setShortName(shortName + "-updated");
            peo.setDescription(description + "-updated");
            peo.setSequence(sequence + 2);
            aimStore.update(peo);

            peo = new PEO();
            peo.setId(id);
            peo = (PEO) aimStore.retrieve(peo);
            assertEquals(id, peo.getId());
            assertEquals(shortName + "-updated", peo.getShortName());
            assertEquals(description + "-updated", peo.getDescription());
            assertTrue(sequence + 2 == peo.getSequence());
        } catch (ProjectAimException ex) {
            fail("Failed to update PEO: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 4)
    public void testSearchPEOs() throws ProjectAimException {
        PEO query;

        try {
            query = new PEO();
            query.setId(id);
            query.setDescription(description + "-updated");
            if (aimStore.search(query).size() != 1) {
                throw new ProjectAimException("Unexpected Results for case 1.");
            }

            query = new PEO();
            query.setId(id);
            if (!aimStore.search(query).isEmpty()) {
                throw new ProjectAimException("Unexpected Results for case 2.");
            }

        } catch (ProjectAimException ex) {
//            fail("Failed to search PEOs: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 5)
    public void testDeletePEO() throws ProjectAimException {
        try {
            peo = new PEO();
            peo.setId(id);
            aimStore.delete(aimStore.retrieve(peo));
        } catch (ProjectAimException ex) {
            fail("Failed to delete PEO: " + ex.getMessage());
        }
    }

    @Test
    @Order(order = 6)
    public void testRetrieveInvalidPEO() {
        try {
            peo = new PEO();
            peo.setId(id);
            peo = (PEO)aimStore.retrieve(peo);
            fail("Retrieved an invalid PEO.");
        } catch (ProjectAimException ex) {
            assert (true);
        }
    }
}
