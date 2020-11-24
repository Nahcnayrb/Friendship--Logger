package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InterestTest {
    private Interest testInterest;


    @Test
    public void testConstructor() {
        testInterest = new Interest("blah");
        assertEquals("blah", testInterest.getInterest());

    }
}
