package com.eonsahead.swing;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VectorTest {
    
    private static final double EPSILON = 1E-8;
    
    public VectorTest() {
    } // VectorTest()

    @Test
    public void testGet() {
        int index = 0;
        Vector instance = new Vector( 99.0, 0.0, 0.0 );
        double expResult = 99.0;
        double result = instance.get(index);
        assertEquals(expResult, result, EPSILON);
    } // testGet()

    @Test
    public void testSet_int_double() {
        System.out.println("set");
        int index = 0;
        double value = 0.0;
        Vector instance = new Vector();
        instance.set(index, value);
        fail("The test case is a prototype.");
    } // testSet_int_double()

    @Test
    public void testSet_Vector() {
        System.out.println("set");
        Vector v = null;
        Vector instance = new Vector();
        instance.set(v);
        fail("The test case is a prototype.");
    } // testSet_Vector()

    @Test
    public void testAdd() {
        System.out.println("add");
        Vector v = null;
        Vector instance = new Vector();
        Vector expResult = null;
        Vector result = instance.add(v);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    } // testAdd()

    @Test
    public void testSubtract() {
        System.out.println("subtract");
        Vector v = null;
        Vector instance = new Vector();
        Vector expResult = null;
        Vector result = instance.subtract(v);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    } // testSubtract()

    @Test
    public void testDot() {
        Vector u = new Vector( 0, 1, 0 );
        Vector v = new Vector( 1, 0, 0 );
        double expResult = 0.0;
        double result = u.dot(v);
        assertEquals(expResult, result, EPSILON);
    } // testDot()

    @Test
    public void testMagnitude() {
        System.out.println("magnitude");
        Vector instance = new Vector();
        double expResult = 0.0;
        double result = instance.magnitude();
        assertEquals(expResult, result, 0.0);
        fail("The test case is a prototype.");
    } // testMagnitude()

    @Test
    public void testNormalize() {
        System.out.println("normalize");
        Vector instance = new Vector();
        Vector expResult = null;
        Vector result = instance.normalize();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    } // testNormalize()

    @Test
    public void testCross() {
        System.out.println("cross");
        Vector v = null;
        Vector instance = new Vector();
        Vector expResult = null;
        Vector result = instance.cross(v);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    } // testCross() 
} // VectorTest