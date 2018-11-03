package com.r2_d2.parser.arithmetic_string_parsing;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArithmeticStringParsingTest {
    private ArithmeticStringParsing parser;
    private static final double DELTA = 1e-15;

    @Before
    public void setUp() throws Exception {
        parser = new ArithmeticStringParsing();
    }

    @After
    public void tearDown() throws Exception {
        parser = null;
    }

    @Test
    public void calculateExpression() {
        assertEquals(4.0, parser.calculateExpression("2*2"), DELTA);
        assertEquals(6.0, parser.calculateExpression("2*2+2"), DELTA);
        assertEquals(4.0, parser.calculateExpression("8/4 + 2"), DELTA);
        assertEquals(6.0, parser.calculateExpression("8/4 + 2*2"), DELTA);

    }
}