package com.cse308.junit.rules;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;

import org.junit.rules.ExternalResource;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class TestCasePrinterRule implements TestRule {

    private OutputStream out = null;
    private final TestCasePrinter printer = new TestCasePrinter();
    private String beforeContent = null;
    private String afterContent = null;
    private long timeStart;
    private long timeEnd;

    public TestCasePrinterRule(OutputStream os) {
        out = os;
    }

    private class TestCasePrinter extends ExternalResource {

        @Override
        protected void before() throws Throwable {
            timeStart = System.currentTimeMillis();
            out.write(beforeContent.getBytes());
        }

        @Override
        protected void after() {
            try {
                timeEnd = System.currentTimeMillis();
                double seconds = (timeEnd - timeStart) / 1000.0;
                out.write((afterContent + "Time elapsed: " + new DecimalFormat("0.000").format(seconds) + " sec\n").getBytes());
            } catch (IOException ioe) {
                /* ignore */
            }
        }
    }

    public final Statement apply(Statement statement, Description description) {
        beforeContent = "\n[TEST START] " + description.getMethodName() + "\n"; // description.getClassName() to get class name
        afterContent = "[TEST ENDED] ";
        return printer.apply(statement, description);
    }
}