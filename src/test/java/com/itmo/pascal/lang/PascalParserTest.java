package com.itmo.pascal.lang;

import com.intellij.testFramework.ParsingTestCase;
import org.junit.Test;


public class PascalParserTest extends ParsingTestCase {
    public PascalParserTest() {
        super("parser", "pas", true, new PascalParserDefinition());
    }

    @Test
    public void testSimple() {
        doTest(true);
    }

    @Test
    public void testSample1() {
        doTest(true);
    }

    @Test
    public void testSample2() {
        doTest(true);
    }

    @Test
    public void testSample3() {
        doTest(true);
    }

    @Override
    public String getTestDataPath() {
        return "src/test/java/data";
    }
}