package com.cse308.projectaim.services;

import com.cse308.junit.rules.TestCasePrinterRule;
import org.junit.Rule;
import org.junit.Test;

public class ProjectAIMPopulatorTest {

    @Rule
    public TestCasePrinterRule pr = new TestCasePrinterRule(System.out);

    @Test
    public void populateDatabase() throws Exception {
        ProjectAIMPopulator pap = new ProjectAIMPopulator();
        //pap.purgeDatabase();
        //pap.populateDatabase();
        assert(true);
    }
}
